package com.wachiramartin.newsapp;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wachiramartin.newsapp.adapter.RecyclerViewAdapter;
import com.wachiramartin.newsapp.adapter.RecyclerViewOnClickListener;
import com.wachiramartin.newsapp.model.Article;
import com.wachiramartin.newsapp.services.RequestManager;
import com.wachiramartin.newsapp.services.onFetchDataListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewOnClickListener, View.OnClickListener {

    private Button categoryButtonBusiness,categoryButtonGeneral,categoryButtonHealth,categoryButtonTechnology,categoryButtonSports,categoryButtonScience,categoryButtonEntertainment;
    private SearchView searchView;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_newsArticles);
        setSupportActionBar(toolbar);

        categoryButtonBusiness = findViewById(R.id.btn_categoryBusiness);
        categoryButtonBusiness.setOnClickListener(this);

        categoryButtonGeneral = findViewById(R.id.btn_categoryGeneral);
        categoryButtonGeneral.setOnClickListener(this);

        categoryButtonHealth = findViewById(R.id.btn_categoryHealth);
        categoryButtonHealth.setOnClickListener(this);

        categoryButtonTechnology = findViewById(R.id.btn_categoryTechnology);
        categoryButtonTechnology.setOnClickListener(this);

        categoryButtonSports = findViewById(R.id.btn_categorySports);
        categoryButtonSports.setOnClickListener(this);

        categoryButtonScience = findViewById(R.id.btn_categoryScience);
        categoryButtonScience.setOnClickListener(this);

        categoryButtonEntertainment = findViewById(R.id.btn_categoryEntertainment);
        categoryButtonEntertainment.setOnClickListener(this);

        setSearchView();

        progressBar = findViewById(R.id.progressBar_listArticles);
        progressBar.setVisibility(View.VISIBLE);

        RequestManager requestManager = new RequestManager(MainActivity.this);
        requestManager.fetchNewsHeadlines(listener, "general", null);

        progressBar.setVisibility(GONE);
    }

    private final onFetchDataListener listener = new onFetchDataListener() {
        @Override
        public void onFetchData(List<Article> newsResponses, String message) {
            setRecyclerView(newsResponses);
     //       Toast.makeText(MainActivity.this, newsResponses.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "An error occurred!!!", Toast.LENGTH_LONG).show();
        }
    };

    private void setSearchView(){
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestManager requestManager = new RequestManager(MainActivity.this);
                requestManager.fetchNewsHeadlines(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setRecyclerView(List<Article> articleList){
        RecyclerView recyclerView = findViewById(R.id.rv_newsHeadlines);
        recyclerView.setHasFixedSize(true);
   /*     recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                HorizontalScrollView horizontalScrollView = findViewById(R.id.horizontalScrollViewButtons);

                if(dy > 0){
                    horizontalScrollView.setVisibility(GONE);
                   // cardView.setVisibility(GONE);
                }else{
                    horizontalScrollView.setVisibility(View.VISIBLE);
                   // cardView.setVisibility(View.VISIBLE);
                }
            }
        });*/
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, articleList, this);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void onClickItem(Article article) {
        Intent intent = new Intent(MainActivity.this, NewsDetails.class);
        intent.putExtra("data", article);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();

        RequestManager requestManager = new RequestManager(this);
        requestManager.fetchNewsHeadlines(listener, category, null);

    }
}