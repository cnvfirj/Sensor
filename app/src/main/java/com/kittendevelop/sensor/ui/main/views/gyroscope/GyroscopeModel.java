package com.kittendevelop.sensor.ui.main.views.gyroscope;

import com.kittendevelop.sensor.R;

import javax.inject.Inject;

public class GyroscopeModel{

    private int mSrc;


    @Inject
    public GyroscopeModel() {
        mSrc = R.drawable.ic_gyroscope;
    }

    public int getSrc() {
        return mSrc;
    }
}
