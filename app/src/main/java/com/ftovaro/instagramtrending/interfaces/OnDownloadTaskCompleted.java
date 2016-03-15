package com.ftovaro.instagramtrending.interfaces;

import com.ftovaro.instagramtrending.model.InstagramPost;

import java.util.ArrayList;

/**
 * Created by
 *
 * @author Felipe Tovar on
 * @date 12/6/15.
 * @about
 */
public interface OnDownloadTaskCompleted {
    void onTaskCompleted(ArrayList<InstagramPost> posts,  boolean error, String message);
}
