package com.kittendevelop.sensor.ui.main.views.stars;

import android.app.Application;
import android.graphics.PointF;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.kittendevelop.sensor.Massages.MASSAGE;

public class StarsViewModel extends AndroidViewModel implements LifecycleObserver, MainListener {

    private StarsModel mModel;

    private final ObservableField<Integer> mWaiting;



    public StarsViewModel(@NonNull Application application, StarsModel model) {
        super(application);
        mModel = model;
        mWaiting = new ObservableField<>(VISIBLE);
    }
    @Override
    public void degrees(float deg) {
    }
    @Override
    public void point(double[] pt, boolean ready) {
          if(ready)mWaiting.set(INVISIBLE);
          else mWaiting.set(VISIBLE);
    }

    public ObservableField<Integer> getWaiting(){
        return mWaiting;
    }
}
