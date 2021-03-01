package com.kittendevelop.sensor.ui.main.views.compass;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CompassModel implements SensorEventListener{

    private final MutableLiveData<Drawable> mCompass;

    private final SensorManager mSensorManager;

    private final Sensor mAccelerometer;
    private final Sensor mMagnetometer;
    private Drawable mDrawable;

    public CompassModel(SensorManager mSensorManager) {
        this.mSensorManager = mSensorManager;
        mCompass = new MutableLiveData<>();
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public CompassModel drawable(Drawable drawable){
        mDrawable = drawable;
        mCompass.setValue(drawable);
        return this;
    }

    public void start(){

       mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
       mSensorManager.registerListener(this,mMagnetometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop(){
       mSensorManager.unregisterListener(this);
    }

    public LiveData<Drawable>getData(){
        return mCompass;
    }

    private float[] floatGravity = new float[3];
    private float[] floatGeoMagnetic = new float[3];

    private float[] floatOrientation = new float[3];
    private float[] floatRotationMatrix = new float[9];

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        fillValues(sensorEvent);
        if(floatGravity!=null&&floatGeoMagnetic!=null) {
            SensorManager.getRotationMatrix(floatRotationMatrix, null, floatGravity, floatGeoMagnetic);
            SensorManager.getOrientation(floatRotationMatrix, floatOrientation);
            float rotation = (float) (-floatOrientation[0]*180/3.14159);
            mCompass.setValue(getRotateDrawable(mDrawable,rotation));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    private void fillValues(SensorEvent sensorEvent){
        switch (sensorEvent.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                floatGravity = sensorEvent.values;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                floatGeoMagnetic = sensorEvent.values;
                break;
        }
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
