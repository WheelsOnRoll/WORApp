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

public class CycleReportPageFragment extends PageMainFragment {
    public CycleReportPageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cycle_report_page, container, false);

        return view;
    }
}
