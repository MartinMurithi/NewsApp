package com.wachiramartin.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wachiramartin.newsapp.model.Article;

public class NewsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        TextView newsDetailsContent, newsDetailsTitle, newsDetailsPublishTime, newsDetailsAuthor, newsDetailsDescription;
        ImageView newsDetailsImage;

        newsDetailsTitle = findViewById(R.id.tv_newsDetailsTitle);
        newsDetailsPublishTime = findViewById(R.id.tv_newsDetailsPublishedTime);
        newsDetailsAuthor = findViewById(R.id.tv_newDetailsAuthor);
        newsDetailsContent = findViewById(R.id.tv_newsDetailsContent);
        newsDetailsDescription = findViewById(R.id.tv_newsDetailsDescription);
        newsDetailsImage = findViewById(R.id.iv_newsDetailsImage);

        Article article = (Article) getIntent().getSerializableExtra("data");

        newsDetailsTitle.setText(article.getTitle());
        newsDetailsDescription.setText(article.getDescription());
        newsDetailsPublishTime.setText(article.getPublishedAt());
        newsDetailsAuthor.setText(article.getAuthor());
        newsDetailsContent.setText(article.getContent());
        Glide.with(this).load(article.getUrlToImage()).into(newsDetailsImage);

    }
}