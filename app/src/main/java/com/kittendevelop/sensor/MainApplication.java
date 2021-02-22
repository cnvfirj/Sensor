package com.kittendevelop.sensor;

import android.app.Application;

import androidx.fragment.app.Fragment;

import com.kittendevelop.sensor.ui.di.ApplicationComponent;
import com.kittendevelop.sensor.ui.di.ApplicationModule;
import com.kittendevelop.sensor.ui.di.DaggerApplicationComponent;
import com.kittendevelop.sensor.ui.main.MainFragment;


public class MainApplication extends Application {

    private ApplicationComponent mComponent;
    private MainFragment mFragment;
//
    @Override
    public void onCreate() {
        super.onCreate();
        mFragment = MainFragment.newInstance();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this,mFragment))
                .build();
        mComponent.inject(this);

    }

    public MainFragment fragment(){
        return mFragment;
    }
    public ApplicationComponent component(){
        return mComponent;
    }
}
