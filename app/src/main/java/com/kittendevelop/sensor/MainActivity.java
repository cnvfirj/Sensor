package com.kittendevelop.sensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        ((MainApplication)getApplication()).component().inject(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ((MainApplication)getApplication()).fragment())
                    .commitNow();
        }
    }

}