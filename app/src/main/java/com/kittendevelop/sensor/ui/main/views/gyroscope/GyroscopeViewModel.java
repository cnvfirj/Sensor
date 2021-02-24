package com.kittendevelop.sensor.ui.main.views.gyroscope;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;

import com.kittendevelop.sensor.MainApplication;
import com.kittendevelop.sensor.R;

import javax.inject.Inject;


public class GyroscopeViewModel extends AndroidViewModel  implements LifecycleObserver {

   private final GyroscopeModel mModel;

    private final LiveData<Drawable> mBubble;

    private final ObservableField<Drawable> mDrawable;



    public GyroscopeViewModel(@NonNull Application application, GyroscopeModel mModel) {
        super(application);
        mDrawable = new ObservableField<>();
        this.mModel = mModel;
        mBubble = mModel.getData();
        mBubble.observe(((MainApplication) application).fragment(), new Observer<Drawable>() {
            @Override
            public void onChanged(Drawable drawable) {
                mDrawable.set(drawable);
            }
        });
    }

    public ObservableField<Drawable>getBubble(){
        return mDrawable;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void connect(){
        mModel.start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disconnect(){
        mModel.stop();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
