package com.wachiramartin.newsapp.services;

import com.wachiramartin.newsapp.model.Article;
import com.wachiramartin.newsapp.model.NewsAPIResponse;

import java.util.List;

public interface onFetchDataListener {
    //Will fetch the results from the api GET request
    void onFetchData(List<Article> newsResponses, String message);

    //Will handle the error by displaying a message
    void onError(String message);
}
