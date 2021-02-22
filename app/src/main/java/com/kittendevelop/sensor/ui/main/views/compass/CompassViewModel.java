package com.kittendevelop.sensor.ui.main.views.compass;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.hardware.SensorManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.kittendevelop.sensor.R;

import javax.inject.Inject;

public class CompassViewModel extends AndroidViewModel implements LifecycleObserver {

    private final CompassModel mModel;



    public CompassViewModel(@NonNull Application application, CompassModel mModel) {
        super(application);
        this.mModel = mModel;
    }

    public Drawable getDrawable(){
        return mModel.getDrawable();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void connect(){
       mModel.start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disconnect(){
        mModel.stop();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }



}
