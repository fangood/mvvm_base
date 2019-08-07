package com.zj.databinding.bindingadapters;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.adapters.ListenerUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.zj.databinding.adapter.FragmentPagerAdapter;
import com.zj.databinding.mvvm.R;

import java.util.List;


@BindingMethods({
        @BindingMethod(type = ViewPager.class, attribute = "offscreenPageLimit", method = "setOffscreenPageLimit"),
        @BindingMethod(type = ViewPager.class, attribute = "onPageChange", method = "setOnPageChangeListener")
})
public class ViewPagerBindingAdapter {

    @BindingAdapter(value = {"fragmentManager", "fragments", "pageTitles"}, requireAll = false)
    public static void setFragments(ViewPager viewPager, FragmentManager fragmentManager, List<Fragment> fragments, List<CharSequence> pageTitles) {
        if (fragmentManager == null) {
            throw new IllegalArgumentException("fragmentManager must not be null");
        }
        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager, fragments, pageTitles));
    }

    @BindingAdapter("currentItem")
    public static void setCurrentItem(ViewPager viewPager, int index) {
        final int oldIndex = viewPager.getCurrentItem();
        if (index == oldIndex) {
            return;
        }
        viewPager.setCurrentItem(index);
    }

    @InverseBindingAdapter(attribute = "currentItem", event = "currentItemAttrChanged")
    public static int getCurrentItemInt(ViewPager view) {
        return view.getCurrentItem();
    }

    @BindingAdapter(value = {"onPageScrolled", "onPageSelected",
            "onPageScrollStateChanged", "currentItemAttrChanged"}, requireAll = false)
    public static void addOnPageChangeListener(ViewPager view, final onPageScrolled pageScrolled,
                                               final onPageSelected pageSelected, final onPageScrollStateChanged pageScrollStateChanged,
                                               final InverseBindingListener currentItemAttrChanged) {
        final ViewPager.OnPageChangeListener newValue;
        if (pageScrolled == null && pageSelected == null && pageScrollStateChanged == null && currentItemAttrChanged == null) {
            newValue = null;
        } else {
            newValue = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (pageScrolled != null) {
                        pageScrolled.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    if (pageSelected != null) {
                        pageSelected.onPageSelected(position);
                    }
                    if (currentItemAttrChanged != null) {
                        currentItemAttrChanged.onChange();
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (pageScrollStateChanged != null) {
                        pageScrollStateChanged.onPageScrollStateChanged(state);
                    }
                }
            };
        }
        final ViewPager.OnPageChangeListener oldValue = ListenerUtil.trackListener(view, newValue, R.id.onPageChangeListener);
        if (oldValue != null) {
            view.removeOnPageChangeListener(oldValue);
        }
        if (newValue != null) {
            view.addOnPageChangeListener(newValue);
        }
    }

    public interface onPageScrolled {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    }

    public interface onPageSelected {
        void onPageSelected(int position);
    }

    public interface onPageScrollStateChanged {
        void onPageScrollStateChanged(int state);
    }
}
