package com.example.simonedigiorgio.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;

public class second extends AppCompatActivity{

	private WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_second);
    	Intent toy = getIntent();
    	// Get the url string from MainActivity
    	String web = toy.getStringExtra("Url");

    	// Get host         if I use browser.getUrl() the app crash
    	//String host = browser.getUrl();

    	//Creation of webview
    	browser = (WebView) findViewById(R.id.browser);
    	browser.setWebViewClient(new WebViewClient());
    	browser.getSettings().setJavaScriptEnabled(true);

    	// Use the string "web" for load the page
    	browser.loadUrl("" + web);
    }

    @Override
    public void onBackPressed() {
    	if (browser.canGoBack()) {
            browser.goBack();
    	} else {
    		super.onBackPressed();
    	}
    }
}
