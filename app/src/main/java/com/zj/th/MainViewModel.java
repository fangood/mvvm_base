package com.zj.th;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainViewModel extends ViewModel {

    public final ObservableList<Tab> tabs = new ObservableArrayList<>();

    public MainViewModel() {
        tabs.addAll(getTabs());
    }

    private List<Tab> getTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab("/order/pickUpOrder", "接单", R.drawable.ic_pick_up_selector));
        tabs.add(new Tab("/order/orderList", "订单", R.drawable.ic_order_selector));
        tabs.add(new Tab("/user/message", "消息", R.drawable.ic_msg_selector));
        tabs.add(new Tab("/contacts/contacts", "通讯录", R.drawable.ic_contacts_selector));
        tabs.add(new Tab("/user/userCenter", "个人中心", R.drawable.ic_user_center_selector));
        return tabs;
    }
}
