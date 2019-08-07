package com.zj.th;

import androidx.annotation.DrawableRes;

/**
 * 主界面tab
 *
 */
public class Tab {

    private String fragment;
    private String title;
    private int icon;

    public Tab(String fragment, String title,@DrawableRes int icon) {
        this.fragment = fragment;
        this.title = title;
        this.icon = icon;
    }

    public String getFragment() {
        return fragment;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }
}