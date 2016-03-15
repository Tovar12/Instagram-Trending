package com.ftovaro.instagramtrending.utils;

import com.ftovaro.instagramtrending.model.InstagramPost;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by FelipeTovar on 15-Mar-16.
 */
public class DataWrapper implements Serializable {

    private ArrayList<InstagramPost> instagramPosts;

    public DataWrapper(ArrayList<InstagramPost> data) {
        this.instagramPosts = data;
    }

    public ArrayList<InstagramPost> getInstagramPosts() {
        return this.instagramPosts;
    }
}
