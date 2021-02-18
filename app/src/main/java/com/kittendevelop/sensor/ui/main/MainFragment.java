package com.kittendevelop.sensor.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kittendevelop.sensor.R;
import com.kittendevelop.sensor.databinding.MainFragmentBinding;
import com.kittendevelop.sensor.ui.main.views.compass.CompassModel;
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

    private CompassViewModel mCompassViewModel;
    private GyroscopeViewModel mGyroscopeViewModel;
    private CoordinatesViewModel mCoordinatesViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCompassViewModel = new ViewModelProvider(this,new CompassViewModelFactory(getActivity().getApplication(),new CompassModel())).get(CompassViewModel.class);
        mGyroscopeViewModel = new ViewModelProvider(this,new GyroscopeViewModelFactory(getActivity().getApplication(),new GyroscopeModel())).get(GyroscopeViewModel.class);
        mCoordinatesViewModel = new ViewModelProvider(this,new CoordinatesViewModelFactory(getActivity().getApplication(),new CoordinatesModel())).get(CoordinatesViewModel.class);

        MainFragmentBinding b = MainFragmentBinding.bind(getView());
        b.setCompass(mCompassViewModel);
        b.setGyroscope(mGyroscopeViewModel);
        b.setCoordinates(mCoordinatesViewModel);

    }

}