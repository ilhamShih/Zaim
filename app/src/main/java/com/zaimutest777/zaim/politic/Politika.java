package com.zaimutest777.zaim.politic;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.zaimutest777.zaim.R;
import static com.zaimutest777.zaim.MainActivity.PRIVATEPOLICY;
public class Politika extends AppCompatActivity {

    private WebView webView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.politika);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Политика конфидециальности");

        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(PRIVATEPOLICY);

        //Toast.makeText(getApplicationContext(), PRIVATEPOLICY, Toast.LENGTH_LONG).show();

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