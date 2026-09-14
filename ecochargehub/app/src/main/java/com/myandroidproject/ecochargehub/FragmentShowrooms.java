package com.myandroidproject.ecochargehub;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class FragmentShowrooms extends Fragment {

    private Button viewShowroomButton;
    private ImageView logoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_showrooms, container, false);

        // Find buttons
        viewShowroomButton = rootView.findViewById(R.id.buttonOnVehicleImage);
        logoutButton = rootView.findViewById(R.id.logout);

        // Set click listeners
        viewShowroomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), view_showrooms.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return rootView;
    }
}
