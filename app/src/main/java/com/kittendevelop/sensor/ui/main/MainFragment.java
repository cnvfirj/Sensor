package com.kittendevelop.sensor.ui.main;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kittendevelop.sensor.MainApplication;
import com.kittendevelop.sensor.R;
import com.kittendevelop.sensor.databinding.MainFragmentBinding;
import com.kittendevelop.sensor.ui.main.gps.Permiss;
import com.kittendevelop.sensor.ui.main.views.compass.CompassViewModel;
import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesViewModel;
import com.kittendevelop.sensor.ui.main.views.stars.StarsViewModel;

import javax.inject.Inject;

import static com.kittendevelop.sensor.Massages.MASSAGE;


public class MainFragment extends Fragment{
    @Inject
    public CompassViewModel mCompassViewModel;
    @Inject
    public CoordinatesViewModel mCoordinatesViewModel;
    @Inject
    public StarsViewModel mStarsViewModel;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((MainApplication)getActivity().getApplication()).component().inject(this);
        getLifecycle().addObserver(mCompassViewModel);
        getLifecycle().addObserver(mCoordinatesViewModel);
        getLifecycle().addObserver(mStarsViewModel);
        mCompassViewModel.setMainListener(mStarsViewModel);
        mCoordinatesViewModel.setMainListener(mStarsViewModel);
        checkPermission();
        return inflater.inflate(R.layout.main_fragment, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainFragmentBinding b = MainFragmentBinding.bind(getView());
        b.setCompass(mCompassViewModel);
        b.setStars(mStarsViewModel);
        b.setCoordinates(mCoordinatesViewModel);

    }

    private void checkPermission(){
        if(Permiss.checkPermiss(getContext()))mCoordinatesViewModel.connect();
        else {
            Permiss.dialog(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MASSAGE("code fragment"+requestCode);
        if(requestCode==0){
            MASSAGE("OK "+PackageManager.PERMISSION_GRANTED);
            MASSAGE("result "+grantResults[0]);
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                mCoordinatesViewModel.connect();
            }else {
                if(Permiss.checkRationale(getActivity())){
                    Permiss.rationale(this);
                }
            }
        }
    }
}