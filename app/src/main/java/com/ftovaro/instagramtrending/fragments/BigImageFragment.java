package com.ftovaro.instagramtrending.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ftovaro.instagramtrending.R;
import com.ftovaro.instagramtrending.activities.DetailedPostActivity;
import com.ftovaro.instagramtrending.model.InstagramPost;
import com.ftovaro.instagramtrending.network.AppController;
import com.ftovaro.instagramtrending.utils.DataWrapper;

import java.util.ArrayList;

/**
 * Represents an element of the view pager that has all the big image
 * Created by FelipeTovarMac on 5/12/15.
 */
public class BigImageFragment extends Fragment {
    /** URL of the image **/
    private String imageURL;
    /** list of posts **/
    private ArrayList<InstagramPost> instagramPosts = new ArrayList<>();
    /** unique id that represents the position of the post in the list of posts **/
    private int id;

    public BigImageFragment(){
        // Required empty public constructor
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

    /**
     * Set the URL of the post's image.
     * @param imageURL  URL of the image.
     */
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    /**
     * Set a list of InstagramPosts.
     * @param instagramPosts    list of InstagramPosts.
     */
    public void setInstagramPosts(ArrayList<InstagramPost> instagramPosts){
        this.instagramPosts.addAll(instagramPosts);
    }

    /**
     * Set the id for the current fragment.
     * @param id    unique id for the fragment.
     */
    public void setId(int id){
        this.id = id;
    }
}
