package com.zj.databinding;

import java.util.ArrayList;
import java.util.Collection;

import androidx.databinding.ListChangeRegistry;
import androidx.databinding.ObservableList;

/**
 * 可重新填充的ObservableArrayList<p>
 * 主要为了解决RecyclerView刷新数据的时候先clear再addAll,导致的数据连续更新2次，RecyclerView崩溃的问题
 *
 */
public class RefillableObservableArrayList<T> extends ArrayList<T> implements ObservableList<T> {

    private transient ListChangeRegistry mListeners = new ListChangeRegistry();

    @Override
    public void addOnListChangedCallback(OnListChangedCallback listener) {
        if (mListeners == null) {
            mListeners = new ListChangeRegistry();
        }
        mListeners.add(listener);
    }

    @Override
    public void removeOnListChangedCallback(OnListChangedCallback listener) {
        if (mListeners != null) {
            mListeners.remove(listener);
        }
    }

    @Override
    public boolean add(T object) {
        super.add(object);
        notifyAdd(size() - 1, 1);
        return true;
    }

    @Override
    public void add(int index, T object) {
        super.add(index, object);
        notifyAdd(index, 1);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        int oldSize = size();
        boolean added = super.addAll(collection);
        if (added) {
            notifyAdd(oldSize, size() - oldSize);
        }
        return added;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        boolean added = super.addAll(index, collection);
        if (added) {
            notifyAdd(index, collection.size());
        }
        return added;
    }

    /**
     * if this list changed as a result of the call
     *
     * @param collection
     * @return
     */
    public boolean refill(Collection<? extends T> collection) {
        if (collection == null || collection.size() == 0) {
            int oldSize = size();
            clear();
            return oldSize != 0;
        }
        super.clear();
        boolean changed = super.addAll(collection);
        if (changed) {
            mListeners.notifyChanged(this);
        }
        return changed;
    }

    @Override
    public void clear() {
        int oldSize = size();
        super.clear();
        if (oldSize != 0) {
            notifyRemove(0, oldSize);
        }
    }

    @Override
    public T remove(int index) {
        T val = super.remove(index);
        notifyRemove(index, 1);
        return val;
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);
        if (index >= 0) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T set(int index, T object) {
        T val = super.set(index, object);
        if (mListeners != null) {
            mListeners.notifyChanged(this, index, 1);
        }
        return val;
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
        notifyRemove(fromIndex, toIndex - fromIndex);
    }

    private void notifyAdd(int start, int count) {
        if (mListeners != null) {
            mListeners.notifyInserted(this, start, count);
        }
    }

    private void notifyRemove(int start, int count) {
        if (mListeners != null) {
            mListeners.notifyRemoved(this, start, count);
        }
    }
}