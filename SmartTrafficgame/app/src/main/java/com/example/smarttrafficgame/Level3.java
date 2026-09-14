package com.example.smarttrafficgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Level3 extends AppCompatActivity {

    TextView scoreTextView;
    Button btn;
    int  marks;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);
        scoreTextView = findViewById(R.id.score2);
        btn = findViewById(R.id.nxtbtn2);
        String id = getIntent().getStringExtra("id");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (marks < 3) {
                  showScannedDataDialog();

                } else {
                    Intent intent = new Intent(getApplicationContext(), Certi3.class);
                    intent.putExtra("id", id);
                    System.out.println(id);
                    startActivity(intent);
                    finish();

                }
            }
        });
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("user_id", id.trim());
        System.out.println(id);
        String rs = Network.connect("http://" + Network.IP + "/level3.php", p);

        List<String> buses = Arrays.asList(rs.trim().split("#"));


        String n=buses.get(0);
        String[] parts = n.split("<br>");

        scoreTextView.setText("Your Score: " +  parts[1]+"/"+parts[0]);
        marks= Integer.parseInt(parts[1]);
        // Call AsyncTask to fetch answers from the server and calculate the score

    }
    private void showScannedDataDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogview= LayoutInflater.from(this).inflate(R.layout.fail_pop_up,null);
        builder.setView(dialogview);

        ImageView imageView=dialogview.findViewById(R.id.imageView);
        TextView textView=dialogview.findViewById(R.id.textView);
        String id = getIntent().getStringExtra("id");
        //shows the dialog box to the driver  when the driver successfully scanned the student qr code
        AlertDialog dialog = builder.create();
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    Toast.makeText(Level3.this, "YOU ARE FAIL SECOND EXAM", Toast.LENGTH_SHORT).show();
                    HashMap<String ,String > p2=new HashMap<String ,String>();
                    p2.put("user_id",id.trim());
                    System.out.println(id);
                    String rs2=Network.connect("http://"+Network.IP+"/delete_level3.php",p2);
                    Intent intent2 = new Intent(getApplicationContext(), YTVideo2.class);
                    intent2.putExtra("id", id);
                    System.out.println(id);
                    startActivity(intent2);
                    finish();

                    dialog.dismiss();
                }
            }
        }, 2000);
    }

}