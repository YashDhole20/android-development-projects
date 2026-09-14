package com.example.smarttrafficgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class FailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);
        Button btn=findViewById(R.id.nextFail);
        String id=getIntent().getStringExtra("id");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int fail=getIntent().getIntExtra("fail",0);

                    switch (fail){

                        case 1:
                            Toast.makeText(FailActivity.this, "YOU ARE FAIL FIRST EXAM", Toast.LENGTH_SHORT).show();
                            HashMap<String ,String > p1=new HashMap<String ,String>();
                            p1.put("user_id",id.trim());
                            System.out.println(id);
                            String rs1=Network.connect("http://"+Network.IP+"/delete_level1.php",p1);
                            Intent intent1 = new Intent(getApplicationContext(),  YoutubeViews.class);
                            intent1.putExtra("id", id);
                            System.out.println(id);
                            startActivity(intent1);
                            finish();
                            break;
                        case 2:
                            Toast.makeText(FailActivity.this, "YOU ARE FAIL SECOND EXAM", Toast.LENGTH_SHORT).show();
                            HashMap<String ,String > p2=new HashMap<String ,String>();
                            p2.put("user_id",id.trim());
                            System.out.println(id);
                            String rs2=Network.connect("http://"+Network.IP+"/delete_level2.php",p2);
                            Intent intent2 = new Intent(getApplicationContext(), YTvideows1.class);
                            intent2.putExtra("id", id);
                            System.out.println(id);
                            startActivity(intent2);
                            finish();

                            break;
                        case 3:
                            Toast.makeText(FailActivity.this, "YOU ARE FAIL THIRD EXAM", Toast.LENGTH_SHORT).show();
                            HashMap<String ,String > p3=new HashMap<String ,String>();
                            p3.put("user_id",id.trim());
                            System.out.println(id);
                            String rs3=Network.connect("http://"+Network.IP+"/delete_level3.php",p3);
                            Intent intent3 = new Intent(getApplicationContext(), YTVideo2.class);
                            intent3.putExtra("id", id);
                            System.out.println(id);
                            startActivity(intent3);
                            finish();

                            break;
                        case 4:
                            Toast.makeText(FailActivity.this, "YOU ARE FAIL FOURTH EXAM", Toast.LENGTH_SHORT).show();
                            HashMap<String ,String > p4=new HashMap<String ,String>();
                            p4.put("user_id",id.trim());
                            System.out.println(id);
                            String rs4=Network.connect("http://"+Network.IP+"/delete_level4.php",p4);
                            Intent intent4 = new Intent(getApplicationContext(), YTVideo3.class);
                            intent4.putExtra("id", id);
                            System.out.println(id);
                            startActivity(intent4);
                            finish();

                            break;
                        case 5:
                            Toast.makeText(FailActivity.this, "YOU ARE FAIL FIFTH EXAM", Toast.LENGTH_SHORT).show();
                            HashMap<String ,String > p5=new HashMap<String ,String>();
                            p5.put("user_id",id.trim());
                            System.out.println(id);
                            String rs5=Network.connect("http://"+Network.IP+"/delete_level5.php",p5);
                            Intent intent5 = new Intent(getApplicationContext(), YTvideo4.class);
                            intent5.putExtra("id", id);
                            System.out.println(id);
                            startActivity(intent5);
                            finish();

                            break;

                        default:
                            Toast.makeText(FailActivity.this, "You are redirect to Dashborad", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                            intent.putExtra("id", id);
                            System.out.println(id);
                            startActivity(intent);
                            finish();

                    }

            }
        });
    }
}