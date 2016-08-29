package com.ramirez.fernando.technews.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.client.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ramirez.fernando.technews.model.Article;
import com.ramirez.fernando.technews.adapter.ArticlesAdapter;
import com.ramirez.fernando.technews.R;
import com.ramirez.fernando.technews.util.Print;
import com.ramirez.fernando.technews.view.newArticle.NewArticle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity {

    private List<Article> articlesList = new ArrayList<>();
    private ArticlesAdapter articlesAdapter;
    private DatabaseReference mDatabase;
    private DatabaseReference myRef;

    // Views
    @BindView(R.id.recycler_view)
    RecyclerView articlesRecyclerView;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        // Show loading view while firebase loads
        loading = new ProgressDialog(this);
        loading.setTitle("Loading");
        loading.setMessage("Tech News is loading...");
        loading.show();

        articlesAdapter = new ArticlesAdapter(articlesList);
        articlesRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        articlesRecyclerView.setLayoutManager(mLayoutManager);
        articlesRecyclerView.setAdapter(articlesAdapter);

        readArticles();
//        prepareTestingArticleData();

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

    // Read data from firebase and populate the recycler view
    private void readArticles() {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        myRef = mDatabase.getRef();

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                loading.dismiss();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Article article = snapshot.getValue(Article.class);
                    Print.log(article.toString());
                    articlesList.add(article);
                    refreshAdapter();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Print.log("onChildChanged:" + dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Print.log("onChildRemoved:" + dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Print.log("onChildMoved:" + dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Print.log("postComments:onCancelled" + databaseError.toException());
                Toast.makeText(getApplicationContext(), "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        myRef.addChildEventListener(childEventListener);
    }

    private void prepareTestingArticleData() {
        Article article = new Article("Article Title", "Article Description", "username", "image", "\"Aug 37, 2016 6:55:14 PM\"");
        articlesList.add(article);

        article = new Article("Article Title 2", "Article Description 2", "username", "image", "\"Aug 37, 2016 7:55:14 PM\"");
        articlesList.add(article);

        article = new Article("Article Title 3", "Article Description 3", "username", "image", "\"Aug 37, 2016 8:55:14 PM\"");
        articlesList.add(article);

        article = new Article("Article Title 4", "Article Description 4", "username", "image", "\"Aug 37, 2016 9:55:14 PM\"");
        articlesList.add(article);

        article = new Article("Article Title 5", "Article Description 5", "username", "image", "\"Aug 37, 2016 10:55:14 PM\"");
        articlesList.add(article);

        refreshAdapter();
    }

    private void refreshAdapter() {
        articlesAdapter.notifyDataSetChanged();
    }
}
