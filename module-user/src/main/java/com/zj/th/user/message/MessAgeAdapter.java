package com.zj.th.user.message;

import androidx.annotation.NonNull;
import android.view.ViewGroup;

import com.zj.th.base.common.BaseRecyclerViewAdapter;
import com.zj.th.base.common.BaseRecyclerViewHolder;
import com.zj.th.data.remote.message.MessageBean;
import com.zj.th.user.R;


public class MessAgeAdapter extends BaseRecyclerViewAdapter<MessageBean> {
    private final static int TIME = 1;
    private final static int MESSAGE = 2;

    public MessAgeAdapter(){

    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TIME){
            return new MessageTimeViewHolder(parent,
                    R.layout.user_message_item_time_layout);
        }else{
            return new MessageListViewHolder(
                    parent,
                    R.layout.user_message_item_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(data.size()!= 0 && data.get(position).getType() == 0){
            return TIME;
        }else{
            return MESSAGE;
        }
    }
}
