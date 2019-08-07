package com.zj.th.user.register;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zj.th.base.activity.AppMvvmActivity;
import com.zj.th.data.remote.request.PostCodeRequest;
import com.zj.th.user.R;
import com.zj.th.user.databinding.UserActivityRegisterBinding;

@Route(path = "/user/Register")
public class RegisterActivity extends AppMvvmActivity<UserActivityRegisterBinding> {

    private int mCurrentStep = 1;

    private Animation mInFromLeft;
    private Animation mOutToLeft;
    private Animation mInFromRight;
    private Animation mOutToRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInFromLeft = AnimationUtils.loadAnimation(this, R.anim.user_anim_in_from_left);
        mOutToLeft = AnimationUtils.loadAnimation(this, R.anim.user_anim_out_to_left);
        mInFromRight = AnimationUtils.loadAnimation(this, R.anim.user_anim_in_from_right);
        mOutToRight = AnimationUtils.loadAnimation(this, R.anim.user_anim_out_to_right);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.user_activity_register;
    }

    @Override
    public void bindViewModels(@NonNull UserActivityRegisterBinding binding) {
        RegisterViewModel viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        binding.setViewModel(viewModel);
        SmsCodeViewModel smsCodeViewModel = ViewModelProviders.of(this, new SmsCodeViewModel.Factory(PostCodeRequest.SMSCODE_TYPE_REGISTER))
                .get(SmsCodeViewModel.class);
        binding.setSmsCodeViewModel(smsCodeViewModel);
        smsCodeViewModel.phone.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                getBinding().getViewModel().phone.set(((ObservableField<String>) sender).get());
            }
        });
        PasswordViewModel passwordViewModel = ViewModelProviders.of(this).get(PasswordViewModel.class);
        binding.setPasswordViewModel(passwordViewModel);
    }

    @Override
    public void registerViewEvents(@NonNull UserActivityRegisterBinding binding) {
        binding.registerTitle.setLeftClickListener(v -> {
            if (mCurrentStep == 1) {
                finish();
            } else {
                backToStep1();
            }
        });
        binding.registerNextStep.setOnClickListener(v -> {
            if (mCurrentStep == 1) {
                if (!getBinding().getViewModel().canGoStep2()) {
                    return;
                }
                /**
                 * 验证手机号是否存在
                 * */

                // if (getBinding().getViewModel().canGoStep2()) {
                //     getBinding().getSmsCodeViewModel().postCodeCommand.execute();
                // }
            } else {
                getBinding().getViewModel().checkSmsCodeAndPassword(PostCodeRequest.SMSCODE_TYPE_REGISTER);
            }
        });
        binding.registerAgreement.setOnClickListener(v ->
                ARouter.getInstance()
                        .build("/user/Register/Agreement")
                        .navigation());
    }

    private void jumpLogin(String phone) {
        ARouter.getInstance()
                .build("/user/Login")
                .withString("phone", phone)
                .navigation();
        finish();
    }

    @Override
    public void subscribeViewModelChanges() {
        subscribeViewModelChanges(getBinding().getViewModel());
        subscribeViewModelChanges(getBinding().getSmsCodeViewModel());
        // 将smsCode变化同步致SmsCodeViewModel
        getBinding().getViewModel().smsCode.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                getBinding().getSmsCodeViewModel().smsCode.set(((ObservableField<String>) sender).get());
            }
        });
        getBinding().getSmsCodeViewModel()
                .getSmsCodeSendSuccess()
                .observe(this, aVoid -> {
                    if (mCurrentStep == 1) {
                        navigationStep2();
                    }
                });
        getBinding().getViewModel()
                .navigationPickRole
                .observe(this, o -> navigationRolePicker());

        //接口成功了干嘛?
//        getBinding().getSmsCodeViewModel()
//                .getSmsCodeCheckSuccess()
//                .observe(this, o -> getBinding().getViewModel().register());
    }

    private void navigationStep2() {
        mCurrentStep = 2;
        getBinding().registerStep1Container.startAnimation(mOutToLeft);
        getBinding().registerStep2Container.startAnimation(mInFromRight);
        getBinding().registerStep1Container.setVisibility(View.INVISIBLE);
        getBinding().registerStep2Container.setVisibility(View.VISIBLE);
        getBinding().getViewModel().nextStepName.set("注册");
    }

    private void backToStep1() {
        mCurrentStep = 1;
        getBinding().registerStep1Container.startAnimation(mInFromLeft);
        getBinding().registerStep2Container.startAnimation(mOutToRight);
        getBinding().registerStep1Container.setVisibility(View.VISIBLE);
        getBinding().registerStep2Container.setVisibility(View.INVISIBLE);
        getBinding().getViewModel().password.set("");
        getBinding().getSmsCodeViewModel().smsCodeText.set("");
        getBinding().getViewModel().nextStepName.set("下一步");
        getBinding().getSmsCodeViewModel().reset();
    }

    private void navigationRolePicker() {
        ARouter.getInstance().build("/user/PickRole")
                .withString("name", getBinding().getViewModel().name.get())
                .withString("phone", getBinding().getViewModel().phone.get())
                .withString("password", getBinding().getViewModel().password.get())
                .withString("smsCode", getBinding().getViewModel().smsCode.get())
                .navigation(this);
    }
}
