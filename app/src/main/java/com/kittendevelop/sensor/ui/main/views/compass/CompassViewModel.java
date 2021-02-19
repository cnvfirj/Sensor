package com.kittendevelop.sensor.ui.main.views.compass;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.kittendevelop.sensor.R;

import javax.inject.Inject;

public class CompassViewModel extends AndroidViewModel {

    private CompassModel mModel;



    public CompassViewModel(@NonNull Application application, CompassModel mModel) {
        super(application);
        this.mModel = mModel;
    }

    public Drawable getDrawable(){
        return getApplication().getDrawable(R.drawable.ic_compass);
    }

}
