package com.ftovaro.instagramtrending.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ftovaro.instagramtrending.R;
import com.ftovaro.instagramtrending.utils.AppController;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedPostFragment extends Fragment {

    private String imageURL, publishDate, author;

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

        return rootView;
    }

    public void setImage(String imageURL){
        this.imageURL = imageURL;
    }

    public void setPublishDate(String publishDate){
        this.publishDate = publishDate;
    }

    public void setAuthor(String author){
        this.author = author;
    }

}
