package com.zj.th.user.register;

import android.text.InputType;

import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;


public class PasswordViewModel extends ViewModel {

    public final ObservableBoolean visible = new ObservableBoolean(false);
    public final ObservableInt inputType = new ObservableInt(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

    public PasswordViewModel() {
        visible.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                inputType.set(InputType.TYPE_CLASS_TEXT | (visible.get()
                        ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        : InputType.TYPE_TEXT_VARIATION_PASSWORD));
            }
        });
    }

}
