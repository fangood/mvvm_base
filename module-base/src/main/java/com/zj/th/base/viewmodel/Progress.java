package com.zj.th.base.viewmodel;

/**
 */
public class Progress {

    public static final String DEFAULT_TAG = "";

    private String tag = DEFAULT_TAG;
    private String message = "";

    public Progress(String tag, String message) {
        this.tag = tag;
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
