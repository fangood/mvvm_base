package com.zj.th.order;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zj.databinding.mvvm.view.fragment.MvvmFragment;
import com.zj.th.base.widget.ToastManager;
import com.zj.th.order.databinding.OrderFragmentListBinding;


@Route(path = "/order/orderList")
public class OrderListFragment extends MvvmFragment<OrderFragmentListBinding> {

    @Override
    public int getContentLayoutId() {
        return R.layout.order_fragment_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //请求数据
//        getViewDataBinding().getCleanerModel().getOrderList();
    }

    @Override
    public void bindViewModels(@NonNull OrderFragmentListBinding binding) {
        OrderViewModel cleanerViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        binding.setCleanerModel(cleanerViewModel);
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void registerViewEvents(@NonNull OrderFragmentListBinding binding) {

        binding.cleanerTab.addTab(binding.cleanerTab.newTab().setText("抢单记录"));
        binding.cleanerTab.addTab(binding.cleanerTab.newTab().setText("已接订单"));
        binding.cleanerTab.addTab(binding.cleanerTab.newTab().setText("完工订单"));

        binding.cleanerTab.addOnTabSelectedListener(listener);

        binding.llTab.etSearch.addTextChangedListener(textWatcher);
        binding.llTab.etSearch.setOnClickListener(view -> {

        });

        binding.llTab.ivClean.setOnClickListener(view -> {
            binding.llTab.etSearch.setText("");
        });

        //  列表
        initRecyclerView(binding);
        binding.llTab.orderMenu.setOnClickListener(view -> {

        });


        binding.llTab.ivClean.setOnClickListener(view -> {
            getViewDataBinding().llTab.etSearch.setText("");
        });

        binding.llTab.tvSearch.setOnClickListener(view -> {
            Editable text = getViewDataBinding().llTab.etSearch.getText();
            ToastManager.show(getContext(), text.toString());
        });
    }

    private void initRecyclerView(OrderFragmentListBinding binding) {
        binding.recyclerView.setLoadingListener(loadingListener);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setLimitNumberToCallLoadMore(2);
        binding.recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        binding.recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

//        receiveAdapter = new CleanerReceiveAdapter(1);
//        binding.recyclerView.setAdapter(receiveAdapter);

        binding.recyclerView.getDefaultFootView().setLoadingHint("加载中");
        binding.recyclerView.getDefaultFootView().setNoMoreHint("加载完毕");
    }

    private XRecyclerView.LoadingListener loadingListener =
            new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {

                }

                @Override
                public void onLoadMore() {

                }
            };

    @Override
    public void subscribeViewModelChanges() {
        getViewDataBinding()
                .getCleanerModel()
                .successData
                .observe(this, apiResult -> {
                    // 订单列表数据
                    getViewDataBinding().recyclerView.refreshComplete();
                    //获取数据刷新列表

                });
        getViewDataBinding()
                .getCleanerModel()
                .errorMessage
                .observe(this, result -> {

                });
    }


    TextWatcher textWatcher = new TextWatcher() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!TextUtils.isEmpty(charSequence)) {
                getViewDataBinding().llTab.ivClean.setVisibility(View.VISIBLE);
            } else {
                getViewDataBinding().llTab.ivClean.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = tab.getPosition();

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

}
