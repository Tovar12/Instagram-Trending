package com.ftovaro.instagramtrending.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.ftovaro.instagramtrending.R;
import com.ftovaro.instagramtrending.adapters.BigImageAdapter;
import com.ftovaro.instagramtrending.fragments.GridImagesFragment;
import com.ftovaro.instagramtrending.interfaces.CommunicatorListener;
import com.ftovaro.instagramtrending.interfaces.OnRefreshListener;
import com.ftovaro.instagramtrending.model.InstagramPost;
import com.ftovaro.instagramtrending.utils.ScrollableSwipeRefreshLayout;

import java.util.ArrayList;

/**
 * Main activity of the app. Has two fragments, BigImage and GridImages.
 * Created by FelipeTovar on 6/12/15.
 */
public class MainActivity extends AppCompatActivity implements CommunicatorListener,
        SwipeRefreshLayout.OnRefreshListener  {

    /** Big image slider **/
    private ViewPager mPager;
    /** Adapter of the view pager **/
    private BigImageAdapter mPagerAdapter;
    /** Interface to control the swipe to refresh events **/
    private OnRefreshListener refreshListener;
    /** Custom Swipe Refresh to manage correctly the scroll **/
    private ScrollableSwipeRefreshLayout swipeRefreshLayout;
    /** List of Instagram posts **/
    private ArrayList<InstagramPost> instagramPosts = new ArrayList<>();
    /** Start position of the swipe **/
    private static final int START_POSITION_SWIPE = 0;
    /** Represents how long the swipe should go **/
    private static final int END_POSITION_SWIPE = 300;
    /** Start position of the view pager **/
    private static final int START_POSITION_PAGER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (ScrollableSwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new BigImageAdapter(getSupportFragmentManager(), instagramPosts);
        mPager.setAdapter(mPagerAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setProgressViewOffset(false, START_POSITION_SWIPE, END_POSITION_SWIPE);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        refreshListener.refreshPosts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onPostPressed(int position) {
        mPager.setCurrentItem(position);
    }

    @Override
    public void sendInstagramPosts(ArrayList<InstagramPost> instagramPosts) {
        mPagerAdapter.swapPosts(instagramPosts);
        mPagerAdapter.updateDataSet();
        mPager.setCurrentItem(START_POSITION_PAGER);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        try {
            if(fragment instanceof GridImagesFragment){
                try {
                    refreshListener = (OnRefreshListener) fragment;
                }catch (ClassCastException e){
                    // The activity doesn't implement the interface, throw exception
                    throw new ClassCastException(fragment.toString()
                            + " must implement OnRefreshListener");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshCompleted() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
