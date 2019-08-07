package com.zj.th.base.widget;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zj.th.base.R;



public class SimpleMessageDialog extends Dialog {

    private TextView mTitle;
    private TextView mContent;
    private TextView mNegative;
    private TextView mPositive;

    private LinearLayout mBtnContainer;
    private View mBtnDivider;

    public SimpleMessageDialog(@NonNull Context context) {
        super(context, R.style.AppTheme_Dialog);

        setContentView(R.layout.dialog_simple_message);

        mTitle = findViewById(R.id.simple_message_title);
        mContent = findViewById(R.id.simple_message_content);
        mNegative = findViewById(R.id.simple_message_negative);
        mPositive = findViewById(R.id.simple_message_positive);

        mBtnContainer = findViewById(R.id.simple_message_btn_container);
        mBtnDivider = findViewById(R.id.simple_message_btn_divider);
    }

    public SimpleMessageDialog setTitle(String message) {
        mTitle.setText(message);
        mTitle.setVisibility(View.VISIBLE);
        return this;
    }

    public SimpleMessageDialog setMessage(String message) {
        mContent.setText(message);
        return this;
    }

    public SimpleMessageDialog setNegativeButton(String text, View.OnClickListener listener) {

        mNegative.setVisibility(View.VISIBLE);
        mBtnContainer.setWeightSum(2);
        mBtnDivider.setVisibility(View.VISIBLE);

        mNegative.setText(text);
        mNegative.setOnClickListener(listener);
        return this;
    }

    public SimpleMessageDialog setPositiveButton(String text, View.OnClickListener listener) {
        mPositive.setText(text);
        mPositive.setOnClickListener(listener);
        return this;
    }
}
