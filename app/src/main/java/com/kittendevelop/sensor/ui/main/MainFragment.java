package com.kittendevelop.sensor.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kittendevelop.compassview.CompassView;
import com.kittendevelop.sensor.MainApplication;
import com.kittendevelop.sensor.R;
import com.kittendevelop.sensor.databinding.MainFragmentBinding;
import com.kittendevelop.sensor.ui.main.views.compass.CompassViewModel;
import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesViewModel;
import com.kittendevelop.sensor.ui.main.views.stars.StarsViewModel;

import javax.inject.Inject;


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

}