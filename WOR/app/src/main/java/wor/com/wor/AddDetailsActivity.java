package wor.com.wor;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import wor.com.wor.models.User;

public class AddDetailsActivity extends AppCompatActivity {

    private int REFERRAL_BALANCE = 50;

    private EditText blockNumber;
    private EditText mobileNumber;
    private EditText rollNumber;
    private EditText referralCode;
    private ProgressDialog PD;

    private String id;
    private String email;
    private String photoURL;
    private String userName;

    private DatabaseReference mDatabase;

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
        id = extras.getString("id");
        userName = extras.getString("userName");
        email = extras.getString("email");
        photoURL = extras.getString("photoUrl");

        PD = new ProgressDialog(this);
        PD.setMessage("Please wait...");
        PD.setCancelable(false);
        PD.setCanceledOnTouchOutside(false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.add_details_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetails();
            }
        });
    }

    public void addDetails(){
        final String mobile = mobileNumber.getText().toString();
        final String rollNo = rollNumber.getText().toString();
        final String blockNo = blockNumber.getText().toString();
        final String code = referralCode.getText().toString();
        // TODO: Check for possible roll number duplicates
        if(code.length()>0) {
            mDatabase.child("referrals").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(code)) {
                        Toast.makeText(AddDetailsActivity.this, "Invalid Referral Code!", Toast.LENGTH_SHORT).show();
                    } else {
                        String to = dataSnapshot.child(code).child("to").getValue().toString();
                        if (to.equals(email)) {
                            User user = new User(id, userName, email, photoURL, mobile, rollNo, blockNo, REFERRAL_BALANCE);
                            addUser(user);
                        } else {
                            Toast.makeText(AddDetailsActivity.this, "Invalid Referral Code!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            User user = new User(id, userName, email, photoURL, mobile, rollNo, blockNo, 0);
            addUser(user);
        }

    }

    public void addUser(User user){
        mDatabase.child(id).setValue(user);
        SharedPreferences sharedPreferences = getSharedPreferences("wor", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userString", user.toString());
        editor.apply();
        // TODO: Start home
    }

}
