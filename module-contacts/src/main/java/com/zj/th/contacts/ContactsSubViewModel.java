package com.zj.th.contacts;

import androidx.lifecycle.ViewModel;

import android.widget.Filter;
import android.widget.Filterable;

import com.zj.databinding.RefillableObservableArrayList;
import com.zj.th.contacts.utils.LetterIndex;
import com.zj.th.contacts.utils.Wrapper;
import com.zj.th.data.remote.contacts.FriendBean;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * BR自动生成
 */
public class ContactsSubViewModel extends ViewModel implements Filterable {

    public final ItemBinding itemBinding = ItemBinding.of(BR.viewModel, R.layout.contacts_item_contacts_sub);
    public final RefillableObservableArrayList<ContactsSubItemViewModel> items = new RefillableObservableArrayList<>();
    public List<ContactsSubItemViewModel> dataSource;

    private LetterIndex<FriendBean> mLetterIndex;

    private Filter mFilter;

    public void setFriends(boolean buttonShow,List<FriendBean> friends) {
        mLetterIndex = new LetterIndex<>(buttonShow,friends, item -> item.getFriendName());
        List<Wrapper<FriendBean>> sorted = mLetterIndex.getSortedItems();
        List<ContactsSubItemViewModel> list = new ArrayList<>();
        for (Wrapper<FriendBean> item : sorted) {
            list.add(new ContactsSubItemViewModel(item));
        }
        dataSource = list;
        items.refill(list);
    }

    public int getLetterIndex(Character letter) {
        return mLetterIndex.getLetterIndex(letter);
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence keyword) {
                    List<ContactsSubItemViewModel> data = new ArrayList<>();
                    if (dataSource != null) {
                        for (ContactsSubItemViewModel viewModel : dataSource) {
                            if (viewModel.getFriend().getFriendName().contains(keyword)
                                    || viewModel.getFriend().getTelephone().contains(keyword)) {
                                data.add(viewModel);
                            }
                        }
                    }
                    FilterResults result = new FilterResults();
                    result.values = data;
                    result.count = data.size();
                    return result;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    items.refill((List<ContactsSubItemViewModel>) results.values);
                }
            };
        }
        return mFilter;
    }
}
