package com.myandroidproject.ecochargehub;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.HashMap;

public class station_registration extends AppCompatActivity {

    EditText nameEditText, addressEditText, cityEditText, talukaEditText, districtEditText, openTimeEditText, closeTimeEditText, mobileEditText, passwordEditText, powerEditText;
    Button submitButton;
    TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_registration);

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        cityEditText = findViewById(R.id.cityEditText);
        talukaEditText = findViewById(R.id.talukaEditText);
        districtEditText = findViewById(R.id.districtEditText);
        openTimeEditText = findViewById(R.id.openTimeEditText);
        closeTimeEditText = findViewById(R.id.closeTimeEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        powerEditText = findViewById(R.id.powerEditText);
        submitButton = findViewById(R.id.submitButton);
        registerText = findViewById(R.id.registerText);

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open registration page when clicked
                Intent intent = new Intent(station_registration.this, station_login.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        openTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOpenTimePicker();
            }
        });

        closeTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCloseTimePicker();
            }
        });
    }

    private void showOpenTimePicker() {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        openTimeEditText.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);

        timePickerDialog.show();
    }

    private void showCloseTimePicker() {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        closeTimeEditText.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);

        timePickerDialog.show();
    }

    private void registerUser() {
        String name = nameEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String city = cityEditText.getText().toString().trim();
        String taluka = talukaEditText.getText().toString().trim();
        String district = districtEditText.getText().toString().trim();
        String opentime = openTimeEditText.getText().toString().trim();
        String closetime = closeTimeEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String power = powerEditText.getText().toString().trim();


        if (TextUtils.isEmpty(name)) {
            nameEditText.setError("Enter a station name");
            nameEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            addressEditText.setError("Enter a Address");
            addressEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(city)) {
            cityEditText.setError("Enter your city");
            cityEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(taluka)) {
            talukaEditText.setError("Enter your taluka");
            talukaEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(district)) {
            districtEditText.setError("Enter your district");
            districtEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(opentime)) {
            openTimeEditText.setError("Enter your district");
            openTimeEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(closetime)) {
            closeTimeEditText.setError("Enter your district");
            closeTimeEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mobile)) {
            mobileEditText.setError("Enter your mobile number");
            mobileEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Please enter a password");
            passwordEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(power)) {
            powerEditText.setError("Enter your district");
            powerEditText.requestFocus();
            return;
        }

        // Register the user
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("address", address);
        params.put("city", city);
        params.put("taluka", taluka);
        params.put("district", district);
        params.put("opentime", opentime);
        params.put("closetime", closetime);
        params.put("password", password);
        params.put("mobile", mobile);
        params.put("power", power);


        String response = Net.connect("http://" + Net.IP + "/station_registration.php", params);

        if (response != null) {
            if (response.trim().equals("approval is pending")) {
                Toast.makeText(getApplicationContext(), "Approval is pending. You cannot log in yet.", Toast.LENGTH_LONG).show();
            } else if (response.trim().equals("0")) {
                Toast.makeText(getApplicationContext(), "Registration failed. Please check your information.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Registration successful. You can now log in.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(station_registration.this, station_login.class);
                startActivity(intent);
                finish(); // Prevents going back to registration screen with back button
            }
        } else {
            Toast.makeText(getApplicationContext(), "Error connecting to server. Please try again later.", Toast.LENGTH_LONG).show();
        }
    }
}































//package com.myandroidproject.ecochargehub;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.HashMap;
//
//public class station_registration extends AppCompatActivity {
//
//    EditText nameEditText, addressEditText, cityEditText, talukaEditText, districtEditText, openTimeEditText, closeTimeEditText, mobileEditText, passwordEditText, powerEditText;
//    Button submitButton;
//    TextView registerText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_station_registration);
//
//        // Initialize views
//        nameEditText = findViewById(R.id.nameEditText);
//        addressEditText = findViewById(R.id.addressEditText);
//        cityEditText = findViewById(R.id.cityEditText);
//        talukaEditText = findViewById(R.id.talukaEditText);
//        districtEditText = findViewById(R.id.districtEditText);
//        openTimeEditText = findViewById(R.id.openTimeEditText);
//        closeTimeEditText = findViewById(R.id.closeTimeEditText);
//        mobileEditText = findViewById(R.id.mobileEditText);
//        passwordEditText = findViewById(R.id.passwordEditText);
//        powerEditText = findViewById(R.id.powerEditText);
//        submitButton = findViewById(R.id.submitButton);
//        registerText = findViewById(R.id.registerText);
//
//        registerText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Open registration page when clicked
//                Intent intent = new Intent(station_registration.this, station_login.class);
//                startActivity(intent);
//            }
//        });
//
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                registerUser();
//            }
//        });
//    }
//
//    private void registerUser() {
//        String name = nameEditText.getText().toString().trim();
//        String address = addressEditText.getText().toString().trim();
//        String city = cityEditText.getText().toString().trim();
//        String taluka = talukaEditText.getText().toString().trim();
//        String district = districtEditText.getText().toString().trim();
//        String opentime = openTimeEditText.getText().toString().trim();
//        String closetime = closeTimeEditText.getText().toString().trim();
//        String password = passwordEditText.getText().toString().trim();
//        String mobile = mobileEditText.getText().toString().trim();
//        String power = powerEditText.getText().toString().trim();
//
//
//        if (TextUtils.isEmpty(name)) {
//            nameEditText.setError("Enter a station name");
//            nameEditText.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(address)) {
//            addressEditText.setError("Enter a Address");
//            addressEditText.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(city)) {
//            cityEditText.setError("Enter your city");
//            cityEditText.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(taluka)) {
//            talukaEditText.setError("Enter your taluka");
//            talukaEditText.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(district)) {
//            districtEditText.setError("Enter your district");
//            districtEditText.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(opentime)) {
//            openTimeEditText.setError("Enter your district");
//            openTimeEditText.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(closetime)) {
//            closeTimeEditText.setError("Enter your district");
//            closeTimeEditText.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(mobile)) {
//            mobileEditText.setError("Enter your mobile number");
//            mobileEditText.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(password)) {
//            passwordEditText.setError("Please enter a password");
//            passwordEditText.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(power)) {
//            powerEditText.setError("Enter your district");
//            powerEditText.requestFocus();
//            return;
//        }
//
//        // Register the user
//        HashMap<String, String> params = new HashMap<>();
//        params.put("name", name);
//        params.put("address", address);
//        params.put("city", city);
//        params.put("taluka", taluka);
//        params.put("district", district);
//        params.put("opentime", opentime);
//        params.put("closetime", closetime);
//        params.put("password", password);
//        params.put("mobile", mobile);
//        params.put("power", power);
//
//
//        String response = Net.connect("http://" + Net.IP + "/station_registration.php", params);
//
//        if (response != null) {
//            if (response.trim().equals("approval is pending")) {
//                Toast.makeText(getApplicationContext(), "Approval is pending. You cannot log in yet.", Toast.LENGTH_LONG).show();
//            } else if (response.trim().equals("0")) {
//                Toast.makeText(getApplicationContext(), "Registration failed. Please check your information.", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "Registration successful. You can now log in.", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(station_registration.this, station_login.class);
//                startActivity(intent);
//                finish(); // Prevents going back to registration screen with back button
//            }
//        } else {
//            Toast.makeText(getApplicationContext(), "Error connecting to server. Please try again later.", Toast.LENGTH_LONG).show();
//        }
//    }
//}


//    public void showOpenTimePicker(View view) {
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
//                (timePicker, hourOfDay, minute) -> {
//                    // Handle the selected time
//                    // Update the EditText with the selected time
//                },
//                // Default values for the time picker dialog
//                12, 0, false);
//
//        timePickerDialog.show();
//    }
//
//    public void showCloseTimePicker(View view) {
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
//                (timePicker, hourOfDay, minute) -> {
//                    // Handle the selected time
//                    // Update the EditText with the selected time
//                },
//                // Default values for the time picker dialog
//                12, 0, false);
//
//        timePickerDialog.show();
//    }
