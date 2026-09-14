package com.example.attendanceusinggeofencing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;

public class StatusActivity extends AppCompatActivity {

    RadioGroup rgdoc1;
    RadioButton rbtn3;
    Button btnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        btnsubmit = (Button)findViewById(R.id.btnsubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.btnsubmit:
                        rgdoc1 = (RadioGroup) findViewById(R.id.rgdoc1);
                        int i = rgdoc1.getCheckedRadioButtonId();
                        rbtn3 = (RadioButton)findViewById(i);


                        HashMap<String, String> param = new HashMap<String, String>();
                        param.put("status", rbtn3.getText().toString());

                        StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(sb);
                        String id = Network.connect("http://" + Network.IP +  "/Govt/talathi_dashboard.php", param);
                        id = id.trim();
                        if (id.equals("0")) {
                            Toast.makeText(getApplicationContext(), "Data not inserted", Toast.LENGTH_LONG).show();
                        } else if (!id.equals("1")) {
                            Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_LONG).show();
                        }
                }
            }
        });
    }
}
