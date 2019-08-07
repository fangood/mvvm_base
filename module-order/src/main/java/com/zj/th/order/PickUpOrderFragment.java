package com.zj.th.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zj.databinding.mvvm.view.fragment.MvvmFragment;
import com.zj.th.base.App;
import com.zj.th.base.utils.ARouterExt;
import com.zj.th.base.widget.ToastManager;
import com.zj.th.order.databinding.OrderFragmentPickUpOrderBinding;

@Route(path = "/order/pickUpOrder")
public class PickUpOrderFragment extends MvvmFragment<OrderFragmentPickUpOrderBinding> {

    private final int REQUEST_SEARCH_KEYWORD = 1;
    private OrderListFragment orderListFragment;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 角色切换的监听
        App.get().getLiveCurrentPosition().observe(this, position -> {

            getViewDataBinding().getViewModel().positionName.set(position.getPositionName());

            orderListFragment = (OrderListFragment) ARouter.getInstance()
                    .build("/order/pickup/OrderListFragment")
                    .withInt("type", 1)
                    .withInt("orderType", position.getOrderTypes())
                    .withString("keyword", getViewDataBinding().getViewModel().keyword.get())
                    .navigation();

            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.pick_up_order_content, orderListFragment)
                    .commit();
        });
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.order_fragment_pick_up_order;
    }

    @Override
    public void bindViewModels(@NonNull OrderFragmentPickUpOrderBinding binding) {
        PickUpOrderViewModel viewModel = ViewModelProviders.of(this).get(PickUpOrderViewModel.class);
        binding.setViewModel(viewModel);
    }

    @Override
    public void registerViewEvents(@NonNull OrderFragmentPickUpOrderBinding binding) {
        binding.pickUpOrderSearch.setOnClickListener(v -> {
//                ARouterExt.navigation(this,
//                ARouter.getInstance()
//                        .build("/order/search")
//                        .withInt("searchType", 1)
//                        .withString("keyword", getViewDataBinding().getViewModel().keyword.get()),
//                REQUEST_SEARCH_KEYWORD)
                    ToastManager.show(getContext(), "搜索结果带回调");
                }
        );
        binding.titleBarRolePicker.setOnClickListener(view -> ToastManager.show(getContext(), "chang role"));
        binding.pickUpOrderTitleBar.setRightClickListener(v -> ARouter.getInstance().build("/order/pickUpOrder/map").navigation());
    }

    @Override
    public void subscribeViewModelChanges() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SEARCH_KEYWORD && resultCode == Activity.RESULT_OK) {
            String keyword = data.getStringExtra("keyword");
            getViewDataBinding().getViewModel().setKeyword(keyword);
        }
    }

}
