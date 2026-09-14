//package com.example.mybusmyapp;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.IBinder;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationResult;
//
//public class LocationServices extends Service {
//    private LocationCallback locationCallback=new LocationCallback() {
//        @Override
//        public void onLocationResult(@NonNull LocationResult locationResult) {
//            super.onLocationResult(locationResult);
//            if (locationResult!= null && locationResult.getLastLocation()!=null){
//                double latitude=locationResult.getLastLocation().getLatitude();
//                double longitude=locationResult.getLastLocation().getLongitude();
//                Log.d("LOCATION_UPDATE",latitude+" ,"+longitude);
//            }
//        }
//    };
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
//
////    private void stat
//}
