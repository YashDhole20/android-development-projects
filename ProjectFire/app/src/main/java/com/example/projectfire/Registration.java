package com.example.projectfire;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity {



    DatabaseReference reference;

    private EditText name,id,mobile,email;
    private Button sumbit;
    private TextView gotoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion);

        name=findViewById(R.id.editParentName);
        id=findViewById(R.id.editParentChildID);
        mobile=findViewById(R.id.editParentMobile);
        email=findViewById(R.id.editParentPassword);

        gotoHome=findViewById(R.id.textGotoSignin);
        sumbit=findViewById(R.id.btnParentRegistration);
        reference= FirebaseDatabase.getInstance().getReference("User");

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String > param=new HashMap<String, String>();

                param.put("id",id.getText().toString());
                param.put("Parent_name",name.getText().toString());
                param.put("mobile",mobile.getText().toString());
                param.put("email",email.getText().toString());
                reference.setValue(param);
            }
        });



    }
}
