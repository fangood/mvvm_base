package com.zj.th.user.login;

import android.text.TextUtils;

import com.zj.databinding.mvvm.viewmodel.Command;
import com.zj.databinding.mvvm.viewmodel.SingleLiveEvent;
import com.zj.th.base.App;
import com.zj.th.base.utils.ACache;
import com.zj.th.base.utils.Validator;
import com.zj.th.base.viewmodel.AppViewModel;
import com.zj.th.data.remote.ErrorParser;
import com.zj.th.data.remote.exception.ApiNotSuccessException;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 登录页ViewModel
 */
public class LoginViewModel extends AppViewModel {

    public final ObservableField<String> phone = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableField<String> verificationCode = new ObservableField<>();
    private final SingleLiveEvent loginSuccessEvent = new SingleLiveEvent();
    private final SingleLiveEvent loginFailedEvent = new SingleLiveEvent();
    private final SingleLiveEvent accountNotActivateEvent = new SingleLiveEvent();

    private final SingleLiveEvent passwordErrorEvent = new SingleLiveEvent();

    protected boolean isPasswordMode = true;

    public Command loginCommand = () -> {
        //TODO
//        if (!validatePhone()) {
//            return;
//        }
//        if (isPasswordMode && !validatePassword()) {
//            return;
//        }
//        if (!isPasswordMode && !validateSmsCode()) {
//            postMessage("请输入正确的验证码");
//            return;
//        }
        LoginModel model = new LoginModel();
        (isPasswordMode ? model.loginByPassword(phone.get(), password.get()) : model.loginBySmsCode(phone.get(), verificationCode.get()))
                // 订阅接口调用，并执行以上流程
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> postProgressStart())
                .doFinally(() -> postProgressDone())
                .subscribe(result -> {
                    // 接口调用成功的回调
                    postMessage("登录成功");
                    loginSuccessEvent.call();
                }, throwable -> {
                    passwordErrorEvent.call();
                    // 接口调用失败，或以上步骤中抛出了异常的回调
                    if (throwable instanceof ApiNotSuccessException
                            && TextUtils.equals(((ApiNotSuccessException) throwable).getCode(), "6001")) {
                        accountNotActivateEvent.call();
                        ACache.get(App.get()).put(phone.get() + "notActivateAfterRegister", phone.get());
                    } else {
                        postMessage(ErrorParser.parse(throwable));
                        loginFailedEvent.call();
                    }
                });
    };


    public void setPasswordMode(boolean passwordMode) {
        isPasswordMode = passwordMode;
    }

    private boolean validatePhone() {
        if (!Validator.isMobileNO(phone.get())) {
            postMessage("请输入正确的手机号");
            return false;
        }
        return true;
    }

    private boolean validateSmsCode() {
        if (TextUtils.isEmpty(verificationCode.get())) {
            postMessage("请输入验证码");
            return false;
        }
        if (verificationCode.get().length() != 6) {
            postMessage("请输入验证码");
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        if (TextUtils.isEmpty(password.get())) {
            postMessage("密码不能为空");
            return false;
        }
        if (!Validator.isPassword(password.get())) {
            postMessage("密码8-20位，必须包含数字和大小写字母");
            return false;
        }
        return true;
    }

    public LiveData getLoginSuccessEvent() {
        return loginSuccessEvent;
    }

    public LiveData getAccountNotActivateEvent() {
        return accountNotActivateEvent;
    }

    public LiveData getLoginFailedEvent() {
        return loginFailedEvent;
    }

    public SingleLiveEvent getPasswordErrorEvent() {
        return passwordErrorEvent;
    }

}
