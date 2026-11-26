package com.itq.noticiasapp;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad encargada de desplegar la noticia original en un WebView.
 */
public class DetalleNoticiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_noticia);

        WebView webView = findViewById(R.id.webViewDetalle);

        String url = getIntent().getStringExtra("ARTICLE_URL");

        if (url != null && !url.isEmpty()) {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);

            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
        }
    }
}