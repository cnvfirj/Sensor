package com.kittendevelop.sensor.ui.main.views.gyroscope;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GyroscopeViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Application mApplication;
    private GyroscopeModel mModel;


    public GyroscopeViewModelFactory(@NonNull Application application, GyroscopeModel mModel) {
        this.mApplication = application;
        this.mModel = mModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass==GyroscopeViewModel.class)return (T)new GyroscopeViewModel(mApplication,mModel);
        return null;
    }
}
