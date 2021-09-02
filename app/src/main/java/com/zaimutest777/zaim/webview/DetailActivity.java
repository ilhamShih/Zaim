package com.zaimutest777.zaim.webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.zaimutest777.zaim.R;

public class DetailActivity extends AppCompatActivity {

    private WebView webView;
    private String category;



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        Intent intent = getIntent();
        category = intent.getStringExtra("url");


            webView = (WebView) findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setAllowContentAccess(true);
            settings.setDomStorageEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(category);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String category) {
                return false;
            }


            public void onPageStarted(final WebView view, final String category, final Bitmap favicon) {
                progress.setVisibility(View.VISIBLE);
                super.onPageStarted(view, category, favicon);
            }

            @Override
            public void onPageFinished(final WebView view, final String category) {
                progress.setVisibility(View.GONE);
                super.onPageFinished(view, category);
            }
        });


    }



    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}