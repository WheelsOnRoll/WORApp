package wor.com.wor.PageFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import wor.com.wor.MainActivity;
import wor.com.wor.R;
import wor.com.wor.SignInActivity;

/**
 * Created by Santosh on 28-Dec-16.
 */

public class LogoutPageFragment extends PageMainFragment implements  View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {  public LogoutPageFragment(){}
    Button logoutButton;
    private FirebaseAuth mFirebaseAuth;
    private GoogleApiClient mGoogleApiClient;
    private String mUsername;
    private static final String TAG = "Logout Page Fragment";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_logout_page, container, false);
        logoutButton = (Button) view.findViewById(R.id.logoutButton);
        mFirebaseAuth = FirebaseAuth.getInstance();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).logout();

            }
        });
        return view;
    }
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(getActivity(), "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
