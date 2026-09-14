package com.example.mybusmyapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class DriverProfile extends AppCompatActivity  {


    private Button logoutBtn,scanQRBtn,presentStudBtn;
    private TextView driverProfileTxt,busNumberText;
//    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;

    private TextView locationTextView;
    private Button locationBtn;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    private Double lat, lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);

        logoutBtn=findViewById(R.id.btnDriverLogout);
        scanQRBtn=findViewById(R.id.btnDriverScanQR);
        presentStudBtn=findViewById(R.id.btnDriverPresentStu);
        driverProfileTxt=findViewById(R.id.textDriverProfileName);
        busNumberText=findViewById(R.id.textDriverProfileBusNumber);


        locationBtn=findViewById(R.id.btnDriverLocation);
        driverProfileTxt.setText(getIntent().getStringExtra("drivername"));
        busNumberText.setText(getIntent().getStringExtra("busnumber"));



        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(DriverProfile.this, DriverLocation.class);
                    intent.putExtra("busnumber",getIntent().getStringExtra("busnumber").trim());
                    startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                Toast.makeText(DriverProfile.this, "Log out Successfully...", Toast.LENGTH_SHORT).show();
            }
        });

        //QR Code Scanner
        scanQRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator=new IntentIntegrator(DriverProfile.this);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setPrompt("Scan a QR Code");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
            }
        });

        presentStudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),DriverPresentStudent.class);
                intent.putExtra("drivername",driverProfileTxt.getText().toString());
                startActivity(intent);
            }
        });










//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setInterval(10000); // 10 seconds
//        locationRequest.setFastestInterval(5000); // 5 seconds
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//
//        LocationCallback locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                if (locationResult == null) {
//                    return;
//                }
//                for (Location location : locationResult.getLocations()) {
////                    locationTextView.setText("Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());
//                    Toast.makeText(DriverProfile.this, "Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
//                }
//            }
        }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode==LOCATION_PERMISSION_REQUEST_CODE){
//            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
////                showLocation();
//            }
//            else{
//                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        }
//    }

//        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);



    //QR Code Scanner
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult!=null){
//            intentResult("result",intentResult.toString());
            String da= intentResult.getContents();
            System.out.println(da);
            if (da==null){
                Toast.makeText(this, "Scan Cancel", Toast.LENGTH_SHORT).show();
            }
            else if(da!=null) {
                HashMap<String,String> param=new HashMap<String, String>();
                String[] scannedDataArray = da.split("//");
                showScannedDataDialog(da);
                param.put("drivername",driverProfileTxt.getText().toString());
                param.put("studentid",scannedDataArray[1]);
                //For Validation php file
                String rs=Network.connect("http://"+Network.IP+"/presentstudent.php",param);
            }

//            else

////                Toast.makeText(this, "Content"+da, Toast.LENGTH_SHORT).show();
//                // Store scanned data in SharedPreferences as a list
//                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                HashSet<String> scannedDataList= new HashSet<>();
//                if (sharedPreferences.contains("scannedData")) {
//                    String existingData = sharedPreferences.getString("scannedData", "");
//                    scannedDataList.addAll(Arrays.asList(existingData.split(",")));
//                }
//
//                if (!scannedDataList.contains(da)) {
//                    scannedDataList.add(da);
//                }
////                scannedDataList.remove();
//
//                sharedPreferences.edit().putString("scannedData", TextUtils.join(",", scannedDataList)).apply();




            // Retrieve existing scanned data from SharedPreferences
//                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                String existingDataString = sharedPreferences.getString("scannedData", "");
//
//                // Convert existing scanned data string into a list
//                List<String> scannedDataList = new ArrayList<>(Arrays.asList(existingDataString.split(",")));
//
//                // Add new scanned data to the list
//                scannedDataList.add(da);
//
//                // Convert list back to a string and save it in SharedPreferences
//                String newDataString = TextUtils.join(",", scannedDataList);
//                sharedPreferences.edit().putString("scannedData", newDataString).apply();
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.remove("scannedData"); // Remove the specific entry by key
//                editor.apply();
//            }
        }else{

            super.onActivityResult(requestCode, resultCode, data);
        }

    }
    //To show QR Code Successfully scanned
    private void showScannedDataDialog(String scannedData) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogview= LayoutInflater.from(this).inflate(R.layout.layout_popup_qr_scanned,null);
        builder.setView(dialogview);

        ImageView imageView=dialogview.findViewById(R.id.imageView);
        TextView textView=dialogview.findViewById(R.id.textView);

        //shows the dialog box to the driver  when the driver successfully scanned the student qr code
        AlertDialog dialog = builder.create();
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, 2000);
    }
}