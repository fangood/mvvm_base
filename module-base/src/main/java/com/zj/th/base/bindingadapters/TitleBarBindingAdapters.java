package com.zj.th.base.bindingadapters;

import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

import com.zj.th.base.widget.TitleBar;

@BindingMethods({
        @BindingMethod(type = TitleBar.class, attribute = "leftText", method = "setLeftText"),
        @BindingMethod(type = TitleBar.class, attribute = "centerText", method = "setCenterText"),
        @BindingMethod(type = TitleBar.class, attribute = "rightText", method = "setRightText")
})
public class TitleBarBindingAdapters {

}
