package com.zj.databinding.bindingadapters;

import androidx.databinding.BindingAdapter;
import androidx.annotation.DrawableRes;
import android.view.View;

import com.zj.databinding.mvvm.viewmodel.Command;


public class ViewBindingAdapter {

    @BindingAdapter("clickCommand")
    public static void setClickCommand(View view, final Command command) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (command != null) {
                    command.execute();
                }
            }
        });
    }

    @BindingAdapter("android:background")
    public static void setBackground(View view, @DrawableRes int background) {
        view.setBackgroundResource(background);
    }

}
