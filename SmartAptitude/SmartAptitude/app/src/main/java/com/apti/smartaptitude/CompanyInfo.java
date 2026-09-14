    package com.apti.smartaptitude;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ListView;
    import android.widget.ArrayAdapter;
    import android.widget.Toast;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.List;
    import org.json.JSONArray;
    import org.json.JSONException;


    public class CompanyInfo extends AppCompatActivity {
        ListView vacancyListView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_company_info);

            vacancyListView = findViewById(R.id.vacancy_list_view);
            String companyName = getIntent().getStringExtra("companyName");
            System.out.println("---"+companyName);

            String response = Network.connect("http://" + Network.IP + "/getVacancyList.php?companyid=" + companyName,
                    new HashMap<String, String>());
            String[] companies = response.trim().split("#");

            // Create a list to store company names
            final List<String> companyList = new ArrayList<>();
            for (String company : companies) {

                companyList.add(company);
            }



            System.out.println(companyList.get(1)+" 11111");
            // Create an ArrayAdapter to populate the ListView with company names
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, companyList);
            vacancyListView.setAdapter(adapter);

            vacancyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedCompanyName = companyList.get(i);
                    System.out.println("data"+selectedCompanyName);
                    String spt[]=selectedCompanyName.split(",");
                    Toast.makeText(CompanyInfo.this, ""+selectedCompanyName, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), jobdescription.class);
                    intent.putExtra("job_name", spt[0]);
                    intent.putExtra("id",companyName);
//                    intent.putExtra("UserID",getIntent().getStringExtra("UserID"));
                    startActivity(intent);
                }
            });

        }
    }
