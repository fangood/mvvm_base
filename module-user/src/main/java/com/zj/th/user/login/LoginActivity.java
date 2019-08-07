package com.zj.th.user.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zj.th.base.TokenManager;
import com.zj.th.base.activity.AppMvvmActivity;
import com.zj.th.data.remote.request.PostCodeRequest;
import com.zj.th.user.R;
import com.zj.th.user.databinding.UserActivityLoginBinding;
import com.zj.th.user.register.PasswordViewModel;
import com.zj.th.user.register.SmsCodeViewModel;

/**
 * 登录页
 * 需要用MVVM实现的Activity，必须继承自MvvmActivity，并传入Activity对应布局生成的Binding
 * <p>
 * 参考：@see <a href="https://msdn.microsoft.com/en-us/library/gg405484(v=PandP.40).aspx">微软关于MVVM的实现介绍</>
 */
@Route(path = "/user/Login")
public class LoginActivity extends AppMvvmActivity<UserActivityLoginBinding> {

    @Autowired
    String phone = "";
    private Animation mInFromLeft;
    private Animation mOutToLeft;
    private Animation mInFromRight;
    private Animation mOutToRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        TokenManager.get().disableTokenExpiredHandler();

        super.onCreate(savedInstanceState);

        mInFromLeft = AnimationUtils.loadAnimation(this, R.anim.user_anim_in_from_left);
        mOutToLeft = AnimationUtils.loadAnimation(this, R.anim.user_anim_out_to_left);
        mInFromRight = AnimationUtils.loadAnimation(this, R.anim.user_anim_in_from_right);
        mOutToRight = AnimationUtils.loadAnimation(this, R.anim.user_anim_out_to_right);
    }

    @Override
    protected void onDestroy() {
        TokenManager.get().enableTokenExpiredHandler();
        super.onDestroy();
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.user_activity_login;
    }

    @Override
    public void bindViewModels(@NonNull UserActivityLoginBinding binding) {
        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        viewModel.setPasswordMode(true);
        binding.setViewModel(viewModel);

        SmsCodeViewModel smsCodeViewModel = ViewModelProviders.of(this, new SmsCodeViewModel.Factory(PostCodeRequest.SMSCODE_TYPE_LOGIN))
                .get(SmsCodeViewModel.class);
        binding.setSmsCodeViewModel(smsCodeViewModel);
        smsCodeViewModel.phone.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                getViewDataBinding().getViewModel().phone.set(((ObservableField<String>) sender).get());
            }
        });

        PasswordViewModel passwordViewModel = ViewModelProviders.of(this).get(PasswordViewModel.class);
        binding.setPasswordViewModel(passwordViewModel);
    }

    @Override
    public void registerViewEvents(@NonNull UserActivityLoginBinding binding) {
        binding.loginTitle.setLeftClickListener(v -> finish());
        binding.loginChangeToPasswordMode.setOnClickListener(v -> changeToPasswordMode());
        binding.loginChangeToVerificationCodeMode.setOnClickListener(v -> changeToVerificationCodeMode());
        //从注册过来的手机号
        binding.getSmsCodeViewModel().phone.set(phone);
    }
    /**
     * 触发
     * */
    @Override
    public void subscribeViewModelChanges() {
        subscribeViewModelChanges(getViewDataBinding().getViewModel());
        subscribeViewModelChanges(getViewDataBinding().getSmsCodeViewModel());
        getViewDataBinding().getViewModel()
                .getLoginSuccessEvent()
                .observe(this, o -> navigationMainActivity());

        getViewDataBinding().getViewModel()
                .getAccountNotActivateEvent()
                .observe(this, o -> {
//                    navigationAccountNotActivate();
                    //TODO
                    navigationMainActivity();
                });

        getViewDataBinding()
                .getViewModel()
                .getPasswordErrorEvent()
                .observe(this, o -> getViewDataBinding().getPasswordViewModel().visible.set(true));

        getViewDataBinding().getViewModel().getLoginFailedEvent().observe(this,o -> {
//            navigationAccountNotActivate();
            //TODO
            navigationMainActivity();
        });

    }

    private void navigationMainActivity() {
        ARouter.getInstance()
                .build("/app/main")
                .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .navigation(this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        finish();
                    }
                });
    }

    private void changeToPasswordMode() {

        getViewDataBinding().getViewModel().setPasswordMode(true);

        getViewDataBinding().loginVerificationCodeLoginContainer.startAnimation(mOutToRight);
        getViewDataBinding().loginPasswordLoginContainer.startAnimation(mInFromLeft);
        getViewDataBinding().loginResetPassword.startAnimation(mInFromLeft);

        // 显示找回密码
        getViewDataBinding().loginResetPassword.setVisibility(View.VISIBLE);
        getViewDataBinding().loginVerificationCodeLoginContainer.setVisibility(View.INVISIBLE);
        getViewDataBinding().loginPasswordLoginContainer.setVisibility(View.VISIBLE);
    }

    private void changeToVerificationCodeMode() {

        getViewDataBinding().getViewModel().setPasswordMode(false);

        getViewDataBinding().loginVerificationCodeLoginContainer.startAnimation(mInFromRight);
        getViewDataBinding().loginPasswordLoginContainer.startAnimation(mOutToLeft);
        getViewDataBinding().loginResetPassword.startAnimation(mOutToLeft);

        // 隐藏找回密码
        getViewDataBinding().loginResetPassword.setVisibility(View.INVISIBLE);
        getViewDataBinding().loginVerificationCodeLoginContainer.setVisibility(View.VISIBLE);
        getViewDataBinding().loginPasswordLoginContainer.setVisibility(View.INVISIBLE);
    }

    /**
     * 未激活账户页面
     */
    @SuppressLint("WrongConstant")
    private void navigationAccountNotActivate() {

        TokenManager.get().enableTokenExpiredHandler();

        ARouter.getInstance()
                .build("/user/accountNotActivate")
                .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .navigation(this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        finish();
                    }
                });
    }


}
