package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.view.View;
import android.view.textclassifier.TextLanguage;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity_ViewTask extends AppCompatActivity {


    private TextView title, description, location;
//    private ViewModelAdapter viewAdapter;

     ListView licomp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_task);

        licomp = (ListView) findViewById(R.id.idViewModel);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .penaltyDeath()
                .build());
        String rs = Network.connect("http://" + Network.IP + "/viewtask.php", new HashMap<String, String>());

//

//
            String[] companies = rs.split(",");
            final List<String> companyList = new ArrayList<>();
            for (String company : companies) {
                companyList.add(company);
            }
            ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_2,companyList);
            licomp.setAdapter(adapter);

//
//Toast.makeText(getApplicationContext(),"data"+rs,Toast.LENGTH_SHORT).show();
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, companyList);
//            licomp.setAdapter(adapter);

//            licomp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    String []strings= (String[]) parent.getItemAtPosition(position);
//
//                    Intent intent=new Intent(MainActivity_ViewTask.this,MainActivity_ViewTask.class);
//                    intent.putExtra("title",strings[0]);
//                    intent.putExtra("description",strings[1]);
//                    intent.putExtra("location",strings[2]);
//                }
//            });
        }
    }










