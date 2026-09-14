package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddBusTiming extends AppCompatActivity {

    private EditText busNoEdit,driverNmEdit,routeStartEdit,routeEndEdit,timeStartEdit,timeEndEdit,passEdit;
    private Button saveBusBtn,cancelBusBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus_timing);

        busNoEdit=findViewById(R.id.editBusNumber);
        driverNmEdit=findViewById(R.id.editDriverName);
        routeStartEdit=findViewById(R.id.editRouteStart);
        routeEndEdit=findViewById(R.id.editRouteEnd);
        timeStartEdit=findViewById(R.id.editTimingStart);
        timeEndEdit=findViewById(R.id.editTimingEnd);
        passEdit=findViewById(R.id.editDriverPassword);

        saveBusBtn=findViewById(R.id.btnSaveBus);
        cancelBusBtn=findViewById(R.id.btnBackToPanel);


        cancelBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminPanel.class));
            }
        });

        saveBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(busNoEdit.getText().toString())) {
                    busNoEdit.setError("Enter Bus no");
                    busNoEdit.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(driverNmEdit.getText().toString())){
                    driverNmEdit.setError("Enter Driver name");
                    driverNmEdit.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(routeStartEdit.getText().toString())){
                    routeStartEdit.setError("Enter Route Starting");
                    routeStartEdit.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(routeEndEdit.getText().toString())){
                    routeEndEdit.setError("Enter route ending");
                    routeEndEdit.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(timeStartEdit.getText().toString())){
                    timeEndEdit.setError("Enter starting time");
                    timeEndEdit.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty((timeEndEdit.getText().toString()))){
                    timeEndEdit.setError("Enter ending time");
                    timeEndEdit.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty((passEdit.getText().toString()))){
                    passEdit.setError("Enter Password");
                    passEdit.requestFocus();
                    return;
                }

                HashMap<String,String> param=new HashMap<String, String>();

                param.put("busnumber",busNoEdit.getText().toString());
                param.put("drivername",driverNmEdit.getText().toString());
                param.put("routebegin",routeStartEdit.getText().toString());
                param.put("routedestination",routeEndEdit.getText().toString());
                param.put("timestart",timeStartEdit.getText().toString());
                param.put("timeend",timeEndEdit.getText().toString());
                param.put("password",passEdit.getText().toString());
                StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(sb);

                String rs = Network.connect("http://" + Network.IP + "/busdetailsinsert.php",param);
                System.out.println("resp" + rs);

                if (rs.equals("0")) {
                    Toast.makeText( getApplicationContext(), "Wrong Info", Toast.LENGTH_LONG).show();

                } else if (!rs.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(getApplicationContext(),AdminPanel.class);
                    startActivity(in);
                    finish();
                }

            }
        });


    }
}