package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity_Delete extends AppCompatActivity {




     ArrayList<ViewModel> deleteModelArrayList = new ArrayList<ViewModel>();
     DeleteModelAdapter deleteModelAdapter = new DeleteModelAdapter(this, deleteModelArrayList);
     int position;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_delete);


        RecyclerView viewm = findViewById(R.id.idDeleteModel);


        deleteModelArrayList.add(new ViewModel("DSA in Java", "asdf", "asdf"));
        deleteModelArrayList.add(new ViewModel("DSA in Java", "asdf", "asdf"));
        deleteModelArrayList.add(new ViewModel("DSA in Java", "asdf", "asdf"));
        deleteModelArrayList.add(new ViewModel("DSA in Java", "asdf", "asdf"));
        deleteModelArrayList.add(new ViewModel("DSA in Java", "asdf", "asdf"));
        deleteModelArrayList.add(new ViewModel("DSA in Java", "asdf", "asdf"));


        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        viewm.setLayoutManager(linearLayoutManager);
        viewm.setAdapter(deleteModelAdapter);


        ViewModel deletedItem = deleteModelArrayList.get(position);
        deleteModelArrayList.remove(position);
        deleteModelAdapter.notifyItemRemoved(position);

    }
        public    void deleteItem(int pos){
           int  position =pos ;
            Toast.makeText(getApplicationContext(), "position"+pos, Toast.LENGTH_SHORT).show();
        }
    }

