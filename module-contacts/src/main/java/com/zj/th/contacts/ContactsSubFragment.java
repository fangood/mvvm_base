package com.zj.th.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zj.databinding.mvvm.view.fragment.MvvmFragment;
import com.zj.th.base.widget.LetterIndexView;
import com.zj.th.base.widget.ThProgressDialog;
import com.zj.th.contacts.databinding.ContactsFragmentContactsSubBinding;
import com.zj.th.data.remote.contacts.FriendBean;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;


@Route(path = "/contacts/contacts/sub")
public class ContactsSubFragment extends MvvmFragment<ContactsFragmentContactsSubBinding> {

    ThProgressDialog thWeeksProgressDialog;
    @Autowired
    public List<FriendBean> friends;

    @Autowired
    public boolean buttonShow;

    private String keyword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        thWeeksProgressDialog = new ThProgressDialog(getContext());
        ARouter.getInstance().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onBindingCreated(@NonNull ContactsFragmentContactsSubBinding binding) {
        binding.contactsSubRecycler.setAdapter(new BindingRecyclerViewAdapter<ContactsSubItemViewModel>() {
            @Override
            public void onBindBinding(ViewDataBinding binding, int variableId, int layoutRes, int position, ContactsSubItemViewModel item) {
                super.onBindBinding(binding, variableId, layoutRes, position, item);

                binding.getRoot().setOnClickListener(v -> {

                });
            }
        });
        super.onBindingCreated(binding);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.contacts_fragment_contacts_sub;
    }

    @Override
    public void bindViewModels(@NonNull ContactsFragmentContactsSubBinding binding) {
        ContactsSubViewModel viewModel = ViewModelProviders.of(this).get(ContactsSubViewModel.class);
        viewModel.setFriends(buttonShow, friends);
        if (keyword != null) {
            viewModel.getFilter().filter(keyword);
        }
        binding.setViewModel(viewModel);
    }

    @Override
    public void registerViewEvents(@NonNull ContactsFragmentContactsSubBinding binding) {
        binding.contactsSubLetterIndex.setOnTouchingLetterChangedListener(new LetterIndexView.OnTouchingLetterChangedListener() {
            @Override
            public void onHit(String letter) {
                getViewDataBinding().contactsSubHitLetter.setText(letter);
                getViewDataBinding().contactsSubHitLetter.setVisibility(View.VISIBLE);
                int index = getViewDataBinding().getViewModel().getLetterIndex(letter.charAt(0));
                if (index >= 0) {
                    LinearLayoutManager manager = (LinearLayoutManager) getViewDataBinding().contactsSubRecycler.getLayoutManager();
                    manager.scrollToPositionWithOffset(index, 0);
                }
            }

            @Override
            public void onCancel() {
                getViewDataBinding().contactsSubHitLetter.setVisibility(View.GONE);
            }
        });
        binding.contactsSubRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = manager.findFirstVisibleItemPosition();
                BindingRecyclerViewAdapter<ContactsSubItemViewModel> adapter = (BindingRecyclerViewAdapter<ContactsSubItemViewModel>) recyclerView.getAdapter();
                if (adapter.getItemCount() == 0) {
                    getViewDataBinding().contactsSubLetterLabel.setVisibility(View.INVISIBLE);
                } else {
                    getViewDataBinding().contactsSubLetterLabel.setVisibility(View.VISIBLE);
                    ContactsSubItemViewModel viewModel = adapter.getAdapterItem(position);
                    getViewDataBinding().contactsSubLetterLabel.setText(viewModel.letter.get());
                }
            }
        });
    }

    @Override
    public void subscribeViewModelChanges() {

    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        if (getViewDataBinding() != null && getViewDataBinding().getViewModel() != null) {
            getViewDataBinding().getViewModel().getFilter().filter(keyword);
        }
    }
}
