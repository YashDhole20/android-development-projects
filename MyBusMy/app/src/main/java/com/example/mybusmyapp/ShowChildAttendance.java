package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShowChildAttendance extends AppCompatActivity {

    private EditText studentidEdit;
    private Button nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_child_attendance);


        studentidEdit=findViewById(R.id.editChildStudentId);
        nextBtn=findViewById(R.id.btnNextToAttendance);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String studentid=studentidEdit.getText().toString();

                Intent intent=new Intent(getApplicationContext(), ViewAttendanceForStudent.class);
                intent.putExtra("studentid",studentid);
                startActivity(intent);

            }
        });

    }
}