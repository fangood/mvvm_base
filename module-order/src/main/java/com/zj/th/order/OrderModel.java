package com.zj.th.order;


import com.zj.th.data.remote.ThApi;
import com.zj.th.data.remote.order.OrderMainBean;
import com.zj.th.data.remote.request.OrderMainRequest;
import com.zj.th.data.remote.response.ApiResult;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderModel {

    public Observable<ApiResult<OrderMainBean>> getProductOrSupplierOrder(OrderMainRequest orderMainRequest) {
        return ThApi.getDecorationService()
                .getProductOrSupplierOrder(orderMainRequest)
                .subscribeOn(Schedulers.io())
                // 在io线程中执行回调
                .observeOn(AndroidSchedulers.mainThread());
    }

}
