package com.zj.th.contacts.utils;

public class Wrapper<T> {

    private T item;
    private boolean first;
    private boolean buttonShow;
    private char firstLetter;

    public Wrapper(T item,  boolean buttonShow,boolean first, char firstLetter) {
        this.item = item;
        this.first = first;
        this.buttonShow = buttonShow;
        this.firstLetter = firstLetter;
    }

    public T getItem() {
        return item;
    }

    protected void setItem(T item) {
        this.item = item;
    }

    public boolean isFirst() {
        return first;
    }

    protected void setFirst(boolean first) {
        this.first = first;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    protected void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }

    public boolean isButtonShow() {
        return buttonShow;
    }

    public void setButtonShow(boolean buttonShow) {
        this.buttonShow = buttonShow;
    }
}