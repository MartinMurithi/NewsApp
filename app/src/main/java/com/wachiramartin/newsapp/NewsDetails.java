package com.wachiramartin.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wachiramartin.newsapp.model.Article;

public class NewsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Toolbar toolbar = findViewById(R.id.toolbar_newsDetails);
        setSupportActionBar(toolbar);

        ProgressBar progressBar = findViewById(R.id.progressBar_newsDetails);


        Article article = (Article) getIntent().getSerializableExtra("data");

        progressBar.setVisibility(View.VISIBLE);
        WebView webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.getJavaScriptEnabled();
        webView.setWebChromeClient(new WebChromeClient());
        String url = article.getUrl();
        webView.loadUrl(url);

        progressBar.setVisibility(View.GONE);

    }
}