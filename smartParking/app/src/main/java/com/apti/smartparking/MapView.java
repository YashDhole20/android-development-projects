package com.apti.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapView extends AppCompatActivity {
    private WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.getSettings().setAllowFileAccess(true);
        wv1.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv1.getSettings().setDomStorageEnabled(true);
        wv1.getSettings().setAllowContentAccess(true);
        wv1.getSettings().setAllowFileAccessFromFileURLs(true);
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl("http://"+Network.IP+"/mapview_mobile.php");
        System.out.println("http://"+Network.IP+"/mapview_mobile.php");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}