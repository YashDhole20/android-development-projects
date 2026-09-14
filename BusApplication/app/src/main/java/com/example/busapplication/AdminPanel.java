package com.example.busapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminPanel extends AppCompatActivity {

    private Button busDetailsBtn,studDetailsBtn,updStudBtn,allBusBtn,studReportBtn,busReportBtn,logoutBtn;
    private ImageButton studentCloseBtn,busCloseBtn;
    private Button  addBusBtn,viewBusBtn,deleteBusBtn;
    private Button addStudentBtn,viewStudentBtn,deleteStudentBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        //Main btn
        busDetailsBtn=findViewById(R.id.btnBusDetails);
        studDetailsBtn=findViewById(R.id.btnStudDetails);
        updStudBtn=findViewById(R.id.btnUpdateStud);
        allBusBtn=findViewById(R.id.btnViewAllBus);
        studReportBtn=findViewById(R.id.btnStudReport);
        busReportBtn=findViewById(R.id.btnBusReport);
        logoutBtn=findViewById(R.id.btnAdminLogout);






        busDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(AdminPanel.this);
                dialog.setContentView(R.layout.bus_details);
                  busCloseBtn  = dialog.findViewById(R.id.imageBackToPanelBus);

                //Bus timing btn
                addBusBtn=dialog.findViewById(R.id.btnAddBus);
                viewBusBtn=dialog.findViewById(R.id.btnViewBus);
                deleteBusBtn=dialog.findViewById(R.id.btnDeleteBus);


                 busCloseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                addBusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                 viewBusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                 deleteBusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
        studDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(AdminPanel.this);
                dialog.setContentView(R.layout.student_details);

                //Student   btn
                addStudentBtn=dialog.findViewById(R.id.btnAddStudent);
                viewStudentBtn=dialog.findViewById(R.id.btnViewStudent);
                deleteStudentBtn=dialog.findViewById(R.id.btnDeleteStudent);
                  studentCloseBtn = dialog.findViewById(R.id.imageBackToPanelStudent);
                studentCloseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                 addStudentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                viewStudentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                deleteStudentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });

         updStudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),);
//                startActivity(intent);
            }
        });
         allBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),);
//                startActivity(intent);
            }
        });
         studReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),);
//                startActivity(intent);
            }
        });
         busReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),);
//                startActivity(intent);
            }
        });

          logoutBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  startActivity(new Intent(getApplicationContext(), AdminLogin.class));
              }
          });

    }
}