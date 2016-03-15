package com.kogimobile.kogitest.fragments;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kogimobile.kogitest.R;

/**
 * Created by FelipeTovarMac on 12/5/15.
 */
public class BigImageFragment extends Fragment {

    private static final String TITLE = "title";

    private ImageView image;
    private Bitmap myBitmap;
    private Integer itemData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup  rootView = (ViewGroup) inflater.inflate(R.layout.big_image_fragment,
                container, false);
        return rootView;
    }
}
