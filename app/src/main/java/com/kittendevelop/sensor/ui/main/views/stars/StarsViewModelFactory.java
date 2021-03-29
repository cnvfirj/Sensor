package com.kittendevelop.sensor.ui.main.views.stars;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesViewModel;

import javax.inject.Inject;

public class StarsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Application mApplication;
    private StarsModel mModel;


    public StarsViewModelFactory(@NonNull Application mApplication, StarsModel mModel) {
        this.mApplication = mApplication;
        this.mModel = mModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass== StarsViewModel.class)return (T)new StarsViewModel(mApplication,mModel);
        return null;
    }
}
