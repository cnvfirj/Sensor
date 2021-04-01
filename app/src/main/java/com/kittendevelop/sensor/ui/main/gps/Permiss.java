package com.kittendevelop.sensor.ui.main.gps;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.kittendevelop.sensor.R;

public class Permiss {

    public static boolean checkPermiss(Context context){
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkRationale(Activity activity){
        return ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public static void dialog(Activity activity){
        ActivityCompat.requestPermissions(activity, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION},0);
    }

    public static void dialog(Fragment fragment){
        fragment.requestPermissions(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION},0);
    }

    public static void rationale(Fragment fragment){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(fragment.getActivity());
        dialog.setTitle(R.string.title_location)
                .setMessage(R.string.location_rationale)
                .setPositiveButton(R.string.YES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog(fragment);
                        dialogInterface.cancel();
                    }
                }).setNegativeButton(R.string.NO, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialog.show();
    }
}
