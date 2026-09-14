package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity_optionPage extends AppCompatActivity {

    private Button addTaskBtn,viewTaskBtn,deleteBtn;

    ListView licomp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_optionpage);

        addTaskBtn=findViewById(R.id.btnAddTask);
        viewTaskBtn=findViewById(R.id.btnViewTask);
        deleteBtn=findViewById(R.id.btnDelete);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity_AddTask.class);
                startActivity(intent);
            }
        });

        viewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_optionPage.this,MainActivity_ViewTask.class);
                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_optionPage.this, MainActivity_Delete.class);
                startActivity(intent);
            }
        });

    }
}