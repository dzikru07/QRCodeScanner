package com.example.qrcodescanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button scanBtn;
    private TextView scantype, scanformat;
    private LinearLayout llsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = (Button) findViewById(R.id.scanButton);
        scantype = (TextView) findViewById(R.id.scanType);
        scanformat = (TextView) findViewById(R.id.scanFormat);
        llsearch = (LinearLayout) findViewById(R.id.llsearch);
        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        llsearch.setVisibility(View.GONE);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Scan Code");
        integrator.setOrientationLocked(false);
        integrator.setCaptureActivity(CaptureActivity.class);
        integrator.initiateScan();
        integrator.setBeepEnabled(true);
        integrator.setCameraId(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() == null){
                llsearch.setVisibility(View.GONE);
                Toast.makeText(this, "Keluar", Toast.LENGTH_LONG).show();
            } else {
                llsearch.setVisibility(View.VISIBLE);
                scanformat.setText(result.getContents());
                scantype.setText(result.getFormatName());
            }
        } else {
            super.onActivityResult(requestCode,resultCode, data);
        }
    }
}