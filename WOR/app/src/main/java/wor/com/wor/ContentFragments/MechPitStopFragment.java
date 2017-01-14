package wor.com.wor.ContentFragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.app.Activity;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


import wor.com.wor.R;
import wor.com.wor.models.Cycle;
import wor.com.wor.viewholders.CycleViewHolder;

/**
 * Created by Santosh on 13-Jan-17.
 */

public class MechPitStopFragment extends Fragment {
    private static final String TAG = "TimeLineFragment";

    public MechPitStopFragment() {
        // Required empty public constructor
    }

        Button rideNow;


    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private ProgressBar mProgressBar;
    private LinearLayoutManager mManager1;

    private RecyclerView mCycleRecyclerView;
    private FirebaseRecyclerAdapter<Cycle, CycleViewHolder> mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pitstop_mech, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mCycleRecyclerView = (RecyclerView) rootView.findViewById(R.id.mechcycles);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mManager1 = new LinearLayoutManager(getActivity());
        mCycleRecyclerView.setLayoutManager(mManager1);
        mAdapter = new FirebaseRecyclerAdapter<Cycle, CycleViewHolder>(Cycle.class, R.layout.item_cycle,
                CycleViewHolder.class, mDatabase.child("pitstops").child("mech")){
            @Override
            protected void populateViewHolder(CycleViewHolder viewHolder, Cycle model, int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                Picasso.with(getActivity()).load(model.getImage()).into(viewHolder.cycleimage);
                viewHolder.bindToPost(model, new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                    }
                    else
                    {
                        Intent rIntent = new Intent(getActivity(), wor.com.wor.rideNow.class);
                        startActivity(rIntent);
                    }
                    }

                });
            }
        };
        mCycleRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(getContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            //rideNow.setClickable(true);
        }
    }
}
