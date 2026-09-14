package com.example.cropdiseasedetection;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class replyform extends AppCompatActivity {
    private ProgressBar Progressbar;
    private WebView Webview;

    private String urlToload = "http://"+Network.IP+"/display.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replyform);
        Progressbar = findViewById(R.id.progress);
        Webview = findViewById(R.id.webview);

        Webview.loadUrl(urlToload);
        Webview.setWebViewClient(new WebViewClient());
        Webview.getSettings().setJavaScriptEnabled(true);

        Webview.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int newProgress){
                super.onProgressChanged(view, newProgress);
                Progressbar.setProgress(newProgress);
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(title);
                }
            }

        });

    }

    @Override
    public void onBackPressed() {
        if (Webview.canGoBack()) {
            Webview.goBack();
        } else {
            super.onBackPressed();
        }
    }
//    private ListView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_replyform);
//
//        recyclerView = (ListView) findViewById(R.id.list_item1);
//
//        StrictMode.ThreadPolicy policy =
//                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        HashMap<String, String> param = new HashMap<String, String>();
//
//        String rs = Network.connect("http://" + Network.IP + "/display.php",
//                param);
//
//        List<String> students = Arrays.asList(rs.trim().split("#"));
//
//        List<String[]> studentDataList = new ArrayList<>();
//
//        for (String busString : students) {
//            String[] busData = busString.split("<br>");
//            studentDataList.add(busData);
//        }
//
//        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, R.layout.layout_list1, R.id.displaycropname, studentDataList) {
//            @NonNull
//            @Override
//            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//
//                String[] busData = getItem(position);
//
//                TextView textViewStudentId = view.findViewById(R.id.displaycropname);
//                TextView textViewStudentname = view.findViewById(R.id.displaycropimage);
//                TextView textViewStudentMobile = view.findViewById(R.id.displaycropdes);
//
//                EditText crop = view.findViewById(R.id.replyEditText);
//                Button submit = view.findViewById(R.id.submitButton);
//
//                textViewStudentId.setText(busData[1]);
//                textViewStudentname.setText(busData[2]);
//                textViewStudentMobile.setText(busData[3]);
//
//                submit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        StrictMode.ThreadPolicy policy =
//                                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                        StrictMode.setThreadPolicy(policy);
//                        HashMap<String, String> param = new HashMap<String, String>();
//
//                        param.put("reply", crop.getText().toString());
//                        param.put("id", busData[0]);
//
//                        String rs = Network.connect("http://" + Network.IP + "/reply.php", param);
//
//                        System.out.println(rs);
//
//                        // Remove the item from the list
//                        studentDataList.remove(position);
//
//                        // Notify the adapter about the change
//                        notifyDataSetChanged();
//                    }
//                });
//
//                return view;
//            }
//        };
//        recyclerView.setAdapter(adapter);
//    }
}

