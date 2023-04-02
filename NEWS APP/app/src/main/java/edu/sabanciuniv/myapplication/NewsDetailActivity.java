package edu.sabanciuniv.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDetailActivity extends AppCompatActivity
{
    ImageView imgDetails;
    TextView txtTitleDetail;
    TextView txtDateDetail;
    TextView textNews;
     int id;
     String c;
    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Bitmap img = (Bitmap) msg.obj;
            imgDetails.setImageBitmap(img);

            return true;
        }
    });

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            News news = (News) msg.obj;
            String strDate = news.getDate();
            String needed = strDate.substring(0,10);
            String [] dateParts = needed.split("-");
            String newDate = dateParts[2] + "/" + dateParts[1] + "/" + dateParts[0];
            txtTitleDetail.setText(news.getTitle());
            txtDateDetail.setText(newDate);
            textNews.setText(news.getText());

            NewsRepo repo = new NewsRepo();
            repo.downloadImage(((NewsApp)getApplication()).srv,imgHandler,news.getImagePath());

            return true;
        }
    });




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);


        id = getIntent().getIntExtra("id",1);
        c= getIntent().getStringExtra("catagory");
        imgDetails =findViewById(R.id.imgDetails);
        txtTitleDetail = findViewById(R.id.txtTitleDetail);
        txtDateDetail = findViewById(R.id.txtDateDetail);
        textNews=findViewById(R.id.textNews);

        NewsRepo repo = new NewsRepo();
        repo.getNewsById(((NewsApp)getApplication()).srv,dataHandler,id);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(c);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.newsdetail_menu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()==R.id.detailtitle)
        {
            Intent i = new Intent(this,CommentsActivity.class);
            i.putExtra("id",id);
            this.startActivity(i);
        }

        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return true;
    }








}