package com.kittendevelop.sensor.ui.main.views.stars;

import android.app.Application;
import android.graphics.PointF;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;

import static com.kittendevelop.sensor.Massages.MASSAGE;

public class StarsViewModel extends AndroidViewModel implements LifecycleObserver, MainListener {

    private StarsModel mModel;



    public StarsViewModel(@NonNull Application application, StarsModel model) {
        super(application);
        mModel = model;

    }

    @Override
    public void degrees(float deg) {
    }

    @Override
    public void point(double[] pt) {

    }
}
