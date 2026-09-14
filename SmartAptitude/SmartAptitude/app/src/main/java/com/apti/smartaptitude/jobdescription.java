
package com.apti.smartaptitude;

        import android.app.Dialog;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.HashMap;
        import java.util.List;

        import android.widget.TextView;
        import android.widget.Toast;

public class jobdescription extends AppCompatActivity {
    ListView descriptionList;
    ArrayAdapter<String[]> adapter;

    Button tech,att,code,hr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobdescription);

        descriptionList = findViewById(R.id.description_list);

        descriptionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        displayJobInfo();
    }

    private void displayJobInfo() {
        String companyName = getIntent().getStringExtra("job_name");
        String response = Network.connect("http://" + Network.IP + "/jobinfo.php?companyid=" + companyName,
                new HashMap<String, String>());


        List<String> students = Arrays.asList(response.trim().split("#"));

        List<String[]> studentDataList = new ArrayList<>();

        for (String busString : students) {
            String[] busData = busString.split("<br>");
            studentDataList.add(busData);
        }

        adapter = new ArrayAdapter<String[]>((Context) this, R.layout.vaccancyinfo, R.id.textName, studentDataList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull
            ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                String[] busData = getItem(position);
                TextView textname=view.findViewById(R.id.textName);
                TextView textsalary = view.findViewById(R.id.textsalary);
                TextView textViewjobdescription = view.findViewById(R.id.textjobdescription);
                TextView textJobName=view.findViewById(R.id.textJobName);
                TextView textlocation= view.findViewById(R.id.textlocation);
                Button btn = view.findViewById(R.id.start);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(jobdescription.this);
                        dialog.setContentView(R.layout.rounds);

                        //Bus timing btn
                        tech = dialog.findViewById(R.id.technical);
                        att = dialog.findViewById(R.id.appti);
                        code = dialog.findViewById(R.id.Coding);
                        hr = dialog.findViewById(R.id.hr);


                        dialog.show();
                        tech.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String id3= getIntent().getStringExtra("UserID");
                                HashMap<String,String> p=new HashMap<String, String>();
                                p.put("user_id",id3);
                                p.put("company_id",companyName);
                                p.put("job_nm",textJobName.getText().toString().trim());
                                System.out.println(id3);
                                System.out.println(companyName);
                                String rs=Network.connect("http://" + Network.IP + "/checkForTechnical.php", p);
                                String   r=rs.trim();
                                if(r.equals("1")){
                                    Toast.makeText(jobdescription.this, "You already given test", Toast.LENGTH_SHORT).show();
                                }else  {
                                    Intent intent=new Intent(jobdescription.this,technical.class);
                                    intent.putExtra("UserID",id3);
                                    intent.putExtra("com_id",companyName);
                                    intent.putExtra("job_nm",textJobName.getText().toString().trim());
                                    startActivity(intent);
                                }

                            }
                        });
                        att.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String id3= getIntent().getStringExtra("UserID");
                                HashMap<String,String> p=new HashMap<String, String>();
                                p.put("userid",id3);
                                p.put("company_id",companyName);
                                p.put("job_nm",textJobName.getText().toString().trim());
                                System.out.println(id3);
                                String rs=Network.connect("http://" + Network.IP + "/checkForapti.php", p);
                                String   r=rs.trim();
                                if(r.equals("1")){
                                    Toast.makeText(jobdescription.this, "You already given test", Toast.LENGTH_SHORT).show();
                                }else  {
                                    Intent intent=new Intent(jobdescription.this,Aptitude.class);
                                    intent.putExtra("UserID",id3);
                                    intent.putExtra("com_id",companyName);
                                    intent.putExtra("job_nm",textJobName.getText().toString().trim());
                                    startActivity(intent);
                                }
                            }
                        });
                        code.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String id3= getIntent().getStringExtra("UserID");
                                HashMap<String,String> p=new HashMap<String, String>();
                                p.put("userid",id3);
                                p.put("company_id",companyName);
                                p.put("job_nm",textJobName.getText().toString().trim());
                                System.out.println(id3);
                                String rs=Network.connect("http://" + Network.IP + "/checkForcoding.php", p);
                                String   r=rs.trim();
                                if(r.equals("1")){
                                    Toast.makeText(jobdescription.this, "You already given test", Toast.LENGTH_SHORT).show();
                                }else  {
                                    Intent intent=new Intent(jobdescription.this,coding.class);
                                    intent.putExtra("UserID",id3);
                                    intent.putExtra("com_id",companyName);
                                    intent.putExtra("job_nm",textJobName.getText().toString().trim());
                                    startActivity(intent);
                                }
                            }
                        });
                        hr.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(getApplicationContext(), hr_round.class));
                            }
                        });

                    }
                });//

                textname.setText(busData[0]);
                textsalary.setText(busData[1]);
                textViewjobdescription.setText(busData[2]);
                textlocation.setText(busData[3]);
                textJobName.setText(busData[4]);
                return view;
            }
        };

        descriptionList.setAdapter(adapter);
    }
}
