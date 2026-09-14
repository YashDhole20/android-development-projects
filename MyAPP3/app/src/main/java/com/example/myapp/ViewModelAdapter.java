package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ViewModelAdapter extends ArrayAdapter<String> {

private Context context;
private List<String> list;
    public ViewModelAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context,  R.layout.card_layout, objects);
        this.context=context;
        list=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.card_layout, null);
        }
        TextView locationTxt = view.findViewById(R.id.locationTextView);
        TextView descriptionTxt = view.findViewById(R.id.descriptionTextView);
        TextView titleTxt = view.findViewById(R.id.titleTextView);
        return view;
    }
}
