package com.apti.smartaptitude;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Aptitude extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String[]> adapter;
    List<String[]> questions;
    List<String[]> selectedAnswers;
    HashMap<String, String> selectedOptions;
    private static final long DELAY_TIME = 1500;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aptitude_round);

        listView = findViewById(R.id.QuestionView);

        questions = new ArrayList<>();
        selectedAnswers = new ArrayList<>();
        selectedOptions = new HashMap<>();
        adapter = new ArrayAdapter<String[]>(this, R.layout.layout_question, R.id.questionTextView, questions) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = convertView;
                ViewHolder holder;
                if (view == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    view = inflater.inflate(R.layout.layout_question, parent, false);
                    holder = new ViewHolder();
                    holder.questionTextView = view.findViewById(R.id.questionTextView);
                    holder.radioButton1 = view.findViewById(R.id.option1RadioButton);
                    holder.radioButton2 = view.findViewById(R.id.option2RadioButton);
                    holder.radioButton3 = view.findViewById(R.id.option3RadioButton);
                    holder.radioButton4 = view.findViewById(R.id.option4RadioButton);
                    view.setTag(holder);
                } else {
                    holder = (ViewHolder) view.getTag();
                }

                String[] data = getItem(position);
                holder.questionTextView.setText(data[0]);

                // Set options text
                holder.radioButton1.setText(data[1]);
                holder.radioButton2.setText(data[2]);
                holder.radioButton3.setText(data[3]);
                holder.radioButton4.setText(data[4]);

                // Get selected option for this question
                String selectedOption = selectedOptions.get(data[0]);

                // Update radio button states
                updateRadioButtonState(holder.radioButton1, selectedOption, data[1]);
                updateRadioButtonState(holder.radioButton2, selectedOption, data[2]);
                updateRadioButtonState(holder.radioButton3, selectedOption, data[3]);
                updateRadioButtonState(holder.radioButton4, selectedOption, data[4]);

                // Set click listeners
                holder.radioButton1.setOnClickListener(v -> {
                    selectedOptions.put(data[0], data[1]);
                    notifyDataSetChanged();
                });

                holder.radioButton2.setOnClickListener(v -> {
                    selectedOptions.put(data[0], data[2]);
                    notifyDataSetChanged();
                });

                holder.radioButton3.setOnClickListener(v -> {
                    selectedOptions.put(data[0], data[3]);
                    notifyDataSetChanged();
                });

                holder.radioButton4.setOnClickListener(v -> {
                    selectedOptions.put(data[0], data[4]);
                    notifyDataSetChanged();
                });

                return view;
            }

            // Method to update radio button state based on selected option
            private void updateRadioButtonState(RadioButton radioButton, String selectedOption, String option) {
                radioButton.setChecked(option.equals(selectedOption));
            }

            class ViewHolder {
                TextView questionTextView;
                RadioButton radioButton1;
                RadioButton radioButton2;
                RadioButton radioButton3;
                RadioButton radioButton4;
            }
        };

//                View view = super.getView(position, convertView, parent);
//
//                String[] data = getItem(position);
//                TextView questionTextView = view.findViewById(R.id.questionTextView);
//                RadioButton radioButton1 = view.findViewById(R.id.option1RadioButton);
//                RadioButton radioButton2 = view.findViewById(R.id.option2RadioButton);
//                RadioButton radioButton3 = view.findViewById(R.id.option3RadioButton);
//                RadioButton radioButton4 = view.findViewById(R.id.option4RadioButton);
//
//                questionTextView.setText(data[0]);
//                radioButton1.setText(data[1]);
//                radioButton2.setText(data[2]);
//                radioButton3.setText(data[3]);
//                radioButton4.setText(data[4]);
//
//                radioButton1.setOnClickListener(v -> {
//                    String[] selectedOption = {data[0], data[1]};
//                    selectedAnswers.add(selectedOption);
//                });
//
//                radioButton2.setOnClickListener(v -> {
//                    String[] selectedOption = {data[0], data[2]};
//                    selectedAnswers.add(selectedOption);
//                });
//
//                radioButton3.setOnClickListener(v -> {
//                    String[] selectedOption = {data[0], data[3]};
//                    selectedAnswers.add(selectedOption);
//                });
//
//                radioButton4.setOnClickListener(v -> {
//                    String[] selectedOption = {data[0], data[4]};
//                    selectedAnswers.add(selectedOption);
//                });
//
//                return view;
//            }
//        };
        listView.setAdapter(adapter);

        new GetQuestionsTask().execute();

        Button submitButton = findViewById(R.id.btnsumbit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSelectedAnswersToServer();
            }
        });
    }
    private void executeAfterDelay() {
        // Post delayed execution of code
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent rs = new Intent(Aptitude.this, aptitude_result.class);

                String userId = getIntent().getStringExtra("UserID");
                String com = getIntent().getStringExtra("com_id");
                String job=getIntent().getStringExtra("job_nm");
                rs.putExtra("com_id",com);
                rs.putExtra("UserID",userId);
                rs.putExtra("job_nm",job);
                startActivity(rs);
            }
        }, DELAY_TIME); // Delay time in milliseconds
    }
    private class GetQuestionsTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://192.168.1.4/Smartaptitude/aptitute_questions.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    StringBuilder response = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    return response.toString();
                } else {
                    Log.e("HTTP_ERROR", "HTTP error code: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String questionText = jsonObject.getString("question");
                        String option1 = jsonObject.getString("option1");
                        String option2 = jsonObject.getString("option2");
                        String option3 = jsonObject.getString("option3");
                        String option4 = jsonObject.getString("option4");
                        String[] question = {questionText, option1, option2, option3, option4};

                        questions.add(question);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendSelectedAnswersToServer() {

        JSONArray selectedAnswersJsonArray = new JSONArray();
        String userId = getIntent().getStringExtra("UserID");
        String com = getIntent().getStringExtra("com_id");
        String job=getIntent().getStringExtra("job_nm");
        for (String question : selectedOptions.keySet()) {
            JSONObject selectedAnswerJsonObject = new JSONObject();
            try {
                selectedAnswerJsonObject.put("question", question);
                selectedAnswerJsonObject.put("selected_option", selectedOptions.get(question));
                selectedAnswerJsonObject.put("userid", userId);
                selectedAnswerJsonObject.put("company_id", com);
                selectedAnswerJsonObject.put("job_nm", job);
                selectedAnswersJsonArray.put(selectedAnswerJsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        new SendAnswersToServerTask().execute(selectedAnswersJsonArray.toString());
    }

    private class SendAnswersToServerTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... jsonData) {
            try {
                URL url = new URL("http://192.168.1.4/Smartaptitude/apti_answer.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(jsonData[0]);
                writer.flush();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {

                    Log.d("SendAnswersToServer", "Data sent successfully");
                    executeAfterDelay();
                } else {

                    Log.e("SendAnswersToServer", "Failed to send data, HTTP error code: " + responseCode);
                }

                writer.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            executeAfterDelay();
        }
    }
}
