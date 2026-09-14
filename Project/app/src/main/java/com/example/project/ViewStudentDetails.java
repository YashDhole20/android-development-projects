package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentDetails extends AppCompatActivity {
    FirebaseRecyclerOptions <Student> stud;
    FirebaseRecyclerAdapter  <Student, StudentViewHolder> adapter;
     RecyclerView recyclerView;
    DatabaseReference my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_details);
        Log.d("MyTag", "onCreate() called");
        recyclerView = findViewById(R.id.viewStudentRecycle);
        recyclerView.setHasFixedSize(true);
        Log.d("MyTag", "onCreate() called2");
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewStudentDetails.this));
        Log.d("MyTag", "onCreate() called3");
        my= FirebaseDatabase.getInstance().getReference("Student");
        Log.d("MyTag", "onCreate() called4");
//        stud=new FirebaseRecyclerOptions.Builder<Student>().setQuery(my,Student.class).build();
        Log.d("MyTag", "onCreate() called5");
        List<Student> dummyStudentList = new ArrayList<>();
        dummyStudentList.add(new Student("1", "John Doe", "Jane Doe", "123 Main St", "School", "1234567890"));
        dummyStudentList.add(new Student("2", "Alice Smith", "Bob Smith", "456 Elm St", "Home", "9876543210"));
        // Add more dummy students as needed

        stud = new FirebaseRecyclerOptions.Builder<Student>()
                .setSnapshotArray(dummyStudentList)
                .build();
        adapter=new FirebaseRecyclerAdapter<Student,StudentViewHolder>(stud) {
            @Override
            protected void onBindViewHolder(@NonNull StudentViewHolder holder, int position, @NonNull Student model) {
                holder.studID.setText(model.getStudID());
                holder.studName.setText(model.getStudName());
                holder.parentName.setText(model.getParentName());
                holder.address.setText(model.getAddress());
                holder.pickup.setText(model.getPickup());
                holder.mobile.setText(model.getMobile());
                Log.d("OnBind","123457890");
            }

            @NonNull
            @Override
            public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View vs=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_view_student_details,parent,false);
                return new StudentViewHolder(vs);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
        System.out.println( adapter.getItemCount());

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}