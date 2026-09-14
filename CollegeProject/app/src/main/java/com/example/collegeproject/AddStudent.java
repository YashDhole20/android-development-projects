package com.example.collegeproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudent extends AppCompatActivity {
    DatabaseReference ref;

    private EditText studName,parentName,address,mobile,studeID,pickup;
    private Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        studName=findViewById(R.id.editStudName);
        parentName=findViewById(R.id.editParentName);
        address=findViewById(R.id.editStudAddress);
        mobile=findViewById(R.id.editParentMobileNo);
        studeID=findViewById(R.id.editRFID);
        pickup=findViewById(R.id.editPickupLocation);

        btn_submit=findViewById(R.id.btnNext);

        TextView studNameText = findViewById(R.id.textStudName);
        TextView rfidText = findViewById(R.id.textStudId);
        TextView addressText = findViewById(R.id.textAddress);
        TextView parentNameText = findViewById(R.id.textParentName);
        TextView mobileParentText = findViewById(R.id.textParentMobileNo);
        TextView pickupText=findViewById(R.id.textPickup);

         ref= FirebaseDatabase.getInstance().getReference();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(parentName.getText().toString())) {
                    parentNameText.setText("Enter Parent Name");
                    parentNameText.setVisibility(View.VISIBLE);
                    parentName.requestFocus();

                }   else   {
                    parentNameText.setVisibility(View.GONE);
                }

                //For the Student mobile number
                if (TextUtils.isEmpty(mobile.getText().toString())) {
                    mobileParentText.setText("Enter Mobile Number");
                    mobileParentText.setVisibility(View.VISIBLE);
                    mobile.requestFocus();

                } else if (mobile.getText().toString().length() != 10) {
                    mobileParentText.setText("Enter Valid Number");
                    mobileParentText.setVisibility(View.VISIBLE);
                    mobile.requestFocus();
                    return;
                } else if (mobile.getText().toString().length() == 10) {
                    mobileParentText.setVisibility(View.GONE);
                }


                //For enter  the address
                if (TextUtils.isEmpty(address.getText().toString())) {
                    addressText.setText("Enter Address");
                    addressText.setVisibility(View.VISIBLE);
                    address.requestFocus();

                } else {
                    addressText.setVisibility(View.GONE);
                }


                //For the   RFId
                if (TextUtils.isEmpty(studeID.getText().toString())) {
                    rfidText.setText("Enter Student ID");
                    rfidText.setVisibility(View.VISIBLE);
                    studeID.requestFocus();
                } else {
                    rfidText.setVisibility(View.GONE);
                }
                //For the Student Name
                if (TextUtils.isEmpty(studName.getText().toString())) {
                    studNameText.setText("Enter Student Name");
                    studNameText.setVisibility(View.VISIBLE);
                    studName.requestFocus();
                } else {
                    studNameText.setVisibility(View.GONE);
                }

                if (TextUtils.isEmpty(pickup.getText().toString())) {
                    pickupText.setText("Enter Pickup Locatio ");
                    pickupText.setVisibility(View.VISIBLE);
                    pickup.requestFocus();

                } else {
                    pickupText.setVisibility(View.GONE);
                }

                String parent=parentName.getText().toString().trim();
                String stud=studName.getText().toString().trim();
                String id=studeID.getText().toString().trim();
                String mo=mobile.getText().toString().trim();
                String add=address.getText().toString().trim();
                String pick=pickup.getText().toString().trim();
                ref.setValue("Yash Dhole");
                Student stu=new Student(stud,parent,add,mo,pick,stud);
                ref.child(id).setValue(stu).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AddStudent.this, "Registration Successfully ref", Toast.LENGTH_SHORT).show();
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(AddStudent.this, "Registration Failed. ref", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}