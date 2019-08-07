package com.zj.th.base.widget;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.zj.th.base.R;


public class ThProgressDialog extends Dialog {

    private TextView mMessage;
    private ImageView progress;
    public ThProgressDialog(@NonNull Context context) {
        super(context, R.style.AppTheme_Dialog_Progress);
        setContentView(R.layout.dialog_progress);
        mMessage = findViewById(R.id.progress_message);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public void setMessage(String message) {
        mMessage.setText(TextUtils.isEmpty(message) ? "正在加载..." : message);
    }

    public ThProgressDialog(@NonNull Context context, int resId) {
        super(context, R.style.AppTheme_Dialog_Progress);
        setContentView(R.layout.dialog_lock_progress);
        mMessage = findViewById(R.id.progress_message);
        progress=findViewById(R.id.progress);
        progress.setImageResource(resId);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

}
