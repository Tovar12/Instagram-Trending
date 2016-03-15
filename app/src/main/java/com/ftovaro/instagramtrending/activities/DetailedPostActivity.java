package com.ftovaro.instagramtrending.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ftovaro.instagramtrending.R;
import com.ftovaro.instagramtrending.adapters.BigImageAdapter;
import com.ftovaro.instagramtrending.adapters.DetailedPostAdapter;
import com.ftovaro.instagramtrending.fragments.DetailedPostFragment;
import com.ftovaro.instagramtrending.model.InstagramPost;
import com.ftovaro.instagramtrending.utils.DataWrapper;

import java.util.ArrayList;

public class DetailedPostActivity extends AppCompatActivity {

    //private ArrayList<InstagramPost> instagramPosts = new ArrayList<>();

    private ViewPager mPager;

    private DetailedPostAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("post_list");
        ArrayList<InstagramPost> instagramPosts = dw.getInstagramPosts();

        mPager = (ViewPager) findViewById(R.id.pagerDetailed);
        mPagerAdapter = new DetailedPostAdapter(getSupportFragmentManager(), instagramPosts);
        mPager.setAdapter(mPagerAdapter);

    }

}
