package com.ftovaro.instagramtrending.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ftovaro.instagramtrending.R;
import com.ftovaro.instagramtrending.adapters.BigImageAdapter;
import com.ftovaro.instagramtrending.interfaces.CommunicatorListener;
import com.ftovaro.instagramtrending.interfaces.OnImageSliderListener;
import com.ftovaro.instagramtrending.interfaces.OnPostPressListener;
import com.ftovaro.instagramtrending.model.InstagramPost;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CommunicatorListener  {

    private ViewPager mPager;

    private BigImageAdapter mPagerAdapter;

    private ArrayList<InstagramPost> instagramPosts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new BigImageAdapter(getSupportFragmentManager(), instagramPosts);
        mPager.setAdapter(mPagerAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        mPagerAdapter.setInstagramPosts(instagramPosts);
        mPagerAdapter.notifyDataSetChanged();
    }
}
