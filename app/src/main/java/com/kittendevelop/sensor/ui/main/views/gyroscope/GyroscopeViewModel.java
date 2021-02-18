package com.kittendevelop.sensor.ui.main.views.gyroscope;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.kittendevelop.sensor.R;


public class GyroscopeViewModel extends AndroidViewModel {

   private GyroscopeModel mModel;

    public GyroscopeViewModel(@NonNull Application application, GyroscopeModel mModel) {
        super(application);
        this.mModel = mModel;
    }

    public Drawable getDrawable(){
        return getApplication().getDrawable(R.drawable.ic_gyroscope);

    }
}
