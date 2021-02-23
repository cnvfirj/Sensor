package com.kittendevelop.sensor.ui.main.views.compass;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.VectorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CompassModel implements SensorEventListener, LifecycleObserver {

    private MutableLiveData<Drawable> mCompass;

    private SensorManager mSensorManager;

    private Sensor mAccelerometer;
    private Sensor mMagnetometer;

    public CompassModel(SensorManager mSensorManager) {
        this.mSensorManager = mSensorManager;
        mCompass = new MutableLiveData<>();
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public CompassModel drawable(Drawable drawable){
        drawable.mutate().getCurrent().setLevel(500);
        mCompass.setValue(drawable);
        rotate();
        return this;
    }

    public void start(){
       mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_UI);
       mSensorManager.registerListener(this,mMagnetometer,SensorManager.SENSOR_DELAY_UI);
    }

    public void stop(){
       mSensorManager.unregisterListener(this);
    }

    public LiveData<Drawable>getData(){
        return mCompass;
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

    private void rotate(){
        mCompass.setValue(getRotateDrawable(mCompass.getValue(),20));
    }

    private Drawable getRotateDrawable(final Drawable d, final float angle) {
        final Drawable[] arD = { d };
        return new LayerDrawable(arD) {
            @Override
            public void draw(final Canvas canvas) {

                canvas.save();
                canvas.rotate(angle, d.getBounds().width() / 2, d.getBounds().height() / 2);
                super.draw(canvas);
                canvas.restore();
            }
        };
    }
}
