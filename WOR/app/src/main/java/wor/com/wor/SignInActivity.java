package wor.com.wor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import wor.com.wor.models.User;

/**
 * Created by Santosh on 28-Dec-16.
 */


public class SignInActivity extends BaseActivity implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private SignInButton mSignInButton;


    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private DatabaseReference mDatabase;
    private GoogleApiClient mGoogleApiClient;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        // Assign fields
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);

        mSignInButton.setOnClickListener(this);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                firebaseAuthWithGoogle(acct);

            } else {
                Log.e(TAG, "Google Sign In failed.");
                Toast.makeText(this, "Sign IN failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGooogle:" + acct.getId());
        showProgressDialog();

        final String Email = acct.getEmail();
        final String PhotoUrl;
        final String DN = acct.getDisplayName();
        if (acct.getPhotoUrl() == null)
            PhotoUrl = null;
        else
            PhotoUrl = acct.getPhotoUrl().toString();


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            final String ID = mFirebaseAuth.getCurrentUser().getUid();
                            final String upname = DN.toUpperCase();
                         mDatabase.child("user-data").child(ID).child("uid").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    boolean idfortest = dataSnapshot.exists();
                                    if(!(idfortest)){
                                        Toast.makeText(SignInActivity.this, "testing1", Toast.LENGTH_LONG).show();
                                        writeNewUser(ID, DN, Email, PhotoUrl, upname);}
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                            hideProgressDialog();
                            startActivity(new Intent(SignInActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }

    // [START basic_write]
    private void writeNewUser(String userId, String name, String email, String PhotoUrl, String upname) {
        User user = new User(name, email, PhotoUrl, upname, userId);


        Map<String, Object> postValues = user.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + userId, postValues);
        childUpdates.put("/user-data/" + userId, postValues);

        mDatabase.updateChildren(childUpdates);
        Toast.makeText(this, "Successfully Signed In", Toast.LENGTH_SHORT).show();

    }
}