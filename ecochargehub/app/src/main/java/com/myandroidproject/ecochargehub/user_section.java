package com.myandroidproject.ecochargehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class user_section extends AppCompatActivity {

    private MeowBottomNavigation BottomNavigation1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_section);
        replace(new FragmentShowrooms());
        BottomNavigation1 = findViewById(R.id.BottomNavigation1);
        BottomNavigation1.add(new MeowBottomNavigation.Model(1,R.drawable.icon_showroom));
        BottomNavigation1.add(new MeowBottomNavigation.Model(2,R.drawable.icon_chargingstations));
        BottomNavigation1.add(new MeowBottomNavigation.Model(3,R.drawable.icon_car));
        BottomNavigation1.add(new MeowBottomNavigation.Model(4,R.drawable.icon_power));

        BottomNavigation1.show(1,true);

        BottomNavigation1.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new FragmentShowrooms());
                        break;

                    case 2:
                        replace(new FragmentStations());
                        break;

                    case 3:
                        replace(new FragmentCar());
                        break;

                    case 4:
                        replace(new FragmentPower());
                        break;
                }
                return null;
            }
        });



    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }
}