package com.zj.th.user.message;

import com.zj.th.data.remote.ThApi;
import com.zj.th.data.remote.message.MessageBean;
import com.zj.th.data.remote.response.ApiJavaResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



public class MessageModel {

        //获取推送平台的token
        public Observable<ApiJavaResult<String>> getPushToken(HashMap<String,String> tokenMap) {
            return ThApi.getBaseService()
//                 调用token接口
                .getPushToken(tokenMap)
//                 在io线程中调用接口
                .subscribeOn(Schedulers.io())
//                 在io线程中执行回调
                .observeOn(AndroidSchedulers.mainThread())
//                 验证接口调用是否成功
                .doOnNext(result ->result.assertSuccess())
                .doOnNext(result ->result.assertDataNonNull());
         }



    //获取消息列表
    public Observable<ApiJavaResult<List<MessageBean>>> getMessages(HashMap<String,String> map) {
       return ThApi.getBaseService()
               .queryMessages(map)
//                在io线程中调用接口
                .subscribeOn(Schedulers.io())
//                 在io线程中执行回调
                .observeOn(AndroidSchedulers.mainThread())
//                 验证接口调用是否成功
                .doOnNext(result ->result.assertSuccess())
                .doOnNext(result ->result.assertDataNonNull());
//
    }

    //更新消息已读
    public Observable<ApiJavaResult<String>> updateReadedMsg(HashMap<String,String> params) {
        return ThApi.getBaseService()
//                 调用token接口
                .updateReadMsg(params)
//                 在io线程中调用接口
                .subscribeOn(Schedulers.io())
//                 在io线程中执行回调
                .observeOn(AndroidSchedulers.mainThread())
//                 验证接口调用是否成功
                .doOnNext(result ->result.assertSuccess())
                .doOnNext(result ->result.assertDataNonNull());
    }

    //删除消息
    public Observable<ApiJavaResult<String>> deletMsg(HashMap<String,String> params) {
        return ThApi.getBaseService()
//                 调用token接口
                .getPushToken(params)
//                 在io线程中调用接口
                .subscribeOn(Schedulers.io())
//                 在io线程中执行回调
                .observeOn(AndroidSchedulers.mainThread())
//                 验证接口调用是否成功
                .doOnNext(result ->result.assertSuccess())
                .doOnNext(result ->result.assertDataNonNull());
    }






    public ArrayList<String> getMessages()
    {
        ArrayList<String> list=new ArrayList<>();
//        for (int i = 0; i <10; i++) {
//            list.add("hello world"+i);
//        }
        return list;
    }

}
