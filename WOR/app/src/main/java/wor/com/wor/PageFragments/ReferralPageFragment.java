package wor.com.wor.PageFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

import wor.com.wor.R;
import wor.com.wor.models.*;


public class ReferralPageFragment extends PageMainFragment {

    private EditText friendMail;
    private User mUser;
    private ProgressDialog PD;

    private DatabaseReference mDatabase;

    public ReferralPageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_referral_page, container, false);
        friendMail = (EditText) view.findViewById(R.id.refer_email);
        view.findViewById(R.id.refer_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refer();
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("wor", Context.MODE_PRIVATE);
       // mUser = User.parse(sharedPreferences.getString("userString", ""));
        PD = new ProgressDialog(getActivity());
        PD.setMessage("Please wait...");
        PD.setCancelable(false);
        PD.setCanceledOnTouchOutside(false);
        return view;
    }

    public void refer(){
        PD.show();
        String mail = friendMail.getText().toString();
        long time = System.currentTimeMillis();
        String referralCode = String.valueOf(time).substring(1);
        String referralCodeReadable = referralCode.substring(0,4)+" "+referralCode.substring(4,8)+" "+referralCode.substring(8,12);
        mDatabase.child("referrals").child(referralCode).setValue(new Referral(referralCode, mail));
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mail);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Referral code for Wheels on Roll app");
        emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, getString(R.string.referral_message) + referralCodeReadable);
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        PD.dismiss();
    }

}
