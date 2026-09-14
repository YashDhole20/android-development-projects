package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity {

    private Button admin,driver,student,parent;
    private ImageView backToDashboard;

    private Button viewBusBtn,childAttBtn,viewTimingBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        admin=findViewById(R.id.btnAdmin);
        driver=findViewById(R.id.btnDriver);
        parent=findViewById(R.id.btnParent);
        student=findViewById(R.id.btnStudent);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, AdminLogin.class);
                startActivity(intent);
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, Login.class);
                startActivity(intent);
            }
        });


        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent);
            }
        });

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(getApplicationContext(), ParentLogin.class);
                startActivity(intent);
//                Dialog dialog = new Dialog(Dashboard.this);
//                dialog.setContentView(R.layout.layout_parent_module);
//
//                //Student   btn
//                viewBusBtn =dialog.findViewById(R.id.btnViewBusLocation);
//                childAttBtn=dialog.findViewById(R.id.btnChildAttendance);
//                 viewTimingBtn=dialog.findViewById(R.id.btnViewBusTiming);
//                  backToDashboard=dialog.findViewById(R.id.imageBackToPanelStudent);
//                  dialog.show();
//                  backToDashboard.setOnClickListener(new View.OnClickListener() {
//                      @Override
//                      public void onClick(View v) {
//                          dialog.dismiss();
//                      }
//                  });
//
//                  childAttBtn.setOnClickListener(new View.OnClickListener() {
//                      @Override
//                      public void onClick(View v) {
//                          startActivity(new Intent(getApplicationContext(),ShowChildAttendance.class));
//                      }
//                  });
//                  viewTimingBtn.setOnClickListener(new View.OnClickListener() {
//                      @Override
//                      public void onClick(View v) {
//                          startActivity(new Intent(getApplicationContext(), ViewBusDetails.class));
//                      }
//                  });

            }
        });
    }
}