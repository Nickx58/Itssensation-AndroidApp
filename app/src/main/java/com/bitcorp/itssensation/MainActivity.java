package com.bitcorp.itssensation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bitcorp.controller.Constants;
import com.bitcorp.controller.Controller;


public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // layout with a webview


        webView = (WebView) findViewById(R.id.webView);
        controller =  new Controller(this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setAppCacheMaxSize( 10 * 1024 * 1024 ); // 5MB
        webView.getSettings().setAppCachePath( getApplicationContext().getCacheDir().getAbsolutePath() );
        webView.getSettings().setAllowFileAccess( true );
        webView.getSettings().setAppCacheEnabled( true );
        webView.getSettings().setJavaScriptEnabled( true );
        webView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT ); // load online by default

        if ( !controller.isNetworkAvailable() ) { // loading offline
            Log.e("MainActivity --> " , "Loding offline cached webpage");
            webView.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
        }

        webView.loadUrl(Constants.HOME_URL_SITE);

    }
}