package com.ftovaro.instagramtrending.interfaces;

/**
 * Allows communication the end of a swipe to refresh process.
 * Created by FelipeTovar on 14-Mar-16.
 */
public interface OnRefreshListener {
    /**
     * Indicate that a swipe to refresh has finished the process.
     */
    void refreshPosts();
}
