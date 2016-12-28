package wor.com.wor.PageFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wor.com.wor.R;

/**
 * Created by Santosh on 28-Dec-16.
 */

public class ProfileUpdatePageFragment extends PageMainFragment {  public ProfileUpdatePageFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile_update_page, container, false);

        return view;
    }
}