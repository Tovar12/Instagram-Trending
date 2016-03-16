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
 * Represents an element of the view pager that has all the detailed posts.
 * Created by FelipeTovarMac on 5/12/15.
 */
public class DetailedPostFragment extends Fragment {

    /** Attributes of an InstagramPost **/
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

    /**
     * Set the URL of the post's image.
     * @param imageURL  URL of the image.
     */
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    /**
     * Set the publish date of the post.
     * @param publishDate   date of publish of the post.
     */
    public void setPublishDate(String publishDate){
        this.publishDate = publishDate;
    }

    /**
     * Set the author of the post.
     * @param author    username of the author of the post.
     */
    public void setAuthor(String author){
        this.author = author;
    }

    /**
     * Set the URL of the profile of the post.
     * @param profileURL    URL to the original post.
     */
    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    /**
     * Tags used on the post.
     * @param tag   String with all the tags concatenated.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}
