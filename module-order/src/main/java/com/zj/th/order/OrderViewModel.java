package com.zj.th.order;

import android.annotation.SuppressLint;

import com.zj.th.base.viewmodel.BaseViewModel;
import com.zj.th.data.remote.request.OrderMainRequest;


public class OrderViewModel extends BaseViewModel {

    @SuppressLint("CheckResult")
    public void getOrderList(OrderMainRequest orderMainRequest) {
        new OrderModel()
                .getProductOrSupplierOrder(orderMainRequest)
                .subscribe(result -> {
                            if (!result.isResult()) {
                                errorMessage.setValue(result.getMessage());
                                return;
                            }
                            successData.setValue(result);

                        },
                        throwable -> {
                            errorMessage.setValue(throwable.getMessage());
                        });
    }

}
