package com.zj.th.user.message;

import android.view.ViewGroup;

import com.zj.th.base.common.BaseRecyclerViewHolder;
import com.zj.th.data.remote.message.MessageBean;
import com.zj.th.user.databinding.UserMessageItemTimeLayoutBinding;


public class MessageTimeViewHolder extends BaseRecyclerViewHolder<MessageBean, UserMessageItemTimeLayoutBinding>{

    public MessageTimeViewHolder(ViewGroup viewGroup, int layoutId){
        super(viewGroup,layoutId);
    }

    @Override
    public void onBindViewHolder(MessageBean object, int position) {
        binding.messageTime.setText(object.getMpSendTime());
    }
}
