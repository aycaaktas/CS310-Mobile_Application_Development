package edu.sabanciuniv.myapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.os.Message;
import java.util.List;


public class NewsFragment extends Fragment
{

    ProgressBar prg;
    RecyclerView recView;
    int catagory;



    public NewsFragment(int i)
    {
       this.catagory=i;
    }

    Handler dataHandler = new Handler(new Handler.Callback()
    {
        @Override
        public boolean handleMessage(@NonNull Message msg)
        {
            List<News> data = (List<News>)msg.obj;

            Activity mInstance = getActivity();
            NewsAdapter adp = new NewsAdapter(mInstance,data);
            recView.setAdapter(adp);
            recView.setVisibility(View.VISIBLE);
            prg.setVisibility(View.INVISIBLE);

            return true;
        }
    });


    @Override
    public View onCreateView(  LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        prg = v.findViewById(R.id.progressBar);
        recView = v.findViewById(R.id.recyclerview);
        Activity mInstance = getActivity();
        recView.setLayoutManager(new LinearLayoutManager(mInstance));
        prg.setVisibility(View.VISIBLE);
        recView.setVisibility(View.INVISIBLE);

        NewsRepo repo = new NewsRepo();
        repo.getNewsByCategoryId(((NewsApp)mInstance.getApplication()).srv, dataHandler,catagory);


        return v;
    }


}