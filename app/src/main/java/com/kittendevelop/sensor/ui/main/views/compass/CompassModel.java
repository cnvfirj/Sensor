package com.kittendevelop.sensor.ui.main.views.compass;

import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.kittendevelop.sensor.R;

import javax.inject.Inject;

import static com.kittendevelop.sensor.Massages.MASSAGE;

public class CompassModel implements SensorEventListener, LifecycleObserver {

    private Drawable mDrawable;

    private SensorManager mSensorManager;

    private Sensor mAccelerometer;
    private Sensor mMagnetometer;

    public CompassModel(SensorManager mSensorManager) {
        this.mSensorManager = mSensorManager;
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public CompassModel drawable(Drawable drawable){
        mDrawable = drawable;
        return this;
    }


    public void start(){
       mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_UI);
       mSensorManager.registerListener(this,mMagnetometer,SensorManager.SENSOR_DELAY_UI);
    }


    public void stop(){
       mSensorManager.unregisterListener(this);
    }




    public Drawable getDrawable(){
        return mDrawable;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
          switch (sensorEvent.sensor.getType()){
              case Sensor.TYPE_ACCELEROMETER:
                  break;
              case Sensor.TYPE_MAGNETIC_FIELD:
                  break;
          }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
