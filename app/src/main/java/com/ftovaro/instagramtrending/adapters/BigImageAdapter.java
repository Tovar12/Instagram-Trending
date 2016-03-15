package com.ftovaro.instagramtrending.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ftovaro.instagramtrending.fragments.BigImageFragment;
import com.ftovaro.instagramtrending.model.InstagramPost;

import java.util.ArrayList;

/**
 * Created by
 *
 * @author Felipe Tovar on
 * @date 12/6/15.
 * @about
 */
public class BigImageAdapter extends FragmentStatePagerAdapter {

    ArrayList<InstagramPost> instagramPosts;

    public BigImageAdapter(FragmentManager fm, ArrayList<InstagramPost> instagramPosts){
        super(fm);
        this.instagramPosts = instagramPosts;
    }

    @Override
    public Fragment getItem(int position) {
        BigImageFragment fragment = new BigImageFragment();
        fragment.setInstagramPosts(instagramPosts);
        InstagramPost post = instagramPosts.get(position);
        fragment.setImageURL(post.getImageURL());
        fragment.setId(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return instagramPosts.size();
    }

    public void swapPosts(ArrayList<InstagramPost> instagramPosts) {
        this.instagramPosts = instagramPosts;
    }

    public void updateDataSet(){
        notifyDataSetChanged();
    }

    /**
     * Necessary if the ViewPager notifyDataChanged is not working.
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
