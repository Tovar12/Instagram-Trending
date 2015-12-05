package com.kogimobile.kogitest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kogimobile.kogitest.R;

/**
 * Created by FelipeTovarMac on 12/5/15.
 */
public class GridImagesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  rootView =  inflater.inflate(R.layout.grid_images_fragment, container, false);

        return rootView;
    }
}
