package com.kittendevelop.coordinatesview;

import android.location.Location;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import androidx.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;

public class Request {

    private FusedLocationProviderClient mLocationProvider;
    private CancellationTokenSource mCancellationTokenSource;


    public Request(FusedLocationProviderClient locationProvider) {
        this.mLocationProvider = locationProvider;
        mCancellationTokenSource = new CancellationTokenSource();
    }

    public void searchLocation(OnCompleteListener<Location> listener){
        Task<Location> task = mLocationProvider.getCurrentLocation(PRIORITY_HIGH_ACCURACY,mCancellationTokenSource.getToken());
        task.addOnCompleteListener(listener);
    }

    public void stopLocation(){
        mCancellationTokenSource.cancel();
        mLocationProvider = null;
        mCancellationTokenSource = null;
    }


}
