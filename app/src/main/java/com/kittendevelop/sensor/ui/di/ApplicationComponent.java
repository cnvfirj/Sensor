package com.kittendevelop.sensor.ui.di;


import androidx.fragment.app.Fragment;

import com.kittendevelop.sensor.MainActivity;
import com.kittendevelop.sensor.MainApplication;
import com.kittendevelop.sensor.ui.main.MainFragment;
import com.kittendevelop.sensor.ui.main.views.compass.CompassViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainActivity activity);
    void inject(MainApplication application);
    void inject(MainFragment fragment);


}
