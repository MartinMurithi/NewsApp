package com.wachiramartin.newsapp;

import com.wachiramartin.newsapp.model.NewsAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallNewsApi {
    @GET("top-headlines")//This is an endpoint
    Call<NewsAPIResponse> callHeadLines(
         @Query("country") String country,
         @Query("category") String category,
         @Query("q") String searchKeyword,
         @Query("apiKey") String apiKey
    );
}
