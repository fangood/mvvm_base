package com.zj.th.data.remote.service;

import com.zj.th.data.remote.order.OrderMainBean;
import com.zj.th.data.remote.request.OrderMainRequest;
import com.zj.th.data.remote.response.ApiResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface DecorationService {

    String BASE_URL = "Decoration/api/DecorationAppItem/";

    @POST(BASE_URL + "GetMyOrderList")
    Observable<ApiResult<OrderMainBean>> getProductOrSupplierOrder(
            @Body OrderMainRequest request);
}
