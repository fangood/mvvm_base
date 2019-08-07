package com.zj.th.data;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zj.th.data.remote.ThApi;
import com.zj.th.data.remote.order.City;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CityManager {

    private static CityManager mInstance;
    private final MutableLiveData<List<City>> mCities = new MutableLiveData<>();
    private final MutableLiveData<City> mCurrentCity = new MutableLiveData<>();

    private CityManager() {
        mCities.postValue(new ArrayList<>());
    }

    public static CityManager get() {
        if (mInstance == null) {
            mInstance = new CityManager();
        }
        return mInstance;
    }

    public List<City> getCities() {
        checkCitiesNonNull();
        return mCities.getValue();
    }

    public LiveData<List<City>> getLiveCities() {
        checkCitiesNonNull();
        return mCities;
    }

    public void setCities(List<City> cities) {
        mCities.setValue(cities);
        if (cities.size() > 0) {
            setCurrentCity(cities.get(0));
        }
    }

    private void checkCitiesNonNull() {
        if (mCities.getValue() == null) {
            loadCities();
        }
    }

    public void setCurrentCity(City city) {
        if (city == null || (mCurrentCity.getValue() != null
                && city.getCityId() == mCurrentCity.getValue().getCityId())) {
            return;
        }
        mCurrentCity.setValue(city);
    }

    public LiveData<City> getLiveCurrentCity() {
        return mCurrentCity;
    }

    public City getCurrentCity() {
        return mCurrentCity.getValue();
    }

    public City queryCityByBaiduCode(String code) {
        for (City city : mCities.getValue()) {
            if (TextUtils.equals(city.getBaiduCode(), code)) {
                return city;
            }
        }
        return null;
    }

    public void loadCities() {
        ThApi.getBaseService()
                .getCity()
                .retry(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(result -> result.assertSuccess())
                .doOnNext(result -> result.assertDataNonNull())
                .subscribe(result -> setCities(result.getData()),
                        throwable -> Log.e("CityManager", "获取城市列表失败", throwable));
    }
}
