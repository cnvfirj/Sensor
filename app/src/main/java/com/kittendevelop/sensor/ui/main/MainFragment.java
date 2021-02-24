package com.kittendevelop.sensor.ui.main;

import androidx.lifecycle.ViewModelProvider;

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
import com.kittendevelop.sensor.ui.main.views.compass.CompassViewModel;
import com.kittendevelop.sensor.ui.main.views.compass.CompassViewModelFactory;
import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesModel;
import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesViewModel;
import com.kittendevelop.sensor.ui.main.views.coordinates.CoordinatesViewModelFactory;
import com.kittendevelop.sensor.ui.main.views.gyroscope.GyroscopeModel;
import com.kittendevelop.sensor.ui.main.views.gyroscope.GyroscopeViewModel;
import com.kittendevelop.sensor.ui.main.views.gyroscope.GyroscopeViewModelFactory;
//import com.kittendevelop.sensor.ui.di.DaggerComponentMainFragment;

import javax.inject.Inject;

import static com.kittendevelop.sensor.Massages.MASSAGE;

public class MainFragment extends Fragment {
    @Inject
    public CompassViewModel mCompassViewModel;
    @Inject
    public GyroscopeViewModel mGyroscopeViewModel;
    @Inject
    public CoordinatesViewModel mCoordinatesViewModel;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((MainApplication)getActivity().getApplication()).component().inject(this);
        getLifecycle().addObserver(mCompassViewModel);
        getLifecycle().addObserver(mGyroscopeViewModel);
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainFragmentBinding b = MainFragmentBinding.bind(getView());
        b.setCompass(mCompassViewModel);
        b.setGyroscope(mGyroscopeViewModel);
        b.setCoordinates(mCoordinatesViewModel);
    }

}