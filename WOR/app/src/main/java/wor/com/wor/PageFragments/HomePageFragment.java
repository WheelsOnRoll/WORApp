package wor.com.wor.PageFragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import wor.com.wor.R;
import wor.com.wor.rideNow;

/**
 * Created by Santosh on 12/8/2016.
 */

public class HomePageFragment extends PageMainFragment {

    public HomePageFragment(){}

    Button rideNow;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(getContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            rideNow.setClickable(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        // Inflate thdee layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_home_page, container, false);
        rideNow = (Button) rootView.findViewById(R.id.rideNow);

        rideNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                }
                else
                {
                    Intent rIntent = new Intent(getActivity(), wor.com.wor.rideNow.class);
                    startActivity(rIntent);
                }
            }
        });

        return rootView;
    }



}