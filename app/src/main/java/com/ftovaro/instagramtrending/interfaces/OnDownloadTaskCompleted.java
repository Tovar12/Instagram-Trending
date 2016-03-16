package com.ftovaro.instagramtrending.interfaces;

import com.ftovaro.instagramtrending.model.InstagramPost;

import java.util.ArrayList;

/**
 * Allows to communicate to a fragment or activity that data has finish downloaded.
 * Created by FelipeTovar on 6/12/15.
 */

public interface OnDownloadTaskCompleted {
    /**
     * Indicates when a download of data form the service has completed.
     * @param posts     list of InstagramPosts from the service.
     * @param error     true or false telling if there was an error downloading the data.
     * @param message   message with the error.
     */
    void onTaskCompleted(ArrayList<InstagramPost> posts,  boolean error, String message);
}
