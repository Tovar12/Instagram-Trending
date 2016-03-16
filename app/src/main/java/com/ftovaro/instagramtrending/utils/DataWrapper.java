package com.ftovaro.instagramtrending.utils;

import com.ftovaro.instagramtrending.model.InstagramPost;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Helper class to transfer a list of InstagramPosts through an Intent.
 * Created by FelipeTovar on 15-Mar-16.
 */
public class DataWrapper implements Serializable {
    /** List of posts **/
    private ArrayList<InstagramPost> instagramPosts;

    public DataWrapper(ArrayList<InstagramPost> data) {
        this.instagramPosts = data;
    }

    public ArrayList<InstagramPost> getInstagramPosts() {
        return this.instagramPosts;
    }
}
