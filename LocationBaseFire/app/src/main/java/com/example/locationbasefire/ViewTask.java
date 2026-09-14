package com.example.locationbasefire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewTask extends AppCompatActivity {

    FirebaseRecyclerOptions<Model> model;
    FirebaseRecyclerAdapter<Model, MyViewHolder> adapter;
    private RecyclerView recyclerView;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRef= FirebaseDatabase.getInstance().getReference("task");
        model=new FirebaseRecyclerOptions.Builder<Model>().setQuery(myRef,Model.class).build();
        adapter=new FirebaseRecyclerAdapter<Model, MyViewHolder>(model) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Model model) {
                holder.title.setText(model.getTitle());
                holder.desc.setText(model.getDescription());
                holder.loc.setText(model.getLocation());
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_viewtask,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}