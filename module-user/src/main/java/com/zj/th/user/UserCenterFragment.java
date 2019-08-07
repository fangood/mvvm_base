package com.zj.th.user;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zj.databinding.mvvm.view.fragment.MvvmFragment;
import com.zj.th.base.widget.ImageUtil;
import com.zj.th.base.widget.ToastManager;
import com.zj.th.data.remote.user.UserDetailInfo;
import com.zj.th.user.databinding.UserFragmentUserCenterBinding;

/**
 * 个人中心页面
 *
 * V1.0
 */
@Route(path = "/user/userCenter")
public class UserCenterFragment extends MvvmFragment<UserFragmentUserCenterBinding> {

    private UserDetailInfo detailInfo;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getViewDataBinding().getViewModel().getUserInfo(loginUser.getUserId());
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.user_fragment_user_center;
    }

    @Override
    public void bindViewModels(@NonNull UserFragmentUserCenterBinding binding) {
        UserCenterViewModel userCenterViewModel = ViewModelProviders.of(this)
                .get(UserCenterViewModel.class);
        binding.setViewModel(userCenterViewModel);
    }

    @Override
    public void registerViewEvents(@NonNull UserFragmentUserCenterBinding binding) {

        binding.userCenterBezier.setUpWithAppBarLayout(
                binding.userCenterAppbar,
                R.id.user_center_title);

        binding.userCenterAppbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            int maxOffset = appBarLayout.getHeight() - getViewDataBinding().userCenterTitle.getHeight();
            getViewDataBinding().userCenterHead.setAlpha(1 + ((float) verticalOffset) / maxOffset);
        });


        binding.ivHeader.setOnClickListener(view -> {
            ARouter.getInstance()
                    .build("/user/PersionInfoActivity")
                    .navigation();

        });

        //扫描二维码
        binding.userCenterTitle.setLeftClickListener(v ->
                        ToastManager.show(getContext(),"扫描二维码")
        );
    }

    @Override
    public void subscribeViewModelChanges() {
        getViewDataBinding().getViewModel()
                .successData
                .observe(this, apiResult -> {
                    detailInfo = (UserDetailInfo) apiResult.getData();
                    ImageUtil.loadUrl(detailInfo.getAvatarSrc(), getViewDataBinding().ivHeader);

                    getViewDataBinding().tvName.setText(detailInfo.getCustomerName());
                    getViewDataBinding().tvPhone.setText(detailInfo.getTelephone());
                });

        getViewDataBinding()
                .getViewModel()
                .errorMessage
                .observe(this, s -> {
                    ToastManager.show(getContext(), s);
                });
    }


}
