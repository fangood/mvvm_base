package com.zj.th.order;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * 接活页面ViewModel
 *
 */
public class PickUpOrderViewModel extends ViewModel {

    public final ObservableField<String> positionName = new ObservableField<>();
    public final ObservableField<String> keyword = new ObservableField<>();

    public void setKeyword(String keyword) {
        this.keyword.set(keyword);
    }
}

