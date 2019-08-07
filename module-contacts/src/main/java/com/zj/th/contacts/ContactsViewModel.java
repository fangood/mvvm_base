package com.zj.th.contacts;

import androidx.lifecycle.ViewModel;
import androidx.databinding.ObservableField;


public class ContactsViewModel extends ViewModel {

    public ObservableField<String> keyword = new ObservableField<>();

    public ContactsViewModel() {
        ContactsManager.get().syncContacts();
    }
}
