package edu.sabanciuniv.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class NewsActivity extends AppCompatActivity
{
     private String[] catagories = {"ECONOMICS","SPORTS","POLITICS"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 pag= findViewById(R.id.pager);
        SampleAdapter adp= new SampleAdapter(this);
        pag.setAdapter(adp);


        TabLayout lay=findViewById(R.id.tablayout);
        new TabLayoutMediator(
                lay,
                pag,
                new TabLayoutMediator.TabConfigurationStrategy()
                {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(catagories[position]);
                    }
                }

        ).attach();
        setTitle("   CS310 News");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.home_icon);


    }

class SampleAdapter extends FragmentStateAdapter
{


    public SampleAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public SampleAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public SampleAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        return new NewsFragment(position+1);
    }

    @Override
    public int getItemCount()
    {
        return catagories.length;
    }



}



}