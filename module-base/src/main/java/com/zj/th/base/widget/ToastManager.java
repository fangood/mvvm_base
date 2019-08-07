package com.zj.th.base.widget;

import android.content.Context;

import androidx.annotation.NonNull;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zj.databinding.mvvm.utils.ThreadUtil;
import com.zj.th.base.R;

public class ToastManager {

    public static int LENGTH_LONG = android.widget.Toast.LENGTH_LONG;
    public static int LENGTH_SHORT = android.widget.Toast.LENGTH_SHORT;

    public static void show(@NonNull Context context, CharSequence msg) {
        show(context, msg, LENGTH_LONG);
    }

    public static void show(@NonNull Context context, CharSequence msg, int duration) {
        Context con = context.getApplicationContext();
        ThreadUtil.runOnMainThread(() -> internalShow(con, msg, duration));
    }

    private static void internalShow(@NonNull Context context, CharSequence msg, int duration) {
        Toast rToast = new android.widget.Toast(context.getApplicationContext());

        TextView view = (TextView) View.inflate(context, R.layout.toast_basic, null);

        view.setText(msg);

        int yOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, context.getResources().getDisplayMetrics());

        rToast.setView(view);
        rToast.setGravity(Gravity.TOP, 0, yOffset);
        rToast.setDuration(duration);
        rToast.show();
    }

}
