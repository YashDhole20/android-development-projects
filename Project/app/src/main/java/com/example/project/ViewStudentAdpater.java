//package com.example.project;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//
//public class ViewStudentAdpater extends RecyclerView.Adapter<ViewStudentAdpater.ViewHolder> {
//
//    private final Context context;
//    private final ArrayList<Student> ModelArrayList ;
//
//    public ViewStudentAdpater(Context context, ArrayList<Student> modelArrayList) {
//        this.context = context;
//        ModelArrayList = modelArrayList;
//    }
//
//    @NonNull
//    @Override
//    public ViewStudentAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_view_student_details, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewStudentAdpater.ViewHolder holder, int position) {
//      Student  student =  ModelArrayList.get(position);
//        holder.studID.setText(student.getStudID());
//        holder.studName.setText(student.getStudName());
//        holder.parentName.setText(student. getParentName());
//        holder.address.setText(student.getAddress());
//        holder.pickup.setText(student.getPickup());
//        holder.mobile.setText(student.getMobile());
//    }
//
//    @Override
//    public int getItemCount() {
//        return ModelArrayList.size();
//    }
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//
//        private TextView studName,parentName, address,mobile, pickup, studID;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            studName=itemView.findViewById(R.id.textViewStudentName);
//            parentName=itemView.findViewById(R.id.textViewStudentMobile);
//            address=itemView.findViewById(R.id.textViewStudentEmail);
//            mobile=itemView.findViewById(R.id.textViewStudentAddress);
//            pickup=itemView.findViewById(R.id.textViewStudentPass);
//            studID=itemView.findViewById(R.id.textViewStudentID);
//        }
//    }
//}
