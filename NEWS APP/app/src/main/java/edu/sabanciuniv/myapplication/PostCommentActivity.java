package edu.sabanciuniv.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;

public class PostCommentActivity extends AppCompatActivity {




    EditText Nametext;
    EditText CommentText;
    Button PostButton;
    TextView errorText;
    int id;
    ProgressDialog dig;
    private static final String errortext="All fields should be entered.";

    Handler PostCommentHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            dig.dismiss();
            String commentmessage =  msg.obj.toString();

            if(commentmessage.equals("SUCCESS"))
            {
                finish();
            }

            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);
        id = getIntent().getIntExtra("id",1);
        Nametext=findViewById(R.id.editTextTextPersonName);
        CommentText=findViewById(R.id.editTextTextMultiLine);
        PostButton=findViewById(R.id.buttonComment);

        errorText=findViewById(R.id.ErrorText);
        errorText.setVisibility(View.INVISIBLE);
        setTitle("Post Comment");

        PostButton.setOnClickListener(v->{



            CommentsRepo repo = new CommentsRepo();

            ExecutorService srv = ((NewsApp)getApplication()).srv;

            repo.getCommentsByNewsId(srv,PostCommentHandler,id);


        });
        PostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = Nametext.getText().toString();
                String comment = CommentText.getText().toString();
                if(name.length()==0 || comment.length()==0)
                {
                    errorText.setText(errortext);
                    errorText.setVisibility(View.VISIBLE);

                }
                else{
                    ExecutorService srv = ((NewsApp)getApplication()).srv;
                    CommentsRepo repo = new CommentsRepo();
                    repo.PostComment(srv,PostCommentHandler,name,comment,id);
                    dig = new ProgressDialog(PostCommentActivity.this);
                    dig.setMessage("Uploading the comment....");
                    dig.setTitle("Post Comment");
                    dig.show();
                }

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {


        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return true;
    }

}