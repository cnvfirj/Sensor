package com.kittendevelop.sensor.ui.main.views.coordinates;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;

import com.kittendevelop.sensor.ui.main.views.stars.MainListener;

import javax.inject.Inject;

public class CoordinatesViewModel extends AndroidViewModel implements LifecycleObserver {

    private CoordinatesModel mModel;

    private MainListener mMainListener;



    public CoordinatesViewModel(@NonNull Application application, CoordinatesModel mModel) {
        super(application);
        this.mModel = mModel;
    }

    public void setMainListener(MainListener listener){
        mMainListener = listener;
    }

    public String getCoordinates(){
       return getApplication().getString(mModel.getText());
    }
}
