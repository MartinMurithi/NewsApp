package com.wachiramartin.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wachiramartin.newsapp.R;
import com.wachiramartin.newsapp.model.Article;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final Context context;
    private final List<Article> articles;
    RecyclerViewOnClickListener recyclerViewOnClickListener;

    public RecyclerViewAdapter(Context context, List<Article> articles, RecyclerViewOnClickListener recyclerViewOnClickListener) {
        this.context = context;
        this.articles = articles;
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_headline_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tv_headline.setText(articles.get(position).getTitle());
        holder.tv_source.setText(articles.get(position).getSource().getName());

        if(articles.get(position).getUrlToImage() != null){
            Glide.with(context).load(articles.get(position).getUrlToImage()).into(holder.iv_imgHeadline);
        }
        holder.cardView.setOnClickListener(v -> {
            Article article= articles.get(position);
            recyclerViewOnClickListener.onClickItem(article);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_headline, tv_source;
        ImageView iv_imgHeadline;

        //Will be used for the click listener
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_headline = itemView.findViewById(R.id.tv_newsHeadlines);
            tv_source = itemView.findViewById(R.id.tv_headlines_source);
            iv_imgHeadline = itemView.findViewById(R.id.iv_imageHeadlines);
            cardView = itemView.findViewById(R.id.cv_newsHeadlineItem);

        }

    }
}
