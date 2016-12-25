package wor.com.wor.ContentFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wor.com.wor.R;

/**
 * Created by Santosh on 12/8/2016.
 */

public class PitStop2Fragment extends Fragment {

    private static final String TAG = "PitStop2 Fragment";

    public PitStop2Fragment() {
        // Required empty public constructor
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pitstop2, container, false);
        return rootView;
    }
}
