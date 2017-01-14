package wor.com.wor.viewholders;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import wor.com.wor.R;
import wor.com.wor.models.Cycle;

/**
 * Created by Santosh on 14-Jan-17.
 */

public class CycleViewHolder extends RecyclerView.ViewHolder{

    public ImageView cycleimage;
    public TextView cyclename;
    public TextView cyclequantity;
    public Button rideButton;

    public TextView who;
    public CycleViewHolder(View v) {
        super(v);
        cycleimage = (ImageView) v.findViewById(R.id.cycleimageView);
        cyclename = (TextView) v.findViewById(R.id.cycleName);
        cyclequantity = (TextView) v.findViewById(R.id.cycleQuantity);
        rideButton = (Button) v.findViewById(R.id.rideButton);

    }
    public void bindToPost(Cycle cycle, View.OnClickListener rideClickListener) {
        cyclename.setText(cycle.getModel());
        cyclequantity.setText(cycle.getQuantity().toString());
        rideButton.setOnClickListener(rideClickListener);
    }
}

