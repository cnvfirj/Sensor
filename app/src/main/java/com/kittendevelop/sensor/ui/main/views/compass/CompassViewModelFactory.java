package com.kittendevelop.sensor.ui.main.views.compass;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesViewModel;
import com.kittendevelop.sensor.ui.main.views.gyroscope.GyroscopeViewModel;

import javax.inject.Inject;

public class CompassViewModelFactory  extends ViewModelProvider.NewInstanceFactory {

    private final Application mApplication;
    private final CompassModel mModel;


    @Inject
    public CompassViewModelFactory(Application mApplication, CompassModel mModel) {
        this.mApplication = mApplication;
        this.mModel = mModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass== CompassViewModel.class)return (T)new CompassViewModel(mApplication,mModel);
        return null;
    }

}
