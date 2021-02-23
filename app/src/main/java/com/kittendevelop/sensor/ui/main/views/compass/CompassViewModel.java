package com.kittendevelop.sensor.ui.main.views.compass;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.VectorDrawable;
import android.hardware.SensorManager;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;

import com.kittendevelop.sensor.MainApplication;
import com.kittendevelop.sensor.R;

import javax.inject.Inject;

public class CompassViewModel extends AndroidViewModel implements LifecycleObserver{

    private final CompassModel mModel;

    private LiveData<Drawable>mCompass;

    private ObservableField<Drawable>mDrawable;


    public CompassViewModel(@NonNull Application application, CompassModel mModel) {
        super(application);
        mDrawable = new ObservableField<>();
        this.mModel = mModel;
        mCompass = this.mModel.getData();
        mCompass.observe(((MainApplication)application).fragment(), new Observer<Drawable>() {
            @Override
            public void onChanged(Drawable drawable) {
               mDrawable.set(drawable);
            }
        });
    }



    public ObservableField<Drawable>getCompass(){
        return mDrawable;
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
