package com.kittendevelop.sensor.ui.main.views.coordinates;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kittendevelop.sensor.ui.main.views.gyroscope.GyroscopeViewModel;

import javax.inject.Inject;

public class CoordinatesViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Application mApplication;
    private CoordinatesModel mModel;


    @Inject
    public CoordinatesViewModelFactory(@NonNull Application mApplication, CoordinatesModel mModel) {
        this.mApplication = mApplication;
        this.mModel = mModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass== CoordinatesViewModel.class)return (T)new CoordinatesViewModel(mApplication,mModel);
        return null;
    }
}
