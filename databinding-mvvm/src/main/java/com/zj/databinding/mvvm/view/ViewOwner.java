package com.zj.databinding.mvvm.view;

import androidx.databinding.ViewDataBinding;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

/**
 * MVVM中View层接口
 *
 * @param <VDB> type of ViewDataBinding
 */
public interface ViewOwner<VDB extends ViewDataBinding> {

    /**
     * 获取View层布局的layout res
     *
     * @return layout res
     */
    @LayoutRes
    int getContentLayoutId();

    /**
     * Binding被创建后调用该方法
     *
     * @param binding
     */
    void onBindingCreated(@NonNull VDB binding);

    /**
     * 通过DataBinding给 {@link #getContentLayoutId()}绑定ViewModel
     *
     * @param binding
     */
    void bindViewModels(@NonNull VDB binding);

    /**
     * 注册View中的操作交互事件
     * 将View的操作，反馈给ViewModel
     * 实现Command
     *
     * @param binding
     */
    void registerViewEvents(@NonNull VDB binding);

    /**
     * 订阅ViewModel中的数据变化
     * 实现Notifications
     */
    void subscribeViewModelChanges();

    /**
     * 获取View的数据绑定
     *
     * @return
     */
    VDB getBinding();
}
