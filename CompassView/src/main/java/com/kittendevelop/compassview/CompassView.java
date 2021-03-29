package com.kittendevelop.compassview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

import static android.content.Context.SENSOR_SERVICE;


@BindingMethods({
        @BindingMethod(type = CompassView.class, attribute = "app:onListener", method = "setOnCompassViewListener"),
        @BindingMethod(type = CompassView.class, attribute = "app:isWork", method = "work")
})
public class CompassView extends FrameLayout implements SensorEventListener {
    private ImageView mImageView;

    private float mCurrentDegree = 0f;

    private Drawable mImage;

    private SensorManager mSensorManager;

    private OnCompassViewListener mListener;

    public CompassView(Context context) {
        super(context);
        init(context,null);
    }

    public CompassView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CompassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec < heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        }
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.compass_layout, this, true);
        mSensorManager = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);

        updateImage();
    }


    private void updateImage() {
        if (mImage == null) {
            mImage = ContextCompat.getDrawable(getContext(), R.drawable.ic_compass_image);
        }
        mImageView = findViewById(R.id.compass_image);
        mImageView.setImageDrawable(mImage);
    }

    private void start(){
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }

    private void stop(){
        mSensorManager.unregisterListener(this);
    }

    public void work(boolean started){
        if(started)start();
        else stop();
    }

    public void setOnCompassViewListener(OnCompassViewListener listener){
        mListener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = (Math.round(event.values[0])+360)%360;
        RotateAnimation rotateAnimation = new RotateAnimation(mCurrentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(210);
        rotateAnimation.setFillAfter(true);
        mImageView.startAnimation(rotateAnimation);
        mCurrentDegree = -degree;

        if(mListener!=null)mListener.degrees(mCurrentDegree);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public interface  OnCompassViewListener{
        void degrees(float deg);
    }


}