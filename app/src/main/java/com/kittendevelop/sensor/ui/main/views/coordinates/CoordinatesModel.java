package com.kittendevelop.sensor.ui.main.views.coordinates;

import com.kittendevelop.sensor.R;

import javax.inject.Inject;

public class CoordinatesModel{

    private int mText;


//    @Inject
    public CoordinatesModel() {
        mText = R.string.coord_not_perm;
    }

    public int getText() {
        return mText;
    }
}
