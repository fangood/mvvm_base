package com.zj.th.contacts;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.tabs.TabLayout;
import com.zj.databinding.adapter.FragmentPagerAdapter;
import com.zj.databinding.mvvm.view.fragment.MvvmFragment;
import com.zj.th.contacts.databinding.ContactsFragmentContactsBinding;
import com.zj.th.contacts.utils.PinYin;
import com.zj.th.data.remote.contacts.FriendBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 通讯录
 * V1.0
 */
@Route(path = "/contacts/contacts")
public class ContactsFragment extends MvvmFragment<ContactsFragmentContactsBinding> implements TabLayout.OnTabSelectedListener {

    private List<Fragment> fragments;
    private List<CharSequence> titles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PinYin.init(getActivity());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.contacts_fragment_contacts;
    }

    @Override
    public void bindViewModels(@NonNull ContactsFragmentContactsBinding binding) {
        ContactsViewModel viewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        binding.setViewModel(viewModel);
        binding.contactsViewpager.setOffscreenPageLimit(0);
    }

    @Override
    public void registerViewEvents(@NonNull ContactsFragmentContactsBinding binding) {
        binding.contactsTitle.setLeftClickListener(v -> ARouter.getInstance().build("/contacts/ScanQrCode").navigation());//扫码
        binding.contactsTab.addOnTabSelectedListener(this);
        binding.addContactsSearch.setOnClickListener(v -> {

            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    ((ContactsSubFragment) fragment).setKeyword(getViewDataBinding().getViewModel().keyword.get());
                }
            }
        });
    }

    @Override
    public void subscribeViewModelChanges() {
        ContactsManager.get().getContacts().observe(this, tabs -> {

            fragments = new ArrayList<>();
            titles = new ArrayList<>();

            if (tabs == null || tabs.size() == 0) {
                getViewDataBinding().contactsNoContacts.setVisibility(View.VISIBLE);
            } else {
                getViewDataBinding().contactsNoContacts.setVisibility(View.GONE);
            }

            if (tabs != null) {
                for (ContactTab tab : tabs) {
                    Fragment fragment = getFragment(tab.getTitle(), tab.getFriends());
                    if (fragment != null) {
                        fragments.add(fragment);
                        titles.add(tab.getTitle());
                    }
                }
            }

            FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager(), fragments, titles);
            getViewDataBinding().contactsViewpager.setAdapter(adapter);

            getViewDataBinding().contactsTab.setupWithViewPager(getViewDataBinding().contactsViewpager);
        });
    }

    protected Fragment getFragment(String title, List<FriendBean> friends) {
        Postcard fragmentPostCard = ARouter.getInstance()
                .build("/contacts/contacts/sub")
                .withObject("friends", friends);
        if (!TextUtils.isEmpty(title) && title.equalsIgnoreCase("项目经理")) {
            fragmentPostCard.withBoolean("buttonShow", false);
        } else {
            fragmentPostCard.withBoolean("buttonShow", true);
        }
        return (Fragment) fragmentPostCard.navigation();
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
