package com.example.qrcode;

import static android.widget.Toast.makeText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            String da = intentResult.getContents();
            System.out.println(da);
            if (da == null) {
                makeText(this, "Scan Cancel", Toast.LENGTH_SHORT).show();
            }
            String[] scannedDataArray = da.split("//");
            if (scannedDataArray.length != 6) {
                // If the scanned data is not in the expected format, show toast for invalid QR code
                invalidScanner();

            } else {
                // Process the scanned data further
                String userId = scannedDataArray[0];
                HashMap<String, String> params = new HashMap<>();
                params.put("user_id", userId);
                String response = Network.connect("http://" + Network.IP + "/scanQr.php", params);
                if (response.trim().equals("1")) {
                    alreadyScanned();


                } else if (response.trim().equals("11")) {
                    showScannedDataDialog(da);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void alreadyScanned() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogview = LayoutInflater.from(this).inflate(R.layout.layout_already_present, null);
        builder.setView(dialogview);

        ImageView imageView = dialogview.findViewById(R.id.imageView2);
        TextView textView = dialogview.findViewById(R.id.textView2);

        //shows the dialog box to the driver  when the driver successfully scanned the student qr code
        AlertDialog dialog = builder.create();
        dialog.show();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    makeText(MainActivity.this, "Already Scanned", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    initiateScan();
                }
            }
        }, 2000);
    }

    private void invalidScanner() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogview = LayoutInflater.from(this).inflate(R.layout.layout_invalid_scanner, null);
        builder.setView(dialogview);

        ImageView imageView = dialogview.findViewById(R.id.imageView1);
        TextView textView = dialogview.findViewById(R.id.textView1);

        //shows the dialog box to the driver  when the driver successfully scanned the student qr code
        AlertDialog dialog = builder.create();
        dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    makeText(MainActivity.this, "Invalid QR", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                    initiateScan();
                }
            }
        }, 2000);
    }

    //To show QR Code Successfully scanned
    private void showScannedDataDialog(String scannedData) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogview = LayoutInflater.from(this).inflate(R.layout.layout_popup_qr_scanned, null);
        builder.setView(dialogview);

        ImageView imageView = dialogview.findViewById(R.id.imageView);
        TextView textView = dialogview.findViewById(R.id.textView);

        //shows the dialog box to the driver  when the driver successfully scanned the student qr code
        AlertDialog dialog = builder.create();
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    initiateScan();
                }
            }
        }, 2000);
    }

    private void initiateScan() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setPrompt("Scan a QR Code");
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        intentIntegrator.initiateScan();
    }
}
