package com.kittendevelop.coordinatesview;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CoordinatesView extends FrameLayout {

    private TextView mTextView;

    private String mText;

    public CoordinatesView(@NonNull Context context) {
        super(context);
    }

    public CoordinatesView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }



    private void updateText(){
      if(mText==null)mText = getContext().getString(R.string.search);
      mTextView = findViewById(R.id.number);
      mTextView.setText(mText);
    }

    private void start(){

    }

    private void stop(){

    }

    public void work(boolean work){

    }

    public void setOnCoordinatesListener(){

    }

    public interface OnCoordinatesListener{
        void text(String coordinates);
        void point(float[] coordinates);
    }
}
