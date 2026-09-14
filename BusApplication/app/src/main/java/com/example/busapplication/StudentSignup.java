package com.example.busapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class StudentSignup extends AppCompatActivity {

    private TextInputEditText fnameEdit, addressEdit, usernameEdit, passwordEdit, mobileEdit;
    private Button signupBtn;
    private TextView loginTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        fnameEdit = findViewById(R.id.editStudSignupName);
        addressEdit = findViewById(R.id.editStudSignupAddress);
        usernameEdit = findViewById(R.id.editStudSignupUsername);
        passwordEdit = findViewById(R.id.editStudSignupPassword);
        mobileEdit = findViewById(R.id.editStudSignupMobile);
        signupBtn = findViewById(R.id.btnStudSignup);
        loginTxt = findViewById(R.id.textgotoLogin);

        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentSignup.this, StudentLogin.class);
                startActivity(intent);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                Toast.makeText(StudentSignup.this, "sign up", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    @SuppressLint("NewApi")
//    public Connection connectionclass(){
//        Connection con=null;
//        String ip="127.0.0.1",port="3306",username="",password="",databasename="mybusmyapp";
//
//        StrictMode.ThreadPolicy tp=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(tp);
//
//        try {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";User=" + username + ";password=" + password + ";";
//            con = DriverManager.getConnection(connectionUrl);
//        }catch (Exception e){
//            Log.e("Error",e.getMessage());
//
//        }
//        return con;
//    }

    private void registerUser() {
        final String name = fnameEdit.getText().toString().trim();
        final String address = addressEdit.getText().toString().trim();
        final String email = usernameEdit.getText().toString().trim();
        final String password = passwordEdit.getText().toString().trim();
        final String mobile = mobileEdit.getText().toString().trim();


        if (TextUtils.isEmpty(name)) {
            fnameEdit.setError("Please enter name");
            fnameEdit.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            addressEdit.setError("Please enter address");
            addressEdit.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            usernameEdit.setError("Please enter your email");
            usernameEdit.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            usernameEdit.setError("Enter a valid email");
            usernameEdit.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            mobileEdit.setError("Enter a Mobile No");
            mobileEdit.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEdit.setError("Enter a password");
            passwordEdit.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyFlashScreen()
                .penaltyDeath()
                .build());
        params.put("name", name);
        params.put("password", password);
        params.put("email", email);
        params.put("mob", mobile);
        params.put("address", address);

        //returing the response

        String id = Network.connect("http://" + Network.IP + "/register.php", params);
        System.out.println("resp" + id);
        if (id.trim().equals("0")) {
            Toast.makeText(getApplicationContext(), "Wrong Info", Toast.LENGTH_LONG).show();

        } else if (id.trim() != "0") {
            Intent in = new Intent(getApplicationContext(), StudentLogin.class);
            startActivity(in);
        }
        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();

                params.put("name", name);
                params.put("password", password);
                params.put("email", email);
                params.put("mob", mobile);
                params.put("address", address);

                //returing the response
                String id = Network.connect("http://" + Network.IP+"/register.php", params);
                System.out.println("resp" + id);
//                System.out.println("url" + URLs.URL_REGISTER);
                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
            }
//            protected void onPreExecute() {
//                super.onPreExecute();
//                //displaying the progress bar while user registers on the server
//                progressBar = (ProgressBar) findViewById(R.id.progressBar);
//                progressBar.setVisibility(View.VISIBLE);
//            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);
                System.out.println("sdddddd" + s);
                try {

                    startActivity(new Intent(getApplicationContext(), StudentLogin.class));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }
}
