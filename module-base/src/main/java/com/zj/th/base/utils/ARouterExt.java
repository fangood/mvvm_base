package com.zj.th.base.utils;

import android.content.Intent;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;


public class ARouterExt {

    private ARouterExt() {
        super();
    }

    public static void navigation(Fragment fragment, Postcard postcard, int requestCode) {
        LogisticsCenter.completion(postcard);
        Intent intent = new Intent(fragment.getActivity(), postcard.getDestination());
        intent.putExtras(postcard.getExtras());
        fragment.startActivityForResult(intent, requestCode);
    }
}
