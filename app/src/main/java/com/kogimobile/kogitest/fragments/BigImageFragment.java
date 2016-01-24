package com.kogimobile.kogitest.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kogimobile.kogitest.R;

/**
 * Created by FelipeTovarMac on 12/5/15.
 */
public class BigImageFragment extends Fragment {

    private static final String TITLE = "title";

    private String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  rootView =  inflater.inflate(R.layout.big_image_fragment, container, false);

        TextView image = (TextView) rootView.findViewById(R.id.editText1);
        image.setText(title);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        this.title = (getArguments() != null) ? getArguments().getString(TITLE) : "Vacio";
    }

    public BigImageFragment(){}

    public static BigImageFragment newInstance(String title){
        BigImageFragment bigImageFragment = new BigImageFragment();

        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bigImageFragment.setArguments(bundle);
        bigImageFragment.setRetainInstance(true);

        return bigImageFragment;
    }

    public void setBigImage(String url){
        Log.e("error", url);
    }
}
