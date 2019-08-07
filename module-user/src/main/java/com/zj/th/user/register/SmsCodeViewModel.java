package com.zj.th.user.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import com.zj.databinding.mvvm.viewmodel.Command;
import com.zj.databinding.mvvm.viewmodel.SingleLiveEvent;
import com.zj.th.base.utils.Validator;
import com.zj.th.base.viewmodel.AppViewModel;
import com.zj.th.data.remote.ErrorParser;
import com.zj.th.data.remote.request.PostCodeRequest;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SmsCodeViewModel extends AppViewModel {

    public final ObservableField<String> phone = new ObservableField<>();
    public final ObservableField<String> smsCode = new ObservableField<>();
    public final ObservableField<String> smsCodeText = new ObservableField<>("获取验证码");

    private final SingleLiveEvent<Void> smsCodeSendSuccess = new SingleLiveEvent<>();
    private final SingleLiveEvent<Void> smsCodeCheckSuccess = new SingleLiveEvent<>();

    private int smsCodeType;

    public SmsCodeViewModel(int smsCodeType) {
        this.smsCodeType = smsCodeType;
    }

    private boolean canPostCode = true;

    public Command clearPhoneCommand = () -> phone.set("");

    public Command postCodeCommand = () -> {
        if (!canPostCode) {
            return;
        }
        if (!validatePhone()) {
            return;
        }
        new SmsCodeModel().postCode(phone.get(), smsCodeType)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> postProgressStart())
                .doFinally(() -> postProgressDone())
                .subscribe(result -> {
                            postMessage("验证码发送成功");
                            smsCodeSendSuccess.call();
                            postCodeCountDown();
                        },
                        throwable -> postMessage(ErrorParser.parse(throwable)));
    };

    private Disposable mCountDownDisposable;

    private void postCodeCountDown() {
        canPostCode = false;
        // 倒计时60秒
        mCountDownDisposable = Observable.interval(1, TimeUnit.SECONDS)
                .take(60)
                .map(time -> 59 - time)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(time -> smsCodeText.set(time == 0 ? "重新获取" : time + "秒"),
                        throwable -> throwable.printStackTrace(),
                        () -> canPostCode = true);
    }




    public void reset() {
        if (mCountDownDisposable != null && !mCountDownDisposable.isDisposed()) {
            mCountDownDisposable.dispose();
        }
        smsCodeText.set("获取验证码");
        canPostCode = true;
    }

    private boolean validatePhone() {
        if (!Validator.isMobileNO(phone.get())) {
            postMessage("请输入正确的手机号");
            return false;
        }
        return true;
    }

    public void checkSmsCode() {

        if (!validateSmsCode()) {
            return;
        }

        new SmsCodeModel().checkSmsCode(phone.get(), smsCode.get(), smsCodeType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> smsCodeCheckSuccess.call(),
                        throwable -> postMessage(ErrorParser.parse(throwable)));
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


    public LiveData<Void> getSmsCodeSendSuccess() {
        return smsCodeSendSuccess;
    }

    public SingleLiveEvent<Void> getSmsCodeCheckSuccess() {
        return smsCodeCheckSuccess;
    }

    public static class Factory implements ViewModelProvider.Factory {

        private int smsCodeType;

        public Factory(@PostCodeRequest.SmsCodeType int smsCodeType) {
            this.smsCodeType = smsCodeType;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (SmsCodeViewModel.class.isAssignableFrom(modelClass)) {
                return (T) new SmsCodeViewModel(smsCodeType);
            }
            return null;
        }
    }

}
