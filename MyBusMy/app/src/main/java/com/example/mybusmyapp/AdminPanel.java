package com.example.mybusmyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class AdminPanel extends AppCompatActivity  {

    private Button busDetailsBtn,studDetailsBtn ,allBusBtn,studReportBtn,busReportBtn,logoutBtn;
    private ImageButton studentCloseBtn,busCloseBtn;
    private Button  addBusBtn,viewBusBtn,deleteBusBtn;
    private Button addStudentBtn,viewStudentBtn,deleteStudentBtn;


    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        //Main btn
        busDetailsBtn=findViewById(R.id.btnBusDetails);
        studDetailsBtn=findViewById(R.id.btnStudDetails);

        allBusBtn=findViewById(R.id.btnViewAllBus);
        studReportBtn=findViewById(R.id.btnStudReport);
        busReportBtn=findViewById(R.id.btnBusReport);
        logoutBtn=findViewById(R.id.btnAdminLogout);






        busDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(AdminPanel.this);
                dialog.setContentView(R.layout.bus_details);
                  busCloseBtn  = dialog.findViewById(R.id.imageBackToPanelBus);

                //Bus timing btn
                addBusBtn=dialog.findViewById(R.id.btnAddBus);
                viewBusBtn=dialog.findViewById(R.id.btnViewBus);
                deleteBusBtn=dialog.findViewById(R.id.btnDeleteBus);


                 busCloseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                addBusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), AddBusTiming.class));
                        dialog.dismiss();
                        finish();
                    }
                });
                 viewBusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), ViewBusDetails.class));
                        dialog.dismiss();

                    }
                });
                 deleteBusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), DeleteBusTiming.class));
                        dialog.dismiss();
                    }
                });
            }
        });
        studDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(AdminPanel.this);
                dialog.setContentView(R.layout.student_details);

                //Student   btn
                addStudentBtn=dialog.findViewById(R.id.btnAddStudent);
                viewStudentBtn=dialog.findViewById(R.id.btnViewStudent);
                deleteStudentBtn=dialog.findViewById(R.id.btnDeleteStudent);
                  studentCloseBtn = dialog.findViewById(R.id.imageBackToPanelStudent);
                studentCloseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                 addStudentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), AddStudentDetails.class));
                        dialog.dismiss();
                        finish();
                    }
                });
                viewStudentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), ViewStudentDetails.class));
                        dialog.dismiss();
                    }
                });
                deleteStudentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), DeleteStudentDetails.class));
                        dialog.dismiss();
                    }
                });
            }
        });

         studReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AdminPresentStudent.class);
                startActivity(intent);
            }
        });
         busReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AdminBusReported.class);
                startActivity(intent);
            }
        });

          logoutBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  startActivity(new Intent(getApplicationContext(), AdminLogin.class));
                  Toast.makeText(AdminPanel.this, "Log out Successfully...", Toast.LENGTH_SHORT).show();
                  finish();
              }
          });

//          if(ContextCompat.checkSelfPermission(AdminPanel.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//              ActivityCompat.requestPermissions(AdminPanel.this,new String[]{
//                      Manifest.permission.ACCESS_FINE_LOCATION
//              },100);
//        }

        allBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPanel.this, ViewDriver.class);
                startActivity(intent);
            }
        });

    }
//    @SuppressLint("MissingPermission")
//    private  void getLocation1() {
//        try {
//            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, AdminPanel.this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onLocationChanged(@NonNull Location location) {
//        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
//        try {
//            Geocoder geocoder=new Geocoder(AdminPanel.this, Locale.getDefault());
//            List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
//
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onLocationChanged(@NonNull List<Location> locations) {
//        LocationListener.super.onLocationChanged(locations);
//    }
//
//    @Override
//    public void onFlushComplete(int requestCode) {
//        LocationListener.super.onFlushComplete(requestCode);
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//        LocationListener.super.onStatusChanged(provider, status, extras);
//    }
//
//    @Override
//    public void onProviderEnabled(@NonNull String provider) {
//        LocationListener.super.onProviderEnabled(provider);
//    }
//
//    @Override
//    public void onProviderDisabled(@NonNull String provider) {
//        LocationListener.super.onProviderDisabled(provider);
//    }
}