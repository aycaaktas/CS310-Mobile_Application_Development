package edu.sabanciuniv.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class CommentsActivity extends AppCompatActivity {



    ProgressBar prg;
    RecyclerView recView;
    int nid;

    Handler dataHandler = new Handler(new Handler.Callback()
    {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<Comments> data = (List<Comments>)msg.obj;
            CommentsAdapter adp = new CommentsAdapter(CommentsActivity.this,data);
            recView.setAdapter(adp);
            recView.setVisibility(View.VISIBLE);
            prg.setVisibility(View.INVISIBLE);

            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        nid = getIntent().getIntExtra("id",1);
        prg = findViewById(R.id.progressBarComment);
        recView = findViewById(R.id.recyclerViewComment);
        recView.setLayoutManager(new LinearLayoutManager(this));
        prg.setVisibility(View.VISIBLE);
        recView.setVisibility(View.INVISIBLE);

         setTitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    protected void onStart() {
        super.onStart();
        CommentsRepo repo = new CommentsRepo();
        repo.getCommentsByNewsId(((NewsApp) getApplication()).srv, dataHandler,nid);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.comments_menu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()==R.id.commentpen)
        {
            Intent i = new Intent(this,PostCommentActivity.class);
            i.putExtra("id",nid);
            this.startActivity(i);
        }

        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return true;
    }











}