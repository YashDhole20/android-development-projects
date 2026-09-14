package com.apti.smartaptitude;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    TextView scoreTextView;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreTextView = findViewById(R.id.scoreView);
        btn = findViewById(R.id.nextbtn);
        btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent rs = new Intent(ResultActivity.this, Dashboard.class);
        String userId = getIntent().getStringExtra("UserID");
        rs.putExtra("UserID",userId);
        startActivity(rs);
        }
    });
        String userId = getIntent().getStringExtra("UserID");
        String com=getIntent().getStringExtra("com_id");
        String job=getIntent().getStringExtra("job_nm");
        HashMap<String,String> p=new HashMap<String, String>();
        p.put("userid",userId.trim());
        p.put("company_id",com);
        p.put("job_nm",job.trim());
        System.out.println(com +" com");
        System.out.println(job +" job");
        String rs=Network.connect("http://"+Network.IP+"/get_correct_answers.php",p);

        List<String> buses = Arrays.asList(rs.trim().split("#"));

        List<String[]> busDataList = new ArrayList<>();

        for (String busString : buses) {
            String[] busData = busString.trim().split("<br>"); // Assuming each bus data string is comma-separated
            busDataList.add(busData);
        }
        String firstString = null;
        if (!busDataList.isEmpty()) { // Check if busDataList is not empty
            String[] firstBusData = busDataList.get(0); // Get the first element of busDataList
            if (firstBusData.length > 0) { // Check if the first element contains at least one string
                firstString = firstBusData[0]; // Access the first string
                System.out.println(firstString); // Print or use the first string
            }
        }
        System.out.println(buses+"11111");
        System.out.println(busDataList+"0000");
        System.out.println("Your Score: " + firstString);

        scoreTextView.setText("Your Score: " + firstString);

    }

}



