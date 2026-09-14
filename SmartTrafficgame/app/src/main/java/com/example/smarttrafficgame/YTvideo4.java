package com.example.smarttrafficgame;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



public class YTvideo4 extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String[]> adapter;
    List<String[]> questions;
    Button button;
    HashMap<String, String> selectedOptionsMap = new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ytvideo4);
        button = findViewById(R.id.btnsumbit4);
        listView = findViewById(R.id.listQuestionView4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(selectedOptionsMap);
                String id = getIntent().getStringExtra("id");
                HashMap<String, String> m = new HashMap<String, String>();
                m.put("data", String.valueOf(selectedOptionsMap).trim());
                m.put("user_id", id.trim());
                System.out.println(m);
                String rs = Network.connect("http://" + Network.IP + "/level5_ans.php", m);
                if (rs != "0") {
                    // Handle server response if needed
                    Intent intent = new Intent(getApplicationContext(), Level5.class);
                    System.out.println(id);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();

                } else {
                    // Handle network request failure
                    Toast.makeText(YTvideo4.this, "Failed to submit answers. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        String response = Network.connect("http://" + Network.IP + "/fire.php",


                new HashMap<>());
        List<String> buses = Arrays.asList(response.trim().split("#"));

        List<String[]> busDataList = new ArrayList<>();
        for (String questionsString : buses) {
            String[] busData = questionsString.split("<br>"); // Assuming each bus data string is comma-separated
            busDataList.add(busData);
        }
        if (!busDataList.isEmpty() && !busDataList.get(0)[0].equals("0")) {
            adapter = new ArrayAdapter<String[]>(this, R.layout.question, R.id.questionTextView, busDataList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    String[] busData = getItem(position);
                    TextView textViewBusNumber = view.findViewById(R.id.questionTextView);
                    RadioGroup radioGroupOptions = view.findViewById(R.id.optionsRadioGroup);
                    RadioButton[] radioButtons = new RadioButton[4];
                    radioButtons[0] = view.findViewById(R.id.option1RadioButton);
                    radioButtons[1] = view.findViewById(R.id.option2RadioButton);
                    radioButtons[2] = view.findViewById(R.id.option3RadioButton);
                    radioButtons[3] = view.findViewById(R.id.option4RadioButton);

                    textViewBusNumber.setText(busData[1]);

                    // Set radio button options
                    for (int i = 0; i < radioButtons.length; i++) {
                        radioButtons[i].setText(busData[i + 2]); // Assuming options start from index 2
                        final int optionIndex = i;
                        radioButtons[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                RadioButton radioButton = (RadioButton) v;
                                String selectedOption = radioButton.getText().toString();
                                // Inside the onClickListener for radioButtons
                                String questionText = busData[1]; // Assuming busData[1] contains the question text
                                Log.d("SelectedOption", "Question: " + questionText + ", Selected Option: " + selectedOption);
                                selectedOptionsMap.put(questionText.trim(), selectedOption.trim()); // Map question text to selected option
                            }
                        });
                    }
                    return view;
                }
            };
            listView.setAdapter(adapter);
        }
    }
}