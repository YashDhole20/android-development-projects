package com.example.cropdiseasedetection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class suggestion extends AppCompatActivity {
    private ProgressBar Progressbar;
    private WebView Webview;

    private String urlToload = "http://"+Network.IP+"/display2.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
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
}



//package com.example.cropdiseasedetection;
//
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
//public class suggestion extends AppCompatActivity {
//
//    private ListView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_suggestion);
//
//        recyclerView =(ListView)findViewById(R.id.list_item2);
//
//        StrictMode.ThreadPolicy policy =
//                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        HashMap<String, String> param = new HashMap<String, String>();
//
//        String rs = Network.connect("http://" + Network.IP + "/display2.php",
//                param);
//
//        List<String> students = Arrays.asList(rs.trim().split("#"));
//
//        List<String[]> studentDataList = new ArrayList<>();
//
//
//        for (String busString : students) {
//            String[] busData = busString.split("<br>"); // Assuming each bus data string is comma-separated
//            studentDataList.add(busData);
//        }
//
//        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, R.layout.layout_list2,R.id.displaycropname, studentDataList){
//            @NonNull
//            @Override
//            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//
//                String[] busData = getItem(position);
//
//                TextView textViewCropName = view.findViewById(R.id.displaycropname);
//                TextView textViewCropImage=view.findViewById(R.id.displaycropimage);
//                TextView textViewCropDes = view.findViewById(R.id.displaycropdes);
//                TextView textViewreply= view.findViewById(R.id.displayreply);
//
//                textViewCropName.setText(busData[1]);
//                textViewCropImage.setText(busData[2]);
//                textViewCropDes.setText(busData[3]);
//                textViewreply.setText(busData[4]);
//
//
//                return view;
//
//            }
//        };
//        recyclerView.setAdapter(adapter);
//
////        li= new ArrayList<String>();
////        if (rs.equals("0")) {
////
////            Toast.makeText(getApplicationContext(),
////                    "  ur request unsucessfull ", Toast.LENGTH_LONG).show();
////        } else if ( !rs.equals("0")) {
////
////            values = rs.split("_");
////            for (int i = 0; i < values.length; i++) {
////                li.add(values[i].trim());
////				System.out.println("abc"+values[i].length()+" "+values[i]);
////				li.add(values[i].substring(2, values[i].length()));
////				String[] abc=values[i].split(",");
////				for (int k = 0; k < abc.length; k++) {
////					System.out.println("abc"+Arrays.toString(abc));
////					System.out.println("abc---"+abc[1]);
////				}
//    }
//
//}
//
//
//
//
//
//
