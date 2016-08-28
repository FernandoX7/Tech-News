package com.ramirez.fernando.technews.view.newArticle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ramirez.fernando.technews.R;
import com.ramirez.fernando.technews.model.Article;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewArticle extends AppCompatActivity {

    private String articleTitle, articleDescription;

    // Views
    @BindView(R.id.article_title_edit_text)
    EditText articleTitleET;
    @BindView(R.id.article_description_edit_text) EditText articleDescriptionET;
    @BindView(R.id.using_image_checkbox)
    CheckBox usingImageCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publishArticle();
            }
        });

    }

    private void publishArticle() {
        articleTitle = articleTitleET.getText().toString();
        articleDescription = articleDescriptionET.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String currentUser = user.getEmail();

        boolean usingImage = false;
        String testImageURL = "https://img.ifcdn.com/images/8e62b0ad808b1393a027568f406e7b180870b96062b61f5bbec1025bdebb57b7_1.jpg";

        String timestamp = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()); // Feb 27, 2012 5:41:23 PM

        Article article;

        if (usingImageCheckBox.isChecked()) {
            usingImage = true;
            article = new Article(articleTitle, articleDescription, currentUser, testImageURL, timestamp);
        } else {
            article = new Article(articleTitle, articleDescription, currentUser, timestamp);
        }

        myRef.child("articles").push().setValue(article);

        // Reset text fields
        articleTitleET.setText("");
        articleDescriptionET.setText("");

        // Go to the feed
        finish();


    }

}
