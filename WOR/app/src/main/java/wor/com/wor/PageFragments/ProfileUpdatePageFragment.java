package wor.com.wor.PageFragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.SharedPreferencesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import wor.com.wor.R;
import wor.com.wor.models.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Santosh on 28-Dec-16.
 */

public class ProfileUpdatePageFragment extends PageMainFragment {  public ProfileUpdatePageFragment(){}

    private EditText musername, mrollnum, memail, mblocknum, mphonenum;
    private ImageView meditblocknum, meditphonenum;
    private SharedPreferences sharedPreferences;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile_update_page, container, false);
            musername = (EditText) view.findViewById(R.id.profile_username);
        mrollnum = (EditText) view.findViewById(R.id.profile_roll_no);
        memail = (EditText) view.findViewById(R.id.profile_email);
        mblocknum = (EditText) view.findViewById(R.id.profile_blockno);
        mphonenum = (EditText) view.findViewById(R.id.profile_phone_no);
        meditblocknum = (ImageView) view.findViewById(R.id.profile_edit_blockno);
        meditphonenum = (ImageView) view.findViewById(R.id.profile_edit_phone_no);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        sharedPreferences = getActivity().getSharedPreferences("wor",MODE_PRIVATE);

        meditphonenum.setClickable(true);
        meditblocknum.setClickable(true);
        meditblocknum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        meditphonenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }



    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        String test = sharedPreferences.getString("userString", "");
        User user = User.parse(test);
        musername.setText(user.getUserName());
        mrollnum.setText(user.getRollNo());
        memail.setText(user.getEmail());
        mblocknum.setText(user.getBlockNo());
        mphonenum.setText(user.getMobile());


    }

}