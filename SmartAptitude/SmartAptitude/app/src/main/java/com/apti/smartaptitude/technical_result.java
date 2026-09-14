package com.apti.smartaptitude;

import android.annotation.SuppressLint;
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

public class technical_result extends AppCompatActivity {

    TextView scoreTextView;
    Button btn;

     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.technical_result);

        scoreTextView = findViewById(R.id.score);
        btn = findViewById(R.id.btnresult);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rs = new Intent(technical_result.this, Dashboard.class);
                String userId = getIntent().getStringExtra("UserID");
                rs.putExtra("UserID",userId);
                startActivity(rs);
            }
        });

        // Call AsyncTask to fetch answers from the server and calculate the score
//        new GetCorrectAnswersTask().execute();
         String userId = getIntent().getStringExtra("UserID");
         String com=getIntent().getStringExtra("com_id");
         String job=getIntent().getStringExtra("job_nm");
         HashMap<String,String> p=new HashMap<String, String>();
         p.put("user_id",userId.trim());
         p.put("company_id",com);
         p.put("job_nm",job.trim());
         System.out.println(com +" com");
         System.out.println(job +" job");
        String rs=Network.connect("http://"+Network.IP+"/tech_correct_ans.php",p);

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

//    private class GetCorrectAnswersTask extends AsyncTask<Void, Void, String> {
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            try {
//                String userId = getIntent().getStringExtra("UserID");
//                String com=getIntent().getStringExtra("com_id");
//                URL url = new URL("http://192.168.1.4/Smartaptitude/tech_correct_ans.php?user_id="+userId+"&company_id="+com);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//                connection.connect();
//                int responseCode = connection.getResponseCode();
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    StringBuilder response = new StringBuilder();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        response.append(line);
//                    }
//                    reader.close();
//                    return response.toString();
//                } else {
//                    Log.e("HTTP_ERROR", "HTTP error code: " + responseCode);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }

//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//            if (result != null) {
//                try {
//                    JSONObject jsonObject = new JSONObject(result);
//                    int answerCount = jsonObject.getInt("answer_count");
//                    int correctAnswersCount = jsonObject.getInt("correct_answers_count");
//                    System.out.println(correctAnswersCount);
//                    System.out.println(answerCount);
//                    // Assuming you have answerCount and correctAnswersCount
//                    // Calculate score or display any relevant information
//                    // For example:
//                    int score =  correctAnswersCount;
//                    scoreTextView.setText("Your Score: " + score+"/"+answerCount);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }

}
