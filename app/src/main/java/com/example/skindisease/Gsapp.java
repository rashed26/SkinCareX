package com.example.skindisease;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Gsapp extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsapp);

        // Initialize WebView
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript if required
        webView.setWebViewClient(new WebViewClient());

        // Load your SASS page URL
        webView.loadUrl("file:///android_asset/gsapp.html");
    }

    @Override
    public void onBackPressed() {
        // Check if WebView can go back
        if (webView.canGoBack()) {
            webView.goBack(); // Go back in WebView history
        } else {
            super.onBackPressed(); // Otherwise, perform default back button behavior
        }
    }
}