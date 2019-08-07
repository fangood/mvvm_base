package com.zj.th.contacts;

import com.zj.th.data.remote.contacts.FriendBean;

import java.io.Serializable;
import java.util.List;



public class ContactTab implements Serializable {

    private String title;
    private List<FriendBean> friends;

    public ContactTab(String title, List<FriendBean> friends) {
        this.title = title;
        this.friends = friends;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FriendBean> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendBean> friends) {
        this.friends = friends;
    }
}
