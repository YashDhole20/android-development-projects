package com.example.project;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class StudentViewHolder extends RecyclerView.ViewHolder {
  TextView studID, studName, parentName, address, pickup, mobile;


    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        studName = itemView.findViewById(R.id.textStudentName);
        parentName = itemView.findViewById(R.id.textStudentMobile);
        address = itemView.findViewById(R.id.textStudentEmail);
        mobile = itemView.findViewById(R.id.textStudentAddress);
        pickup = itemView.findViewById(R.id.textStudentPass);
        studID = itemView.findViewById(R.id.textStudentID);

    }
}
