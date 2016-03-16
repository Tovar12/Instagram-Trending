package com.ftovaro.instagramtrending.interfaces;

import com.ftovaro.instagramtrending.model.InstagramPost;

import java.util.ArrayList;

/**
 * Allows communication between fragment and parent activity.
 * Created by FelipeTovar on 14-Mar-16.
 */
public interface CommunicatorListener {
    /**
     * Transfer a list of InstagramPosts.
     * @param instagramPosts    current list of posts.
     */
    void sendInstagramPosts(ArrayList<InstagramPost> instagramPosts);

    /**
     * Send the position of the post which had been clicked.
     * @param position  position of the posts clicked.
     */
    void onPostPressed(int position);

    /**
     * Indicates that swipe to refresh has completed the process.
     */
    void refreshCompleted();
}
