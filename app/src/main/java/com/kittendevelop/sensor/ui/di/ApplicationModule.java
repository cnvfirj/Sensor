package com.kittendevelop.sensor.ui.di;

import android.content.Context;
import android.content.res.Resources;

import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kittendevelop.sensor.MainApplication;
import com.kittendevelop.sensor.ui.main.MainFragment;
import com.kittendevelop.sensor.ui.main.views.compass.CompassModel;
import com.kittendevelop.sensor.ui.main.views.compass.CompassViewModel;
import com.kittendevelop.sensor.ui.main.views.compass.CompassViewModelFactory;
import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesModel;
import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesViewModel;
import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesViewModelFactory;
import com.kittendevelop.sensor.ui.main.views.gyroscope.GyroscopeModel;
import com.kittendevelop.sensor.ui.main.views.gyroscope.GyroscopeViewModel;
import com.kittendevelop.sensor.ui.main.views.gyroscope.GyroscopeViewModelFactory;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Module
public class ApplicationModule implements CallbackDIModule{

    public final MainApplication mApplication;

    public MainFragment mFragment;

    public ApplicationModule(MainApplication mApplication) {
        this.mApplication = mApplication;
    }

    public ApplicationModule owner(MainFragment mFragment){
        this.mFragment = mFragment;
        return this;
    }

    @Provides
    @Singleton
    @ForMainApplication
    public Context applicationContext(){
        return mApplication;
    }

    @Provides
    @Singleton
    public CompassViewModel compassViewModel(){
        return new ViewModelProvider(mFragment,compassFactory()).get(CompassViewModel.class);
    }

    @Provides
    @Singleton
    public GyroscopeViewModel gyroscopeViewModel(){
        return new ViewModelProvider(mFragment,gyroscopeFactory()).get(GyroscopeViewModel.class);
    }

    @Provides
    @Singleton
    public CoordinatesViewModel coordinatesViewModel(){
        return new ViewModelProvider(mFragment,coordinatesFactory()).get(CoordinatesViewModel.class);
    }

    @Provides
    @Singleton
    public ViewModelProvider.NewInstanceFactory compassFactory(){
        return new CompassViewModelFactory(mApplication,new CompassModel());
    }
    @Provides
    @Singleton
    public ViewModelProvider.NewInstanceFactory gyroscopeFactory(){
        return new GyroscopeViewModelFactory(mApplication,new GyroscopeModel());
    }
    @Provides
    @Singleton
    public ViewModelProvider.NewInstanceFactory coordinatesFactory(){
        return new CoordinatesViewModelFactory(mApplication,new CoordinatesModel());
    }

    @Qualifier
    @Retention(RUNTIME)
    public @interface ForMainApplication{

    }

    @Override
    public Resources resources() {
        return mApplication.getResources();
    }



}
