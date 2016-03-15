package com.kogimobile.kogitest.adapters;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.kogimobile.kogitest.R;
import com.kogimobile.kogitest.fragments.BigImageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 *
 * @author Felipe Tovar on
 * @date 12/6/15.
 * @about
 */
public class BigImageAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 5;

    public BigImageAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new BigImageFragment();
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
    
}
