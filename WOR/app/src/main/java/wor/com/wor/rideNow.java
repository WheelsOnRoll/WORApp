package wor.com.wor;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by bhavishya on 28-Dec-16.
 */

public class rideNow extends Activity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView zXingScannerView;
    TextView textView;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ridenow_layout);

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
