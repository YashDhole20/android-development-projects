package com.example.myrecylerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapterrr extends  RecyclerView.Adapter<Adapterrr.ViewHolder>{

    private final Context context;
    private final ArrayList<Model> ModelArrayList ;


    public Adapterrr(Context context, ArrayList<Model> ModelArrayList) {
        this.context = context;
        this.ModelArrayList =  ModelArrayList;
    }

    @NonNull
    @Override
    public  Adapterrr.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_viewtask, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model model =  ModelArrayList.get(position);
        holder.courseNameTV.setText(model.getCourse_name());
        holder.courseRatingTV.setText("" + model.getCourse_rating());
        holder.courseIV.setText(""+model.getCourse_image());
    }



    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return ModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseIV;
        private final TextView courseNameTV;
        private final TextView courseRatingTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseIV = itemView.findViewById(R.id.textViewTitle);
            courseNameTV = itemView.findViewById(R.id.textViewDescription);
            courseRatingTV = itemView.findViewById(R.id.textViewLocation);
        }
    }
}

