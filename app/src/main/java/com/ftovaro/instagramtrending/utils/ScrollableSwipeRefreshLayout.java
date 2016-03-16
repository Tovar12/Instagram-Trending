package com.ftovaro.instagramtrending.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.ftovaro.instagramtrending.R;

/**
 * Custom class to control that the vertical swipe in components inside a SwipeRefreshLayout work
 * correctly.
 * Created by FelipeTovar on 15-Mar-16.
 */
public class ScrollableSwipeRefreshLayout extends SwipeRefreshLayout {

    public ScrollableSwipeRefreshLayout(Context context) {
        super(context);
    }

    public ScrollableSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canChildScrollUp() {
        // Condition to check whether scrollview reached at the top while scrolling or not
        return findViewById(R.id.grid).canScrollVertically(-1);
    }
}