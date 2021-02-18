package com.kittendevelop.sensor.ui.main.views.compass;

import com.kittendevelop.sensor.R;

import static com.kittendevelop.sensor.Massages.MASSAGE;

public class CompassModel {

    private int mSrc;

    public CompassModel() {
        mSrc = R.drawable.ic_compass;
    }

    public int getSrc() {
        return mSrc;
    }
}
