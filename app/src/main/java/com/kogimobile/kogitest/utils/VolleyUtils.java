package com.kogimobile.kogitest.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kogimobile.kogitest.R;
import com.kogimobile.kogitest.model.InstagramPost;
import com.kogimobile.kogitest.model.InstagramUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 *
 * @author Felipe Tovar on
 * @date 12/6/15.
 * @about   A helper class to consume a service with the Volley lib.
 */
public class VolleyUtils {

    private static ArrayList<InstagramPost> posts;

    public static void updatePostList(Context context) {

        posts = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.url_instagram_service);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArrayInstagram =  response.getJSONArray("data");
                            for(int i = 0; i < jsonArrayInstagram.length(); i++){
                                JSONObject jsonObjectComplete = jsonArrayInstagram.getJSONObject(i);
                                String createdTime = jsonObjectComplete.getString("created_time");
                                JSONObject captionObject = jsonObjectComplete
                                        .getJSONObject("caption");
                                String title = captionObject.getString("text");
                                JSONObject fromObject = captionObject.getJSONObject("from");
                                String userName = fromObject.getString("username");
                                String fullName = fromObject.getString("full_name");
                                JSONArray tagsArray = jsonObjectComplete.getJSONArray("tags");
                                ArrayList<String> tagsList = new ArrayList<>();
                                for(int j = 0; j < tagsArray.length(); j++){
                                    tagsList.add(tagsArray.get(j).toString());
                                }
                                createInstagramPost(createdTime, title, userName, fullName,
                                        tagsList);
                            }
                            //Log.d("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        //Adding request to request queue
        //AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        queue.add(jsonObjectRequest);
    }

    public static void createInstagramPost(String createdTime,
                                           String title,
                                           String userName,
                                           String fullName,
                                           ArrayList<String> tagsList){
        StringBuilder urlProfile = new StringBuilder()
                .append("www.instagram.com/")
                .append(userName);

        InstagramUser instagramUser = new InstagramUser.InstagramUserBuiler()
                .userName(userName)
                .fullName(fullName)
                .urlProfile(urlProfile.toString())
                .build();

        InstagramPost instagramPost = new InstagramPost.InstagramPostBuilder()
                .timeStamp(createdTime)
                .title(title)
                .tags(tagsList)
                .instagramUser(instagramUser)
                .build();
    }
}
