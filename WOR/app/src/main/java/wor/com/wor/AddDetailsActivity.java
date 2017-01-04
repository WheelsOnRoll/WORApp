package wor.com.wor;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddDetailsActivity extends AppCompatActivity {

    private EditText blockNumber;
    private EditText mobileNumber;
    private EditText rollNumber;
    private EditText referralCode;

    private String emailID;
    private String photoURL;
    private String username;

    //Email, photourl, Username
    //bundle extras = getintent.getextras
    //extras.getstring
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        //Get information from the layout file
        blockNumber = (EditText) findViewById(R.id.block_number);
        mobileNumber = (EditText) findViewById(R.id.mobile_number);
        rollNumber = (EditText) findViewById(R.id.roll_number);
        referralCode = (EditText) findViewById(R.id.referral_code);

        Bundle extras = getIntent().getExtras();
        extras.getString("email");
        extras.getString("photoUrl");
        extras.getString("id");
    }


}
