package com.zj.th.user.message;

import android.view.View;
import android.view.ViewGroup;

import com.zj.th.base.common.BaseRecyclerViewHolder;
import com.zj.th.base.utils.L;
import com.zj.th.data.remote.message.MessageBean;
import com.zj.th.user.R;
import com.zj.th.user.databinding.UserMessageItemLayoutBinding;

public class MessageListViewHolder extends BaseRecyclerViewHolder<MessageBean, UserMessageItemLayoutBinding> {


    public MessageListViewHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
    }

    @Override
    public void onBindViewHolder(final MessageBean messageBean, int position) {
        if (messageBean.getReceiveStatus() == 0) {
            binding.messageIcon.setImageResource(R.mipmap.ic_message_red);
        } else {
            binding.messageIcon.setImageResource(R.mipmap.ic_message_gary);
        }

        binding.messageText1.setText(messageBean.getMpTitle());
        binding.messageText2.setText(messageBean.getMpContent());
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                L.i("getRoot","onClick");
            }
        });
    }
}
