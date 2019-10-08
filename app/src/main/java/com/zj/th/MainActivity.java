package com.zj.th;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.tabs.TabLayout;
import com.zj.databinding.adapter.FragmentPagerAdapter;
import com.zj.databinding.mvvm.view.activity.MvvmActivity;
import com.zj.th.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 */
@Route(path = "/app/main")
public class MainActivity extends MvvmActivity<ActivityMainBinding> implements TabLayout.OnTabSelectedListener {

    private List<Fragment> fragments = new ArrayList<>();
    private List<CharSequence> titles = new ArrayList<>();

    @Autowired
    public boolean fromRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void bindViewModels(@NonNull ActivityMainBinding binding) {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);
    }

    @Override
    public void registerViewEvents(@NonNull ActivityMainBinding binding) {
        getViewDataBinding().mainTab.setupWithViewPager(getViewDataBinding().mainViewpager);
        getViewDataBinding().mainTab.addOnTabSelectedListener(this);
    }

    @Override
    public void subscribeViewModelChanges() {
        // ViewModel创建的时候，tabs已经初始化好了
        initTabs(getViewDataBinding().getViewModel().tabs);
    }

    public void initTabs(List<Tab> tabs) {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();

        for (Tab tab : tabs) {
            Fragment fragment = getFragment(tab.getFragment());
            if (fragment != null) {
                fragments.add(fragment);
                titles.add(tab.getTitle());
            }
        }

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        getViewDataBinding().mainViewpager.setAdapter(adapter);

        int count = getViewDataBinding().mainTab.getTabCount();

        for (int index = 0; index < count; index++) {
            getViewDataBinding().mainTab.getTabAt(index).setCustomView(getTabView(tabs.get(index)));
        }

        if (count > 0) {
            getViewDataBinding().mainTab.getTabAt(0).select();
        }
    }

    protected Fragment getFragment(String path) {
        return (Fragment) ARouter.getInstance()
                .build(path)
                .navigation();
    }

    public View getTabView(Tab tab) {
        CheckBox view = (CheckBox) View.inflate(this, R.layout.item_main_tab, null);
        view.setCompoundDrawablesWithIntrinsicBounds(0, tab.getIcon(), 0, 0);
        view.setText(tab.getTitle());
        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getCustomView() != null) {
            ((CheckBox) tab.getCustomView()).setChecked(true);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if (tab.getCustomView() != null) {
            ((CheckBox) tab.getCustomView()).setChecked(false);
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        if (tab.getCustomView() != null) {
            ((CheckBox) tab.getCustomView()).setChecked(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Fragment fragment : fragments) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private long previousBackClickTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 过滤按键动作
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        long backClickTime = System.currentTimeMillis();
        // 按返回键不结束activity
        if (backClickTime - previousBackClickTime <= 2000) {
            moveTaskToBack(true);
        } else {
            getViewDataBinding().mainTab.getTabAt(0).select();
        }
        previousBackClickTime = backClickTime;
    }
}
