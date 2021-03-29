package com.kittendevelop.sensor.ui.di;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.VectorDrawable;
import android.hardware.SensorManager;

import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kittendevelop.sensor.MainApplication;
import com.kittendevelop.sensor.R;
import com.kittendevelop.sensor.ui.main.MainFragment;
import com.kittendevelop.sensor.ui.main.views.compass.CompassModel;
import com.kittendevelop.sensor.ui.main.views.compass.CompassViewModel;
import com.kittendevelop.sensor.ui.main.views.compass.CompassViewModelFactory;
import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesModel;
import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesViewModel;
import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesViewModelFactory;
import com.kittendevelop.sensor.ui.main.views.stars.StarsModel;
import com.kittendevelop.sensor.ui.main.views.stars.StarsViewModel;
import com.kittendevelop.sensor.ui.main.views.stars.StarsViewModelFactory;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Module
public class ApplicationModule{

    public final MainApplication mApplication;

    public MainFragment mFragment;

    public ApplicationModule(MainApplication mApplication,MainFragment mFragment){
        this.mFragment = mFragment;
        this.mApplication = mApplication;
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
    public CoordinatesViewModel coordinatesViewModel(){
        return new ViewModelProvider(mFragment,coordinatesFactory()).get(CoordinatesViewModel.class);
    }

    @Provides
    @Singleton
    public StarsViewModel starsViewModel(){
        return new ViewModelProvider(mFragment,starsFactory()).get(StarsViewModel.class);
    }

    private CompassViewModelFactory compassFactory(){
        return new CompassViewModelFactory(mApplication,new CompassModel());
    }

    private CoordinatesViewModelFactory coordinatesFactory(){
        return new CoordinatesViewModelFactory(mApplication,new CoordinatesModel());
    }

    private StarsViewModelFactory starsFactory(){
        return new StarsViewModelFactory(mApplication, new StarsModel());
    }

//    @Singleton
//    private SensorManager sensorManager(){
//        return (SensorManager)mApplication.getSystemService(Context.SENSOR_SERVICE);
//    }


    @Qualifier
    @Retention(RUNTIME)
    public @interface ForMainApplication{

    }

}
