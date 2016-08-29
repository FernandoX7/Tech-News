package com.ramirez.fernando.technews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramirez.fernando.technews.model.Article;
import com.ramirez.fernando.technews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fernandoramirez on 7/19/16.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private List<Article> articleList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, timestamp, author;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.article_row_title);
            description = (TextView) view.findViewById(R.id.article_row_description);
            image = (ImageView) view.findViewById(R.id.article_row_image);
            timestamp = (TextView) view.findViewById(R.id.article_row_timestamp);
            author = (TextView) view.findViewById(R.id.article_row_author);
            context = view.getContext();
        }
    }


    public ArticlesAdapter(List<Article> articlesList) {
        this.articleList = articlesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.timestamp.setText(article.getTimestamp());
        holder.author.setText(article.getAuthor());

        Picasso.with(context)
                .load(article.getImage())
                .placeholder(R.drawable.starry_night)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

}
