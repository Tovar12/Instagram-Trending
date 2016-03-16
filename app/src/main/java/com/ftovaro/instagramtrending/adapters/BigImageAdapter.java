package com.ftovaro.instagramtrending.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ftovaro.instagramtrending.fragments.BigImageFragment;
import com.ftovaro.instagramtrending.model.InstagramPost;

import java.util.ArrayList;

/**
 * Adapter for the fragment that has the big image.
 * Created by FelipeTovar on 6/12/15.
 */
public class BigImageAdapter extends FragmentStatePagerAdapter {

    /** list of InstagramPosts **/
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
