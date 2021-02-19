package com.kittendevelop.sensor.ui.main.views.coordinates;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import javax.inject.Inject;

public class CoordinatesViewModel extends AndroidViewModel {

    private CoordinatesModel mModel;



    public CoordinatesViewModel(@NonNull Application application, CoordinatesModel mModel) {
        super(application);
        this.mModel = mModel;
    }

    public String getCoordinates(){
       return getApplication().getString(mModel.getText());
    }
}
