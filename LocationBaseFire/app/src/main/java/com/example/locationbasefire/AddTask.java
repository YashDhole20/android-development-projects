package com.example.locationbasefire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddTask extends AppCompatActivity {

    private EditText title,description,location;
    private Button save,back;

    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title=findViewById(R.id.editTxtTitle);
        description=findViewById(R.id.editTxtDescription);
        location=findViewById(R.id.editTxtLocation);

        save=findViewById(R.id.btnSave);
        back=findViewById(R.id.btnBack);

        myRef= FirebaseDatabase.getInstance().getReference().child("task");


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> hashMap=new HashMap<String, String>();
                String key=myRef.push().getKey();
                hashMap.put("title",title.getText().toString());
                hashMap.put("description",description.getText().toString());
                hashMap.put("location",location.getText().toString());

                myRef.child(key).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AddTask.this, "Add Tasked", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(AddTask.this, MainActivity.class);
                        String user=getIntent().getStringExtra("user");
                        String mobile=getIntent().getStringExtra("mobile");
                        intent.putExtra("user",user);
                        intent.putExtra("mobile",mobile);
                        startActivity(intent);
                        finish();
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(AddTask.this, "Not Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}