package com.apti.smartaptitude;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompanySelection extends AppCompatActivity {
    ListView companyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_selection);

        companyListView = findViewById(R.id.company_list_view);


        String rs = Network.connect("http://" + Network.IP + "/getCompanylist.php",
                new HashMap<String, String>());

        String[] companies = rs.trim().split("#");

        final List<String> companyList = new ArrayList<>();
        for (String company : companies) {
            companyList.add(company);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, companyList);
        companyListView.setAdapter(adapter);


        companyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedCompanyName = companyList.get(position);
                System.out.println("data"+selectedCompanyName);
                String spt[]=selectedCompanyName.split(",");
                Intent intent = new Intent(CompanySelection.this, jobdescription.class);
                String id2=getIntent().getStringExtra("UserID");
                System.out.println(spt[0]);
                intent.putExtra("job_name", spt[0]);
                intent.putExtra("UserID",id2);
                startActivity(intent);
            }
        });
    }
}
