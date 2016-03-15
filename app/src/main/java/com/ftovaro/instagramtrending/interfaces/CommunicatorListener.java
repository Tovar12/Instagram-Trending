package com.ftovaro.instagramtrending.interfaces;

import com.ftovaro.instagramtrending.model.InstagramPost;

import java.util.ArrayList;

/**
 * Created by FelipeTovar on 14-Mar-16.
 */
public interface CommunicatorListener {
    void sendInstagramPosts(ArrayList<InstagramPost> instagramPosts);
    void onPostPressed(int position);
}
