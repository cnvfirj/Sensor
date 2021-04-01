package com.kittendevelop.sensor.ui.main.views.coordinates;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.kittendevelop.coordinatesview.CoordinatesView;
import com.kittendevelop.sensor.ui.main.views.stars.MainListener;

import javax.inject.Inject;

public class CoordinatesViewModel extends AndroidViewModel implements LifecycleObserver {

    private CoordinatesModel mModel;

    private MainListener mMainListener;

    private final ObservableField<Boolean> mWorking;

    private final CoordinatesView.OnCoordinatesListener mOnCoordinatesListener;
    public CoordinatesViewModel(@NonNull Application application, CoordinatesModel mModel) {
        super(application);
        mWorking = new ObservableField<>(false);
        mOnCoordinatesListener = new CoordinatesView.OnCoordinatesListener() {
            @Override
            public void point(double[] coordinates,boolean ready) {
                if(ready) {
                    if (mMainListener != null) mMainListener.point(coordinates);
                }
            }
        };
        this.mModel = mModel;
    }

    public void setMainListener(MainListener listener){
        mMainListener = listener;
    }

    public CoordinatesView.OnCoordinatesListener getListener(){
        return mOnCoordinatesListener;
    }

    public ObservableField<Boolean>isWork(){
        return mWorking;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void connect(){
        mWorking.set(true);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void disconnect(){
        mWorking.set(false);
    }
}
