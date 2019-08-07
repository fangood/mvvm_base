package com.zj.th;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zj.databinding.mvvm.view.activity.BaseActivity;
import com.zj.th.base.App;
import com.zj.th.base.TokenManager;
import com.zj.th.base.utils.CommonUtil;
import com.zj.th.base.widget.ToastManager;
import com.zj.th.data.UserInfoManager;
import com.zj.th.data.remote.ThApi;
import com.zj.th.data.remote.request.CheckVersionRequest;
import com.zj.th.data.remote.response.CheckVersionResponse;
import com.zj.th.user.login.LoginModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 加载页
 */
@Route(path = "/app/splash")
public class SplashActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SplashActivity";

    private final long ACTIVITY_SHOW_TIME_MILLS = 4000;

    private long mActivityStartTime;
    private Context context;

    @Autowired
    public boolean startManually = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ARouter.getInstance().inject(this);

        if (!isTaskRoot() && !startManually) {
            finish();
            return;
        }

        mActivityStartTime = System.currentTimeMillis();

        setContentView(R.layout.activity_splash);

        context = this;

        findViewById(R.id.splash_login).setOnClickListener(this);
        findViewById(R.id.splash_register).setOnClickListener(this);

        showLogo();
        TokenManager.get().disableTokenExpiredHandler();

        /**
         * 核实否需要升级
         * */
        checkVersion();
    }

    private void TodoMain() {
        UserInfoManager.get()
                .loadUserInfo()
                .timeout(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(userInfo -> App.get().setLoginUser(UserInfoManager.get().getOriginPermission()))
                .doOnNext(loginUserDTOApiResult -> new LoginModel().getToken())
                .subscribe(result -> navigationMainAfterShowed(),
                        throwable -> showLogin());
    }

    /**
     * 提示是否升级
     */
    private void checkVersion() {
        ThApi.getBaseService()
                .checkVersion(new CheckVersionRequest(CommonUtil.getVersionName()))
                .subscribeOn(Schedulers.io())
                .doOnNext(result -> result.assertSuccess())
                .doOnNext(result -> result.assertDataNonNull())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    CheckVersionResponse data = result.getData();
                    if (data.getUpdateState() == 1) {
                        // 提示升级
                        showUpGradeDialog(data);
                    } else if (data.getUpdateState() == 2) {
                        // 强制升级
                        showUpGradeDialog(data);
                    } else {
                        TodoMain();
                    }
                }, throwable -> {
                    ToastManager.show(context, "您的网络不给力，请检查网络后重新尝试");
                    TodoMain();
                });
    }

    public void showUpGradeDialog(CheckVersionResponse data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TokenManager.get().enableTokenExpiredHandler();
    }

    private void showLogo() {
        AlphaAnimation alp = new AlphaAnimation(0.0f, 1.0f);
        alp.setDuration(1000);

        View appName = findViewById(R.id.splash_app_name);
        View appNameEn = findViewById(R.id.splash_app_name_en);

        Observable.interval(1, TimeUnit.SECONDS)
                .take(1)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(time -> {
                    appName.setAnimation(alp);
                    appNameEn.setAnimation(alp);
                    appName.setVisibility(View.VISIBLE);
                    appNameEn.setVisibility(View.VISIBLE);
                });
    }

    private void showLogin() {
        AlphaAnimation alp = new AlphaAnimation(0.0f, 1.0f);
        alp.setDuration(1500);

        View view = findViewById(R.id.splash_login_container);

        Observable.interval(2000, TimeUnit.MILLISECONDS)
                .take(1)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(time -> {
                    view.setAnimation(alp);
                    view.setVisibility(View.VISIBLE);
                });
    }

    private void navigationMainAfterShowed() {
        long showed = System.currentTimeMillis() - mActivityStartTime;
        long last = 0;
        if (showed < ACTIVITY_SHOW_TIME_MILLS) {
            last = ACTIVITY_SHOW_TIME_MILLS - showed;
        }
        Observable.interval(last, TimeUnit.MILLISECONDS)
                .take(1)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(time -> navigationMain(),
                        throwable -> navigationMain());
    }

    private void navigationMain() {
        ARouter.getInstance()
                .build("/app/main")
                .navigation(this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        finish();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.splash_login:
                ARouter.getInstance().build("/user/Login")
                        .navigation();
                break;
            case R.id.splash_register:
                ARouter.getInstance().build("/user/Register")
                        .navigation();
                break;
        }
    }
}
