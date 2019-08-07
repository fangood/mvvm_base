package com.zj.th.contacts;

import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableField;

import com.zj.th.contacts.utils.Wrapper;
import com.zj.th.data.remote.contacts.FriendBean;


public class ContactsSubItemViewModel {

    public final ObservableField<String> nameLabel = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> phone = new ObservableField<>();
    public final ObservableField<String> letter = new ObservableField<>();

    public final ObservableField<Integer> titleVisibility = new ObservableField<>(View.GONE);
    public final ObservableField<Integer> buttonVisibility = new ObservableField<>(View.GONE);

    private FriendBean friend;

    public ContactsSubItemViewModel(Wrapper<FriendBean> wrapper) {
        this.friend = wrapper.getItem();
        nameLabel.set(TextUtils.isEmpty(friend.getFriendName()) ? " " : friend.getFriendName().substring(0, 1));
        name.set(friend.getFriendName());
        phone.set(friend.getTelephone());
        titleVisibility.set(wrapper.isFirst() ? View.VISIBLE : View.GONE);
        buttonVisibility.set(friend.isCanDelegate() ? View.VISIBLE : View.GONE);
        letter.set(wrapper.getFirstLetter() + "");
    }

    public FriendBean getFriend() {
        return friend;
    }

    public void setFriend(FriendBean friend) {
        this.friend = friend;
    }
}
