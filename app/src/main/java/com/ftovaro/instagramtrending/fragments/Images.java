package com.ftovaro.instagramtrending.fragments;

import java.util.ArrayList;

import com.ftovaro.instagramtrending.R;

/**
 * Created by
 *
 * @author Felipe Tovar on
 * @date 12/6/15.
 * @about
 */
public class Images {

    private ArrayList<Integer> imageId;

    public Images(){
        imageId = new ArrayList<>();
        imageId.add(R.drawable.pika);
        imageId.add(R.drawable.pika);
        imageId.add(R.drawable.pika);
        imageId.add(R.drawable.pika);
        imageId.add(R.drawable.pika);
    }

    public ArrayList<Integer> getImageItem(){
        return imageId;
    }
}
