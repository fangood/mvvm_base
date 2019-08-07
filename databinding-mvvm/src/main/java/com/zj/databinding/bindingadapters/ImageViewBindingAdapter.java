package com.zj.databinding.bindingadapters;

import androidx.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class ImageViewBindingAdapter {

    @BindingAdapter("res")
    public static void setImageRes(ImageView imageView, @DrawableRes int res) {
        Glide.with(imageView.getContext())
                .load(res)
                .crossFade()
                .into(imageView);
    }

    @BindingAdapter("bitmap")
    public static void setImageRes(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter(value = {"url", "placeholder", "error", "onSuccess", "onFailure"}, requireAll = false)
    public static void setImageUrl(final ImageView imageView, String url,
                                   Drawable placeholder,
                                   Drawable error,
                                   final ImageLoadSuccessListener onSuccess,
                                   final ImageLoadFailureListener onFailure) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .error(error)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        e.printStackTrace();
                        if (onFailure != null) {
                            return onFailure.onImageLoadFailure(e, model, target, isFirstResource);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (onSuccess != null) {
                            return onSuccess.onImageLoadSuccess(resource, model, target, isFromMemoryCache, isFirstResource);
                        }
                        return false;
                    }
                })
                .into(imageView);
    }

    public interface ImageLoadSuccessListener {
        boolean onImageLoadSuccess(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource);
    }

    public interface ImageLoadFailureListener {
        boolean onImageLoadFailure(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource);
    }
}
