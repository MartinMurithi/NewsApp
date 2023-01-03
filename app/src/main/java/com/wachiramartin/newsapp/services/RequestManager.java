package com.wachiramartin.newsapp.services;

import android.content.Context;
import android.widget.Toast;

import com.wachiramartin.newsapp.R;
import com.wachiramartin.newsapp.model.NewsAPIResponse;
import com.wachiramartin.newsapp.services.CallNewsApi;
import com.wachiramartin.newsapp.services.onFetchDataListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    Context context;
    private final String baseUrl = "https://newsapi.org/v2/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void fetchNewsHeadlines(onFetchDataListener listener, String category, String query){
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        Call<NewsAPIResponse> call = callNewsApi.callHeadLines("us", category, query, context.getString(R.string.api_key) );

        try {
            call.enqueue(new Callback<NewsAPIResponse>() {
                @Override
                public void onResponse(Call<NewsAPIResponse> call, Response<NewsAPIResponse> response) {
                    if(!response.isSuccessful()){
                        String message = response.message();
                        Toast.makeText(context, " Error : "+message, Toast.LENGTH_SHORT).show();
                    }
                    else if (response.body() != null) {
                        listener.onFetchData(response.body().getArticles(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<NewsAPIResponse> call, Throwable t) {
                    listener.onError("Error : "+t.getMessage());
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
