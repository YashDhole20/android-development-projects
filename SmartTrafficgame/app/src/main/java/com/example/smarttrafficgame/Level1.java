package com.example.smarttrafficgame;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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


public class Level1 extends AppCompatActivity {

    TextView scoreTextView;
    Button btn;
    int  marks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        scoreTextView = findViewById(R.id.score);
        btn = findViewById(R.id.nxtbtn);
        String id=getIntent().getStringExtra("id");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (marks < 3) {

                  showScannedDataDialog();

                } else {
                    Intent intent = new Intent(getApplicationContext(), Certificate.class);
                    intent.putExtra("id", id);
                    System.out.println(id);
                    startActivity(intent);
                    finish();

                }
            }
        });

        HashMap<String ,String > p=new HashMap<String ,String>();
        p.put("user_id",id.trim());
        System.out.println(id);
        String rs=Network.connect("http://"+Network.IP+"/level1_correct_ans.php",p);

        List<String> buses = Arrays.asList(rs.trim().split("#"));
        String n=buses.get(0);
        String[] parts = n.split("<br>");
        System.out.println(buses+"11111");
        scoreTextView.setText("Your Score: " +  parts[1]+"/"+parts[0]);
        marks= Integer.parseInt(parts[1]);

    }

//    private class GetCorrectAnswersTask extends AsyncTask<Void, Void, String> {
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            try {
//                URL url = new URL("http://192.168.1.10/SmartTrafficgame/level1_correct_ans.php");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//                connection.connect();
//
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
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//            if (result != null) {
//                try {
//                    JSONObject jsonObject = new JSONObject(result);
//                    int answerCount = jsonObject.getInt("answer_count");
//                    int correctAnswersCount = jsonObject.getInt("correct_answers_count");
//
//                    // Assuming you have answerCount and correctAnswersCount
//                    // Calculate score or display any relevant information
//                    // For example:
//                    int score =  correctAnswersCount;
//                    scoreTextView.setText("Your Score: " + score+"/"+answerCount);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
////
// }
private void showScannedDataDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    View dialogview= LayoutInflater.from(this).inflate(R.layout.fail_pop_up,null);
    builder.setView(dialogview);

    ImageView imageView=dialogview.findViewById(R.id.imageView);
    TextView textView=dialogview.findViewById(R.id.textView);
    String id=getIntent().getStringExtra("id");
    //shows the dialog box to the driver  when the driver successfully scanned the student qr code
    AlertDialog dialog = builder.create();
    dialog.show();
    Toast.makeText(Level1.this, "YOU ARE FAIL FIRST EXAM", Toast.LENGTH_SHORT).show();
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            if (dialog != null && dialog.isShowing()) {

                Intent intent=new Intent(Level1.this, YoutubeViews.class);
                HashMap<String ,String > p1=new HashMap<String ,String>();
                p1.put("user_id",id.trim());
                System.out.println(id);
                String rs1=Network.connect("http://"+Network.IP+"/delete_level1.php",p1);
                Intent intent1 = new Intent(getApplicationContext(),  YoutubeViews.class);
                intent1.putExtra("id", id);
                System.out.println(id);
                startActivity(intent1);
                dialog.dismiss();
                finish();

            }
        }
    }, 2000);
}

}
