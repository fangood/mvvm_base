package com.zj.th.user.message;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zj.databinding.mvvm.view.fragment.MvvmFragment;
import com.zj.th.base.common.OnItemClickListener;
import com.zj.th.base.utils.L;
import com.zj.th.base.widget.ToastManager;
import com.zj.th.data.remote.message.MessageBean;
import com.zj.th.user.R;
import com.zj.th.user.databinding.UserFragmentMessageBinding;
import com.zj.th.user.utils.CollectionUtil;

/**
 * V1.0
 */
@Route(path = "/user/message")
public class MessageFragment extends MvvmFragment<UserFragmentMessageBinding> {

    private MessAgeAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewDataBinding().getMessages().refresh();
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.user_fragment_message;
    }

    @Override
    public void bindViewModels(@NonNull UserFragmentMessageBinding binding) {
        MessageViewModel messageViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        binding.setMessages(messageViewModel);
        /**一键已读*/
        binding.titleBar.setRightClickListener(v -> readMessage());
    }

    @Override
    public void registerViewEvents(@NonNull UserFragmentMessageBinding binding) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);

        binding.recyclerView.setLoadingMoreEnabled(true);
        binding.recyclerView.setPullRefreshEnabled(true);
        binding.recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                getViewDataBinding().getMessages().refresh();
            }

            @Override
            public void onLoadMore() {
                getViewDataBinding().getMessages().loadMore();
            }
        });
        //设置item间距，30dp
//        binding.recyclerView.addItemDecoration();
        adapter = new MessAgeAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener<MessageBean>() {
            @Override
            public void onClick(MessageBean messageBean, int position) {
                L.i("MessageFragment", "onClick: " + position);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void subscribeViewModelChanges() {
        getViewDataBinding().getMessages().messageListBean
                .observe(this, messageBeans -> {
                    if (CollectionUtil.isEmpty(messageBeans)) {
                        return;
                    }
                    adapter.clear();
                    adapter.addAll(messageBeans);
                    adapter.notifyDataSetChanged();
                    getViewDataBinding().executePendingBindings();
                });

        getViewDataBinding().getMessages().getRefreshComplete().observe(this, mRefreshCompleteObserver);
        getViewDataBinding().getMessages().getLoadMoreComplete().observe(this, mLoadMoreCompleteObserver);
        getViewDataBinding().getMessages().getMessage().observe(this, mMessageObserver);

    }

    private Observer<String> mMessageObserver = message -> ToastManager.show(getContext(), message);
    private Observer<Void> mRefreshCompleteObserver = aVoid -> getViewDataBinding().recyclerView.refreshComplete();
    private Observer<Void> mLoadMoreCompleteObserver = aVoid -> getViewDataBinding().recyclerView.loadMoreComplete();

    private void readMessage() {
    }
}
