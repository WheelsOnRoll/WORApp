package wor.com.wor.PageFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wor.com.wor.R;

public class CycleReportPageFragment extends PageMainFragment {
    public CycleReportPageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cycle_report_page, container, false);

        view.findViewById(R.id.owner_complaint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.feedbackMail)});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Complaint for cycle owned for this Semester");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });

        view.findViewById(R.id.rental_complaint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.feedbackMail)});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Complaint for cycle procured on Rent");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });

        return view;
    }

}
