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
import com.kogimobile.kogitest.fragments.GridImagesFragment;
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
    /** The name of the json array with all the data +*/
    private static final String MAIN_JSONARRAY_NAME = "data";
    /** The name of the json object with the timestamp **/
    private static final String JSON_TIME_NAME = "created_time";
    /** The name of the json object with the caption data **/
    private static final String JSON_CAPTION_NAME = "caption";
    /** The name of the json object with the title **/
    private static final String JSON_TITLE_NAME = "text";
    /** The name of the json object with the information about the user **/
    private static final String JSON_FROM_NAME = "from";
    /** The name of the json object with the username **/
    private static final String JSON_USERNAME_NAME = "username";
    /** The name of the json object with the fullname of the user **/
    private static final String JSON_FULLNAME_NAME = "full_name";
    /** The name of the json array with all the tags **/
    private static final String JSON_TAGS_NAME = "tags";
    /** The name of the json array with the image in different sizes **/
    private static final String JSON_IMAGES_NAME = "images";
    /** The name of the json object with the thumbnail url **/
    private static final String JSON_THUMBNAIL_NAME = "thumbnail";
    /** The name of the json object with the url of an image **/
    private static final String JSON_IMAGE_URL_NAME = "url";
    /** The Instagram prefix url **/
    private static final String INSTAGRAM_URL = "www.instagram.com/";


    public static void updatePostList(Context context, final OnDownloadTaskCompleted taskCompleted)
    {
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
                            JSONArray jsonArrayInstagram =  response
                                    .getJSONArray(MAIN_JSONARRAY_NAME);
                            extractDataFromJson(jsonArrayInstagram);
                            taskCompleted.onTaskCompleted(posts, false, null);
                            //Log.d("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            taskCompleted.onTaskCompleted(null, true, e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                taskCompleted.onTaskCompleted(null, true, error.getMessage());
            }
        });

        //Adding request to request queue
        //AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        queue.add(jsonObjectRequest);
    }

    /**
     * Creates an InstagramPost object with the builder pattern and adds It to the ArrayList of
     * InstagramPosts.
     * @param createdTime
     * @param title
     * @param userName
     * @param fullName
     * @param tagsList
     * @param thumbnailURL
     */
    public static void createInstagramPost(String createdTime,
                                           String title,
                                           String userName,
                                           String fullName,
                                           ArrayList<String> tagsList,
                                           String thumbnailURL){
        StringBuilder urlProfile = new StringBuilder()
                .append(INSTAGRAM_URL)
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
                .thumbnailURL(thumbnailURL)
                .build();

        posts.add(instagramPost);
    }

    private static void extractDataFromJson(JSONArray jsonArrayInstagram){
        for(int i = 0; i < jsonArrayInstagram.length(); i++){
            try{
                JSONObject jsonObjectComplete = jsonArrayInstagram.getJSONObject(i);
                String createdTime = jsonObjectComplete.getString(JSON_TIME_NAME);
                JSONObject captionObject = jsonObjectComplete.getJSONObject(JSON_CAPTION_NAME);
                String title = captionObject.getString(JSON_TITLE_NAME);
                JSONObject fromObject = captionObject.getJSONObject(JSON_FROM_NAME);
                String userName = fromObject.getString(JSON_USERNAME_NAME);
                String fullName = fromObject.getString(JSON_FULLNAME_NAME);
                JSONArray tagsArray = jsonObjectComplete.getJSONArray(JSON_TAGS_NAME);
                ArrayList<String> tagsList = new ArrayList<>();
                for(int j = 0; j < tagsArray.length(); j++){
                    tagsList.add(tagsArray.get(j).toString());
                }
                JSONObject imagesObject = jsonObjectComplete.getJSONObject(JSON_IMAGES_NAME);
                JSONObject thumbnailObject = imagesObject.getJSONObject(JSON_THUMBNAIL_NAME);
                String thumbnailURL = thumbnailObject.getString(JSON_IMAGE_URL_NAME);
                createInstagramPost(createdTime, title, userName, fullName, tagsList, thumbnailURL);
            }catch (JSONException je){
                //If there is an exception is because some data came wrong from the service so we
                //ignore that data and continue with the next object
                Log.e("error","Error of an object from the service");
            }
        }
    }
}
