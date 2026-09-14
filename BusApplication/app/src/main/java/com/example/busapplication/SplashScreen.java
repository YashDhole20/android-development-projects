package com.example.busapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView=findViewById(R.id.main_icon);
        Animation animation= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.rotate_clockwise);
        imageView.startAnimation(animation);

        ObjectAnimator scaleDown= ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                PropertyValuesHolder.ofFloat("scaleX",1.2f),
                PropertyValuesHolder.ofFloat("scaleY",1.2f)
        );
        scaleDown.setDuration(310);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(this.getResources().getColor(R.color.white,null));

        }
        new Handler().postDelayed(()->{
            Intent intent=new Intent(SplashScreen.this, Dashboard.class);
            startActivity(intent);
        },1500);
    }
}