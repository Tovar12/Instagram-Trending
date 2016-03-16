package com.ftovaro.instagramtrending.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ftovaro.instagramtrending.R;
import com.ftovaro.instagramtrending.activities.WebActivity;
import com.ftovaro.instagramtrending.network.AppController;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedPostFragment extends Fragment {

    private String imageURL, publishDate, author, profileURL, tag;

    public DetailedPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_detailed_post,
                container, false);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView image = (NetworkImageView) rootView.findViewById(R.id.image);
        image.setImageUrl(imageURL, imageLoader);

        TextView publishDateText = (TextView) rootView.findViewById(R.id.publish_date);
        publishDateText.setText(publishDate);

        TextView authorText = (TextView) rootView.findViewById(R.id.author);
        authorText.setText(author);

        TextView tags = (TextView) rootView.findViewById(R.id.tag);
        tags.setText(tag);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("profileURL", profileURL);
                startActivity(intent);
            }
        });

        return rootView;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    public void setPublishDate(String publishDate){
        this.publishDate = publishDate;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
