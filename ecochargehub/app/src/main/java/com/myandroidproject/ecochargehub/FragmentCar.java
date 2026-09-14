package com.myandroidproject.ecochargehub;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class FragmentCar extends Fragment {

    private Button buttonOnVehicleButton;
    private ImageView logoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_car, container, false);

        // Find buttons
        buttonOnVehicleButton = rootView.findViewById(R.id.buttonOnVehicleButton);
        logoutButton = rootView.findViewById(R.id.logout);

        // Set click listeners
        buttonOnVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), view_vehicles.class);
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
