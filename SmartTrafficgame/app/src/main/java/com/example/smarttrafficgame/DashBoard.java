package com.example.smarttrafficgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;

public class DashBoard extends AppCompatActivity {

    ImageView imageView ,imageview2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        imageView=findViewById(R.id.trafficrules);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), trafficrules.class));
            }
        });
        imageview2=findViewById(R.id.quiz);
        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=getIntent().getStringExtra("id");

                HashMap<String ,String > p=new HashMap<String ,String>();
                p.put("user_id",id.trim());
                System.out.println(id);
                String rs=Network.connect("http://"+Network.IP+"/delete_all.php",p);


                Intent intent = new Intent(getApplicationContext(), YoutubeViews.class);
                System.out.println(id);
                intent.putExtra("id",id);
                startActivity(intent);
                finish();

            }
        });
    }
}