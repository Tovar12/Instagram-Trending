package com.ftovaro.instagramtrending.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ftovaro.instagramtrending.fragments.BigImageFragment;
import com.ftovaro.instagramtrending.fragments.Images;
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
        fragment.setImage(instagramPosts.get(position).getImageURL());
        return fragment;
    }

    @Override
    public int getCount() {
        return instagramPosts.size();
    }

    public void setInstagramPosts(ArrayList<InstagramPost> instagramPosts){
        this.instagramPosts.addAll(instagramPosts);
    }
    
}
