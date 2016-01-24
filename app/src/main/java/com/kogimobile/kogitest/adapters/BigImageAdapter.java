package com.kogimobile.kogitest.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 *
 * @author Felipe Tovar on
 * @date 12/6/15.
 * @about
 */
public class BigImageAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;

    public BigImageAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        fragmentList = new ArrayList<>();
    }

    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }

    @Override
    public Fragment getItem(int arg0){
        return fragmentList.get(arg0);
    }

    @Override
    public int getCount(){
        return fragmentList.size();
    }
}
