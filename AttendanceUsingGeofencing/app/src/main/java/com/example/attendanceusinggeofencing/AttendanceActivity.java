package com.example.attendanceusinggeofencing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.StrictMode;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.test.mock.MockPackageManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AttendanceActivity extends AppCompatActivity {

    Button btngive,btnprofile;
    String location, latlong;
    static String city;
    String uid, busno;
    double lat, lng;
    Button stopButton;
//    ImageView emergencyButton;
    List<Address> addresses;

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    //    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        btngive=(Button)findViewById(R.id.btngive);
        btnprofile=(Button)findViewById(R.id.btnprofile);


        btngive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> param = new HashMap<String, String>();
                param.put("lat", Double.toString(lat));
                param.put("lon", Double.toString(lng));
                param.put("uid",LoginActivity.uuid);
                StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(sb);
                String id = Network.connect("http://" + Network.IP + "/add_location.php", param);
                id = id.trim();
                if (id.equals("0")) {
                    // Toast.makeText(getApplicationContext(), "Registration unsuccessfully", Toast.LENGTH_LONG).show();
                } else if (!id.equals("1")) {
                    //   Toast.makeText(getApplicationContext(), "Registration successfull", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);

                }

//                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
//                startActivity(intent);
            }
        });

        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }
        });
        try {
            new mylocationlistener();
            getGPSLocation(uid, busno);
            Toast.makeText(getApplicationContext(), " location " + location + " ",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(AttendanceActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
//                ActivityCompat.requestPermissions(getApplicationContext(),
//                        new String[]{Manifest.permission.READ_CONTACTS},
//                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted

        }
    }

    public void getGPSLocation(String uid1, String busno1) throws IOException {

        String location = "";


        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location loc = lm
                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        LocationListener ll = new mylocationlistener();
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10,
                ll);
        if (loc != null) {
            lat = loc.getLatitude();
            lng = loc.getLongitude();

            location = lat + "," + lng;

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(lat, lng, 1);
                Address ads=addresses.get(0);
                Toast.makeText(getApplicationContext(), " city name" + ads.getAddressLine(0),
                        Toast.LENGTH_LONG).show();

//                HashMap<String, String> param = new HashMap<String, String>();
//                param.put("lat", String.valueOf(lat));
//                param.put("lng", String.valueOf(lng));
//                param.put("password", editpass.getText().toString());

//                System.out.println("http://"+Network.IP +"/Tra/maptest.php?lat="+lat+"&lng="+lng);
//                wv1.getSettings().setLoadsImagesAutomatically(true);
//                wv1.getSettings().setJavaScriptEnabled(true);
//                wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//                wv1.loadUrl("http://"+Network.IP +"/Tra/maptest.php?lat="+lat+"&lng="+lng);
//                Log.d("data", "http://"+Network.IP +"/Tra/maptest.php?lat="+lat+"lng="+lng);
//                StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                StrictMode.setThreadPolicy(sb);
//                String id = Network.connect("http://"+Network.IP +"/Tra/maptest.php", param);
//                Log.d("url","http://" + Network.IP + "/Tra/maptest.php");
//                id = id.trim();
                city=ads.getAddressLine(0).toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//			city = addresses.get(0).getAddressLine(1);



            // String cityName = addresses.get(0).getAddressLine(0);
        }
//		HashMap<String, String> pm = new HashMap<String, String>();
//		pm.put("uid", uid1);
//		pm.put("busid", busno1);
//		pm.put("ulocation", city);
//		String rs = Network.connect("http://" + Network.IP + "/TrackBus", pm);
//		rs = rs.trim();
//
//		if (rs.equals("0")) {
//			Toast.makeText(getApplicationContext(), " it takes time ",
//					Toast.LENGTH_SHORT).show();
//		} else if (!rs.equals("0")) {
//
//			Toast.makeText(getApplicationContext(), " bus track '" + rs + "' ",
//					Toast.LENGTH_SHORT).show();
//
//		}
    }
//    private class MyBrowser extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//    }
    private class mylocationlistener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {

            if (location != null) {
                Log.d("LOCATION CHANGED", location.getLatitude() + "");
                Log.d("LOCATION CHANGED", location.getLongitude() + "");
                String str = "\n CurrentLocation: " + "\n Latitude: "
                        + location.getLatitude() + "\n Longitude: "
                        + location.getLongitude();

                String locationStr = location.getLatitude() + ","
                        + location.getLongitude();

                Geocoder geocoder = new Geocoder(AttendanceActivity.this, Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    Address ads=addresses.get(0);
                    Toast.makeText(getApplicationContext(), " city name" + ads.getAddressLine(0),
                            Toast.LENGTH_LONG).show();
                    city=ads.getAddressLine(0).toString();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//                location=locationStr;
                // // Toast.makeText(getApplicationContext(),
                // // "location is '"+str+"'",Toast.LENGTH_LONG).show();
                // Toast.makeText(getApplicationContext(),
                // "location is '" + locationStr + "'", Toast.LENGTH_LONG)
                // .show();
            }

        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

    }
}
