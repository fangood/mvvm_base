package com.zj.th.base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zj.databinding.mvvm.view.BaseLifecycleRegistry;
import com.zj.th.base.activity.BaseActivityLifecycleObserver;
import com.zj.th.base.fragment.BaseFragmentLifecycleObserver;
import com.zj.th.base.utils.ACache;
import com.zj.th.base.utils.CommonUtil;
import com.zj.th.base.utils.L;
import com.zj.th.base.widget.ToastManager;
import com.zj.th.data.remote.BuildConfig;
import com.zj.th.data.remote.ErrorParser;
import com.zj.th.data.remote.ThApi;
import com.zj.th.data.remote.user.LoginUserDTO;
import com.zj.th.data.remote.user.LzbPositionDto;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;


public class App extends Application {
    private static final String TAG = "App";
    private static App mApplication;
    /**
     * 用户当前角色
     */
    private final MutableLiveData<Role> mCurrentRole = new MutableLiveData<>();
    private final MutableLiveData<LzbPositionDto> mCurrentPosition = new MutableLiveData<>();
    private final MutableLiveData<LoginUserDTO> mLoginUserInfo = new MutableLiveData<>();

    public static App get() {
        return mApplication;
    }

    public App() {
        super();
        mApplication = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {

            String filePath = "/com/th/log/";
            String fileName = "error_log.txt";

            File folder = new File(filePath);
            if(!folder.exists()|| !folder.isDirectory()){
                folder.mkdirs();
            }
            File file = new File(folder, fileName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e1) {
                    L.e("日志文件创建失败");
                }
            }

            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            //上传日志文件
            L.e("Application","==================="+getErrorInfo(e));
        });

        // 观测BaseActivity和BaseFragment的生命周期
        BaseLifecycleRegistry.addActivityLifecycleObserver(new BaseActivityLifecycleObserver());
        BaseLifecycleRegistry.addFragmentLifecycleObserver(new BaseFragmentLifecycleObserver());

        RxJavaPlugins.setErrorHandler(throwable ->
                Observable.just(ErrorParser.parse(throwable))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(message -> ToastManager.show(this, message)));

        // 初始化Api
        ThApi.init(BuildConfig.DEBUG, CommonUtil.getDeviceId(this),
                TokenManager.get(), TokenManager.get());
        // 初始化ARouter
        initARouter();

        setLoginUser((LoginUserDTO) ACache.get(this).getAsObject("user"));

        registerActivityLifecycleCallbacks(new LifecycleLog(new LifecycleLog.OnAppBackListener() {
            @Override
            public void onFront() {  //前台
                /**上传日志*/
                uploadLogs();
                Log.e(TAG, "onBack:   前台 --处理日志上传信息");
            }
            @Override
            public void onBack() {//后台 --处理日志上传信息
                /**上传日志*/
                uploadLogs();
                Log.e(TAG, "onBack:   后台 --处理日志上传信息");
            }
        }));
    }
    private String getErrorInfo(Throwable arg1) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        arg1.printStackTrace(pw);
        pw.close();
        String error = writer.toString();
        return error;
    }

    /**
     * 上传到阿里云等
     */
    private void uploadLogs() {
        String filePath = Environment.getExternalStorageDirectory() + "/com/th/log/";

    }

    protected void initARouter() {
        if (BuildConfig.DEBUG) {
            // 开启日志
            ARouter.openLog();
            // 使用InstantRun的时候，需要打开该开关，上线之后关闭，否则有安全风险
            ARouter.openDebug();
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
    }

    /**
     * 获取用户的当前角色
     *
     * @return 当前角色
     */
    public LiveData<Role> getLiveCurrentRole() {
        return mCurrentRole;
    }

    /**
     * 获取用户的当前岗位
     *
     * @return 当前角色
     */
    public LiveData<LzbPositionDto> getLiveCurrentPosition() {
        return mCurrentPosition;
    }

    /**
     * 获取用户的当前角色
     *
     * @return 当前角色
     */
    public Role getCurrentRole() {
        return mCurrentRole.getValue();
    }

    /**
     * 获取用户的当前角色
     *
     * @return 当前角色
     */
    public LzbPositionDto getCurrentPosition() {
        return mCurrentPosition.getValue();
    }

    /**
     * 设定用户的当前角色
     */
    private void setCurrentRole(Role role) {
        if (role == null) {
            return;
        }
        mCurrentRole.setValue(role);
    }

    /**
     * 设定用户的当前岗位
     */
    @MainThread
    public void setCurrentPosition(@NonNull LzbPositionDto position) {
        if (position == null) {
            return;
        }
        if (position.equals(mCurrentPosition.getValue())) {
            return;
        }
        setCurrentRole(Role.getByOrderType(position.getOrderTypes()));
        mCurrentPosition.setValue(position);
    }

    @MainThread
    public void setLoginUser(@NonNull LoginUserDTO user) {
        if (user == null) {
            return;
        }
        mLoginUserInfo.setValue(user);
        if (user.getPosition() != null) {
            setCurrentPosition(user.getPosition());
        } else {
            setCurrentPosition(user.getSubPosition().get(0));
        }
        ACache.get(this).put("user", user);
    }

    public LoginUserDTO getLoginUser() {
        return mLoginUserInfo.getValue();
    }

    public LiveData getLiveLoginUser() {
        return mLoginUserInfo;
    }


}
