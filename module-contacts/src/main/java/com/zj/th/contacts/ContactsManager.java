package com.zj.th.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.MainThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class ContactsManager {

    private static ContactsManager mInstance;
    private final MutableLiveData<List<ContactTab>> mContacts = new MutableLiveData<>();

    private ContactsManager() {
        mContacts.postValue(new ArrayList<>());
    }

    public static ContactsManager get() {
        if (mInstance == null) {
            mInstance = new ContactsManager();
        }
        return mInstance;
    }

    public LiveData<List<ContactTab>> getContacts() {
        if (mContacts.getValue() == null || mContacts.getValue().size() == 0) {
            syncContacts();
        }
        return mContacts;
    }

    @MainThread
    public void setContacts(List<ContactTab> contacts) {
        mContacts.setValue(contacts);
    }

    public void postContacts(List<ContactTab> contacts) {
        mContacts.postValue(contacts);
    }

    private boolean synchronizing = false;

    public void syncContacts() {
        if (synchronizing) {
            return;
        }
        synchronizing = true;
        //获取好友列表
        new ContactsModel().getFriendsList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {

                            mContacts.getValue().clear();
                            List<ContactTab> tabList = new ArrayList<>();
                            Set<String> titles = result.getData().keySet();
                            for (String title : titles) {
                                tabList.add(new ContactTab(title, result.getData().get(title)));
                            }
                            setContacts(tabList);

                            synchronizing = false;
                        },
                        throwable -> {
                            mContacts.getValue().clear();
                            synchronizing = false;
                        });
    }


}
