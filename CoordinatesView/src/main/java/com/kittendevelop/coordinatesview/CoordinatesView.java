package com.kittendevelop.coordinatesview;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

@BindingMethods({
        @BindingMethod(type = CoordinatesView.class, attribute = "onListener", method = "setOnCoordinatesListener"),
        @BindingMethod(type = CoordinatesView.class, attribute = "isWork", method = "work")
})
public class CoordinatesView extends FrameLayout {

    private TextView mTextView;

    private OnCoordinatesListener mListener;

    private Request mRequest;

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

    public void updateLocation(Context context){
        mRequest = new Request(LocationServices.getFusedLocationProviderClient(context));
        mText = getContext().getString(R.string.search);
        updateText();
        mRequest.searchLocation(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()){
                    Location l = task.getResult();
                    if(l!=null) {
                        mPoint[0] = l.getLatitude();
                        mPoint[1] = l.getLongitude();
                        mText = mPoint[0] + ":" + mPoint[1];
                    }else {
                        mText = getContext().getString(R.string.gps_off);
                    }
                    updateText();
                    if (mListener!=null)mListener.point(mPoint,true);
                }else {
                    if(mListener!=null)mListener.point(null,false);
                    mText = getContext().getString(R.string.error);
                    updateText();
                }
            }
        });
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
        if(work)start(getContext());
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

    private void start(Context context){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
            updateLocation(context);
        }else {
            mTextView.setText(getContext().getResources().getText(R.string.permission));
        }
    }

    private void stop(){
         if(mRequest!=null)mRequest.stopLocation();
    }

    public interface OnCoordinatesListener{
        void point(double[] coordinates, boolean ready);
    }
}
