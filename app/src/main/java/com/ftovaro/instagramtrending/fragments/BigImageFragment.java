package com.ftovaro.instagramtrending.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ftovaro.instagramtrending.R;
import com.ftovaro.instagramtrending.activities.DetailedPostActivity;
import com.ftovaro.instagramtrending.model.InstagramPost;
import com.ftovaro.instagramtrending.utils.AppController;
import com.ftovaro.instagramtrending.utils.DataWrapper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by FelipeTovarMac on 12/5/15.
 */
public class BigImageFragment extends Fragment {

    private String imageURL;

    private ArrayList<InstagramPost> instagramPosts = new ArrayList<>();
    private int id;

    public BigImageFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.big_image_fragment,
                container, false);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView image = (NetworkImageView) rootView.findViewById(R.id.image);
        image.setImageUrl(imageURL, imageLoader);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailedPostActivity.class);
                intent.putExtra("post_list", new DataWrapper(instagramPosts));
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        image.setDefaultImageResId(R.drawable.image_placeholder);

        return rootView;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    public void setInstagramPosts(ArrayList<InstagramPost> instagramPosts){
        this.instagramPosts.addAll(instagramPosts);
    }

    public void setId(int id){
        this.id = id;
    }
}
