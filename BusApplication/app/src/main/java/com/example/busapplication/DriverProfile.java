package com.example.busapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class DriverProfile extends AppCompatActivity {

    private Button logoutBtn,scanQRBtn,presentStudBtn;
    private TextView driverProfileTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);

        logoutBtn=findViewById(R.id.btnDriverLogout);
        scanQRBtn=findViewById(R.id.btnDriverScanQR);
        presentStudBtn=findViewById(R.id.btnDriverPresentStu);
        driverProfileTxt=findViewById(R.id.textDriverProfileName);



        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        scanQRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IntentIntegrator intentIntegrator=new IntentIntegr
//
//                intentIntegrator.setOrientationLocked(true);
//                intentIntegrator.setPrompt("Scan a QR Code");
//                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
//                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,requestCode,data);
        if(intentResult!=null){
//            intentResult("result",intentResult.toString());
            String da= intentResult.getContents();;
            if (intentResult.getContents()==null){
                Toast.makeText(this, "Cancel"+da, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Content"+da, Toast.LENGTH_SHORT).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}