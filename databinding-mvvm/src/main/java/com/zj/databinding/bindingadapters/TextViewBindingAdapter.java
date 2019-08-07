package com.zj.databinding.bindingadapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;


@BindingMethods({
        @BindingMethod(type = TextView.class, attribute = "textColor", method = "setTextColor")
})
public final class TextViewBindingAdapter {

    private static Drawable getDrawableWithIntrinsicBounds(Context context, @DrawableRes int resId) {
        if (resId == 0) {
            return null;
        }
        Resources res = context.getResources();
        Drawable drawable = res.getDrawable(resId);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        return drawable;
    }

    @BindingAdapter({"android:drawableBottom"})
    public static void setDrawableBottom(TextView view, @DrawableRes int drawable) {
        Drawable[] drawables = view.getCompoundDrawables();
        view.setCompoundDrawables(drawables[0], drawables[1], drawables[2], getDrawableWithIntrinsicBounds(view.getContext(), drawable));
    }

    @BindingAdapter({"android:drawableLeft"})
    public static void setDrawableLeft(TextView view, @DrawableRes int drawable) {
        Drawable[] drawables = view.getCompoundDrawables();
        view.setCompoundDrawables(getDrawableWithIntrinsicBounds(view.getContext(), drawable), drawables[1], drawables[2], drawables[3]);
    }

    @BindingAdapter({"android:drawableRight"})
    public static void setDrawableRight(TextView view, @DrawableRes int drawable) {
        Drawable[] drawables = view.getCompoundDrawables();
        view.setCompoundDrawables(drawables[0], drawables[1], getDrawableWithIntrinsicBounds(view.getContext(), drawable),
                drawables[3]);
    }

    @BindingAdapter({"android:drawableTop"})
    public static void setDrawableTop(TextView view, @DrawableRes int drawable) {
        Drawable[] drawables = view.getCompoundDrawables();
        view.setCompoundDrawables(drawables[0], getDrawableWithIntrinsicBounds(view.getContext(), drawable), drawables[2],
                drawables[3]);
    }

    @BindingAdapter({"android:drawableStart"})
    public static void setDrawableStart(TextView view, @DrawableRes int drawable) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setDrawableLeft(view, drawable);
        } else {
            Drawable[] drawables = view.getCompoundDrawablesRelative();
            view.setCompoundDrawablesRelative(getDrawableWithIntrinsicBounds(view.getContext(), drawable), drawables[1], drawables[2], drawables[3]);
        }
    }

    @BindingAdapter({"android:drawableEnd"})
    public static void setDrawableEnd(TextView view, @DrawableRes int drawable) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setDrawableRight(view, drawable);
        } else {
            Drawable[] drawables = view.getCompoundDrawablesRelative();
            view.setCompoundDrawablesRelative(drawables[0], drawables[1], getDrawableWithIntrinsicBounds(view.getContext(), drawable), drawables[3]);
        }
    }

    @BindingAdapter("android:inputType")
    public static void setInputType(TextView view, int inputType) {
        view.setInputType(inputType);
    }

    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView view, int color) {
        view.setTextColor(color);
    }
}

