package com.kittendevelop.sensor.ui.main.views.gyroscope;

import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kittendevelop.sensor.R;

import javax.inject.Inject;

public class GyroscopeModel implements SensorEventListener {

    private final MutableLiveData<Drawable>mBubble;

    private final SensorManager mSensorManager;
    private Drawable mDrawable;

    @Inject
    public GyroscopeModel(SensorManager mSensorManager) {
        this.mSensorManager = mSensorManager;
        mBubble = new MutableLiveData<>();
    }

    public GyroscopeModel drawable(Drawable drawable){
        mDrawable = drawable;
        mBubble.setValue(mDrawable);
        return this;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public LiveData<Drawable> getData(){
        return mBubble;
    }

    public void start(){

    }

    public void stop(){
        mSensorManager.unregisterListener(this);
    }

}
