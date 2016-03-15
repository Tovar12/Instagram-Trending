package com.ftovaro.instagramtrending.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ftovaro.instagramtrending.R;
import com.ftovaro.instagramtrending.adapters.DetailedPostAdapter;
import com.ftovaro.instagramtrending.model.InstagramPost;
import com.ftovaro.instagramtrending.utils.DataWrapper;

import java.util.ArrayList;

public class DetailedPostActivity extends AppCompatActivity {

    private ArrayList<InstagramPost> instagramPosts = new ArrayList<>();
    private Toolbar toolbar;
    private ShareActionProvider mShareActionProvider;
    private String linkPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);

        DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("post_list");
        instagramPosts.addAll(dw.getInstagramPosts());
        int id = getIntent().getIntExtra("id", 0);
        String title = instagramPosts.get(id).getTitle();
        linkPost = instagramPosts.get(id).getLink();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        ViewPager mPager = (ViewPager) findViewById(R.id.pagerDetailed);
        DetailedPostAdapter mPagerAdapter = new DetailedPostAdapter(getSupportFragmentManager(), instagramPosts);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(id);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                String title = instagramPosts.get(position).getTitle();
                toolbar.setTitle(title);
                setSupportActionBar(toolbar);
                linkPost = instagramPosts.get(position).getLink();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_datailed_post, menu);
        // Get the menu item.
        MenuItem menuItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareIntent();

        return true;
    }

    private void setShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, linkPost);
        mShareActionProvider.setShareIntent(shareIntent);
    }


}