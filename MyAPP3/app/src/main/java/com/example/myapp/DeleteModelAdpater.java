package com.example.myapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class DeleteModelAdapter extends RecyclerView.Adapter<DeleteModelAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<ViewModel> deleteModelArrayList;

    // Constructor
    public DeleteModelAdapter(Context context, ArrayList<ViewModel> deleteModelArrayList) {
        this.context = context;
        this.deleteModelArrayList = deleteModelArrayList;
    }

    @NonNull
    @Override
    public DeleteModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_delet, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        ViewModel model = deleteModelArrayList.get(position);
        holder.title_nm.setText(model.getTitle_nm());
        holder.desc.setText(""+model.getDesc());
        holder.loc.setText( model.getLoc());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition=holder.getAdapterPosition();
                if(adapterPosition!=RecyclerView.NO_POSITION){

                    //delete data from the database
                    deleteModelArrayList.remove(adapterPosition);
                    notifyItemChanged(adapterPosition);
                    notifyDataSetChanged();
                    Toast.makeText(context.getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        int backgroundColor = ViewModel.isIsSelected() ? Color.LTGRAY : Color.WHITE;
        holder.itemView.setBackgroundColor(backgroundColor);
    }


    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return deleteModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title_nm, desc, loc;
        private Button deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_nm = itemView.findViewById(R.id.titleTextViewDelete);
            desc = itemView.findViewById(R.id.descriptionTextViewDelete);
            loc = itemView.findViewById(R.id.locationTextViewDelete);
            deleteBtn = itemView.findViewById(R.id.btnDeleteA);

        }
    }


}
