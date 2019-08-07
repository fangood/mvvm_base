package com.zj.th.user.register;

import android.text.TextUtils;

import com.zj.th.base.utils.Validator;
import com.zj.th.base.viewmodel.AppViewModel;
import com.zj.th.data.remote.ErrorParser;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class RegisterViewModel extends AppViewModel {

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> phone = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableField<String> smsCode = new ObservableField<>();
    public final ObservableField<String> nextStepName = new ObservableField<>("下一步");

    public final MutableLiveData navigationPickRole = new MutableLiveData();


    public void register() {
        if (!validateName() || !validatePhone() || !validatePassword() || !validateSmsCode()) {
            return;
        }
        navigationPickRole.postValue(null);
    }

    public boolean canGoStep2() {
        return validateName() && validatePhone();
    }

    public boolean showNoNet() {
        postMessage("您的网络不给力，请检查网络后重新尝试");
        return false;
    }

    public boolean validateName() {
        if (TextUtils.isEmpty(name.get())) {
            postMessage("请输入姓名");
            return false;
        }
        if (TextUtils.isEmpty(name.get().replace(" ", ""))) {
            postMessage("请输入姓名");
            return false;
        }
        return true;
    }

    public boolean validatePhone() {
        if (TextUtils.isEmpty(phone.get())) {
            postMessage("请输入手机号");
            return false;
        }
        if (!Validator.isMobileNO(phone.get())) {
            postMessage("请输入正确的手机号");
            return false;
        }
        return true;
    }

    private boolean validateSmsCode() {
        if (TextUtils.isEmpty(smsCode.get())) {
            postMessage("请输入验证码");
            return false;
        }
        if (smsCode.get().length() != 6) {
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


    public void checkSmsCodeAndPassword(int type) {
        if (!validateSmsCode()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }

        new RegisterModel().checkSmsCodeAndPassword(phone.get(), smsCode.get(), password.get(), type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> register(),
                        throwable -> postMessage(ErrorParser.parse(throwable)));
    }

}
