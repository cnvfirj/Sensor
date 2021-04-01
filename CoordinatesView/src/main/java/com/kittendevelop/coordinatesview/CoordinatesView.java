package com.kittendevelop.coordinatesview;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.location.Location;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;

import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;

@BindingMethods({
        @BindingMethod(type = CoordinatesView.class, attribute = "app:onListener", method = "setOnCoordinatesListener"),
        @BindingMethod(type = CoordinatesView.class, attribute = "app:isWork", method = "work")
})
public class CoordinatesView extends FrameLayout {

    private TextView mTextView;

    private OnCoordinatesListener mListener;

    private String mText;
    private double[]mPoint;

    public CoordinatesView(@NonNull Context context) {
        super(context);
        init(context,null);

    }

    public CoordinatesView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void updateLocation(){
        updateText();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec < heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        }
    }


    public void setOnCoordinatesListener(OnCoordinatesListener listener){
        mListener = listener;
    }

    public void work(boolean work){
        if(work)start();
        else stop();
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.coordinates_layout, this, true);

        mPoint = new double[2];
        mTextView = findViewById(R.id.number);

    }

    private void updateText(){
        if(mText==null)mText = getContext().getString(R.string.search);
        mTextView.setText(mText);
    }

    private void start(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
            updateLocation();
        }else {
            mTextView.setText(getContext().getResources().getText(R.string.permission));
        }
    }

    private void stop(){

    }

    public interface OnCoordinatesListener{
        void point(double[] coordinates, boolean ready);
    }
}
