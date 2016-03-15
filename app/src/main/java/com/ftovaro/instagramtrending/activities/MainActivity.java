package com.ftovaro.instagramtrending.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.ftovaro.instagramtrending.R;
import com.ftovaro.instagramtrending.adapters.BigImageAdapter;
import com.ftovaro.instagramtrending.fragments.GridImagesFragment;
import com.ftovaro.instagramtrending.interfaces.CommunicatorListener;
import com.ftovaro.instagramtrending.interfaces.OnImageSliderListener;
import com.ftovaro.instagramtrending.interfaces.OnPostPressListener;
import com.ftovaro.instagramtrending.interfaces.OnRefreshListener;
import com.ftovaro.instagramtrending.model.InstagramPost;
import com.ftovaro.instagramtrending.utils.ScrollableSwipeRefreshLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CommunicatorListener,
        SwipeRefreshLayout.OnRefreshListener  {

    private ViewPager mPager;

    private BigImageAdapter mPagerAdapter;

    private OnRefreshListener refreshListener;

    private ScrollableSwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<InstagramPost> instagramPosts = new ArrayList<>();

    private static final int START_POSITION_SWIPE = 0;

    private static final int END_POSITION_SWIPE = 300;

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostPressed(int position) {
        mPager.setCurrentItem(position);
    }


    /*
    TODO Validar si debería ir
    @Override
    public void onBackPressed() {
        if(mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
    */

    @Override
    public void sendInstagramPosts(ArrayList<InstagramPost> instagramPosts) {
        mPagerAdapter.swapPosts(instagramPosts);
        mPagerAdapter.updateDataSet();
        mPager.setCurrentItem(0);
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
