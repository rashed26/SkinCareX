package com.example.skindisease;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Sass extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sass);

        // Initialize WebView
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript if required
        webView.setWebViewClient(new WebViewClient());

        // Load your SASS page URL
        webView.loadUrl("file:///android_asset/sass.html");
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