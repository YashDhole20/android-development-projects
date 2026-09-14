package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.HashMap;

public class MainActivity_AddTask extends AppCompatActivity {

    private EditText titleEditTxt, descriptionEditTxt, locationEditTxt;
    private Button saveBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_task);

        titleEditTxt = findViewById(R.id.editTxtTitle);
        descriptionEditTxt = findViewById(R.id.editTxtDescription);
        locationEditTxt = findViewById(R.id.editTxtLocation);

        saveBtn = findViewById(R.id.btnSave);
        backBtn = findViewById(R.id.btnBack);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(titleEditTxt.getText().toString())) {
                    titleEditTxt.setError("Please enter title");
                    titleEditTxt.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(descriptionEditTxt.getText().toString())) {
                    descriptionEditTxt.setError("Please enter description");
                    descriptionEditTxt.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(locationEditTxt.getText().toString())) {
                    locationEditTxt.setError("Please enter location");
                    locationEditTxt.requestFocus();
                    return;
                }
                HashMap<String, String> params = new HashMap<String, String>();

                params.put("title", titleEditTxt.getText().toString());
                params.put("description", descriptionEditTxt.getText().toString());
                params.put("location", locationEditTxt.getText().toString());
//                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                        .detectAll()
//                        .penaltyLog()
//                        .penaltyFlashScreen()
//                        .penaltyDeath()
//                        .build());
                String rs = Network.connect("http://" + Network.IP + "/addtaskfile.php",
                        params);
                System.out.println("resp" + rs);
                Toast.makeText( MainActivity_AddTask.this, "res" + rs, Toast.LENGTH_LONG).show();

                if (rs == "0") {
                    Toast.makeText( MainActivity_AddTask.this, "Wrong Info", Toast.LENGTH_LONG).show();

                } else if (rs != "0") {
                    Toast.makeText(MainActivity_AddTask.this, "Saved", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(MainActivity_AddTask.this, MainActivity_optionPage.class);
                    startActivity(in);
                }
            }
        });



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_AddTask.this, MainActivity_optionPage.class);
                startActivity(intent);
            }
        });
    }
//    class RegisterUser extends AsyncTask<Void, Void, String> {
//
//        private ProgressBar progressBar;
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            //creating request handler object
//            RequestHandler requestHandler = new RequestHandler();
//
//            //creating request parameters
//            HashMap<String,String> params = new HashMap<>();
//
//            params.put("title", titleEditTxt.getText().toString());
//            params.put("description",descriptionEditTxt .getText().toString());
//            params.put("location",locationEditTxt.getText().toString());
//
//            //returing the response
//
////                System.out.println("url" + URLs.URL_REGISTER);
//            return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
//        }
//        //            protected void onPreExecute() {
////                super.onPreExecute();
////                //displaying the progress bar while user registers on the server
////                progressBar = (ProgressBar) findViewById(R.id.progressBar);
////                progressBar.setVisibility(View.VISIBLE);
////            }
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            //hiding the progressbar after completion
////                progressBar.setVisibility(View.GONE);
//            System.out.println("sdddddd" + s);
//            try {
//                startActivity(new Intent(getApplicationContext(),MainActivity_optionPage.class));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    //executing the async task
//    RegisterUser ru = new RegisterUser();
//        ru.execute();


}






