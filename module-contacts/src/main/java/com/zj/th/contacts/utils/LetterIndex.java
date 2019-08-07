package com.zj.th.contacts.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.annotations.NonNull;


public class LetterIndex<T> {

    public final static char LETTER_NUMBER = '☆';
    public final static char LETTER_EMPTY = '#';

    public final static char LETTER_NUMBER_VALUE = 0;
    public final static char LETTER_EMPTY_VALUE = 126;

    private List<T> mItems;
    private List<Wrapper<T>> mSortedItems;
    private HashMap<Character, Integer> mLetterIndex = new HashMap<>();

    private Adapter<T> mAdapter;

    boolean buttonShow;

    public LetterIndex( boolean buttonShow,@NonNull List<T> items, Adapter<T> adapter) {
        mItems = items;
        mAdapter = adapter;
        this.buttonShow = buttonShow;
        makeWrappers();
        sortWrappers();
        buildIndex();
    }

    private void makeWrappers() {
        mSortedItems = new ArrayList<>();
        for (T item : mItems) {
            mSortedItems.add(new Wrapper<>(item, buttonShow,false, getFirstLetter(mAdapter.getContent(item))));
        }
    }

    public int getLetterIndex(Character letter) {
        if (mLetterIndex.containsKey(letter)) {
            return mLetterIndex.get(letter);
        }
        return -1;
    }

    public List<Wrapper<T>> getSortedItems() {
        return mSortedItems;
    }

    private char getFirstLetter(String name) {
        if (name != null) {
            name = name.trim();
        }
        if (TextUtils.isEmpty(name)) {
            return LETTER_EMPTY;
        }
        if (name.charAt(0) < '9' && name.charAt(0) > '0') {
            return LETTER_NUMBER;
        }
        String pinYin = PinYin.getPinYin(name);
        pinYin = pinYin.toUpperCase();
        char letter = pinYin.charAt(0);
        if (letter > 'Z' && letter < 'A') {
            return LETTER_NUMBER;
        }
        return letter;
    }

    private void sortWrappers() {
        Collections.sort(mSortedItems, (first, second) -> {
            char fChar = first.getFirstLetter();
            char sChar = second.getFirstLetter();

            if (fChar == LETTER_NUMBER) {
                fChar = LETTER_NUMBER_VALUE;
            }
            if (fChar == LETTER_EMPTY) {
                fChar = LETTER_EMPTY_VALUE;
            }

            if (sChar == LETTER_NUMBER) {
                sChar = LETTER_NUMBER_VALUE;
            }
            if (sChar == LETTER_EMPTY) {
                sChar = LETTER_EMPTY_VALUE;
            }

            return fChar - sChar;
        });
    }

    private void buildIndex() {
        for (int i = 0; i < mSortedItems.size(); i++) {
            Wrapper<T> item = mSortedItems.get(i);
            char letter = item.getFirstLetter();
            if (!mLetterIndex.containsKey(letter)) {
                mLetterIndex.put(letter, i);
                item.setFirst(true);
            } else {
                item.setFirst(false);
            }
        }
    }

    public interface Adapter<T> {
        String getContent(T item);
    }
}