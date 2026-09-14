package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionBarPolicy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.security.auth.callback.Callback;

public class ShowQRCode extends AppCompatActivity {


    private ImageView qrCodeTv;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_qrcode);

        qrCodeTv = findViewById(R.id.imageQRCode);

        // Retrieve student ID from intent
        id = getIntent().getStringExtra("studentid");

        HashMap<String, String> param = new HashMap<>();
        param.put("studentid", id);

        // Assuming Network.connect() returns a Bitmap representing the QR code
         Bitmap bitm = Network.connectForImage("http://" + Network.IP + "/studentqr.php", param);
        qrCodeTv.setImageBitmap(bitm);
//        System.out.println(bitmap+"world");
//        if (bitmap != null) {
//            // Use the Bitmap object (e.g., display it in an ImageView)
//            qrCodeTv.setImageBitmap(bitmap);
//        } else {
//            Toast.makeText(this, "Error Occurs To load the Q", Toast.LENGTH_SHORT).show();
//        }
    }
    public static Bitmap stringToBitmap(String encodedString) {
        try {
            // Decode the string into a byte array
            byte[] decodedBytes = Base64.decode(encodedString, Base64.DEFAULT);
            // Decode the byte array into a Bitmap
            System.out.println(decodedBytes+"hello");
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}