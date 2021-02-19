package com.kittendevelop.sensor;

import android.app.Application;

import com.kittendevelop.sensor.ui.di.ApplicationComponent;
import com.kittendevelop.sensor.ui.di.ApplicationModule;
import com.kittendevelop.sensor.ui.di.DaggerApplicationComponent;


public class MainApplication extends Application {

    private ApplicationComponent mComponent;
//
    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mComponent.inject(this);

    }
//
    public ApplicationComponent component(){
        return mComponent;
    }
}
