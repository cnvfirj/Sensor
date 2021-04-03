package com.kittendevelop.sensor.ui.main.views.compass;

import android.app.Application;
import android.media.MediaPlayer.OnCompletionListener;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.kittendevelop.compassview.CompassView;
import com.kittendevelop.sensor.ui.main.views.stars.MainListener;

import static com.kittendevelop.sensor.Massages.MASSAGE;

public class CompassViewModel extends AndroidViewModel implements LifecycleObserver{

    private final ObservableField<Boolean>mWorking;

    private CompassView.OnCompassViewListener mCompassListener;

    private MainListener mMainListener;

    public CompassViewModel(@NonNull Application application, CompassModel mModel) {
        super(application);
        mWorking = new ObservableField<>();
        mCompassListener = new CompassView.OnCompassViewListener() {
            @Override
            public void degrees(float deg) {
                if(mMainListener!=null)mMainListener.degrees(deg);
            }
        };
    }

    public void setMainListener(MainListener listener){
        mMainListener = listener;
    }

    public ObservableField<Boolean>isWorking(){
        return mWorking;
    }

    public CompassView.OnCompassViewListener listener(){
        return mCompassListener;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void connect(){
       mWorking.set(true);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disconnect(){
        mWorking.set(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
