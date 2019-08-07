package com.zj.databinding.mvvm.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Observable;

import java.io.Serializable;

/**
 * 无论{@link #mValue }是否改变，都会触发
 * {@link OnPropertyChangedCallback#onPropertyChanged(Observable, int)}
 * 和{@link OnEventCallback#onEvent(ObservableEvent, int)}
 * 方法的Observable对象
 *
 * @param <T>
 */
public class ObservableEvent<T> extends BaseObservable implements Serializable {

    protected T mValue;

    public ObservableEvent() {
    }

    public ObservableEvent(T value) {
        mValue = value;
    }

    public void trigger(T value) {
        mValue = value;
        notifyChange();
    }

    public void trigger() {
        trigger(get());
    }

    public T get() {
        return mValue;
    }

    public void addOnEventCallback(final OnEventCallback<T> callback) {
        addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                callback.onEvent((ObservableEvent<T>) sender, propertyId);
            }
        });
    }

    public interface OnEventCallback<T> {
        /**
         * 事件触发的回调
         *
         * @param sender
         * @param propertyId
         */
        void onEvent(ObservableEvent<T> sender, int propertyId);
    }

}
