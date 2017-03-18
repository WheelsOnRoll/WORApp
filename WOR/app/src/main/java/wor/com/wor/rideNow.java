package wor.com.wor;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class rideNow extends Activity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView zXingScannerView;
    TextView textView;
    String text;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ridenow_layout);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        scanqrcode();
        /*zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();*/
    }

    void scanqrcode()
    {
        zXingScannerView = new ZXingScannerView(rideNow.this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(rideNow.this);
        zXingScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        zXingScannerView.stopCamera();
        setContentView(R.layout.ridenow_layout);
        text = result.getText();
        if(text.equals("CODE")){
            long time = System.currentTimeMillis();
            mDatabase.child("test").child("anything"+time).setValue("true");
        }
        textView = (TextView)findViewById(R.id.qrCodeDetail);
        textView.setText(text);
        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
                
            }

            public void onFinish() {
                finish();
            }
        }.start();
    }
}
