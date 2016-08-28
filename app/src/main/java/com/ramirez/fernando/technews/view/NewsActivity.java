package com.ramirez.fernando.technews.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ramirez.fernando.technews.model.Article;
import com.ramirez.fernando.technews.adapter.ArticlesAdapter;
import com.ramirez.fernando.technews.R;
import com.ramirez.fernando.technews.view.newArticle.NewArticle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity {

    private List<Article> articlesList = new ArrayList<>();
    private ArticlesAdapter articlesAdapter;

    // Views
    @BindView(R.id.recycler_view)
    RecyclerView articlesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        articlesAdapter = new ArticlesAdapter(articlesList);
        articlesRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        articlesRecyclerView.setLayoutManager(mLayoutManager);
        articlesRecyclerView.setAdapter(articlesAdapter);

        prepareTestingArticleData();

        // TODO: Make this add a new article - admins only
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Blank 4 now", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            case R.id.action_new_article:
                startActivity(new Intent(this, NewArticle.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void prepareTestingArticleData() {
        Article article = new Article("Article Title", "Article Description");
        articlesList.add(article);

        article = new Article("Article Title 2", "Article Description 2");
        articlesList.add(article);

        article = new Article("Article Title 3", "Article Description 3");
        articlesList.add(article);

        article = new Article("Article Title 4", "Article Description 4");
        articlesList.add(article);

        article = new Article("Article Title 5", "Article Description 5");
        articlesList.add(article);

        articlesAdapter.notifyDataSetChanged();
    }
}
