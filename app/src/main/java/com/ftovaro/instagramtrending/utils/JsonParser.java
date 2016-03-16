package com.ftovaro.instagramtrending.utils;

import android.util.Log;

import com.ftovaro.instagramtrending.model.InstagramPost;
import com.ftovaro.instagramtrending.model.InstagramUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Parse the complete information obtained from the server.
 * Created by FelipeTovar on 16-Mar-16.
 */
public class JsonParser {

    /** The name of the json array with all the data +*/
    private static final String MAIN_JSON_ARRAY_NAME = "data";
    /** The name of the json object with the timestamp **/
    private static final String JSON_TIME_NAME = "created_time";
    /** The name of the json object with the link of the post **/
    private static final String JSON_LINK_NAME = "link";
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
    /** The name of the json object with the standard resolution url **/
    private static final String JSON_STANDARD_RESOLUTION_NAME = "standard_resolution";
    /** The name of the json object with the url of an image **/
    private static final String JSON_IMAGE_URL_NAME = "url";
    /** The Instagram prefix url **/
    private static final String INSTAGRAM_URL = "https://www.instagram.com/";

    /**
     * Creates an InstagramPost object with the builder pattern and adds It to the ArrayList of
     * InstagramPosts.
     *
     * @param createdTime               date of creation of the post.
     * @param title                     title that the user set for the post.
     * @param userName                  user that made the post.
     * @param fullName                  complete of the user who did the post.
     * @param tagsList                  tags that the user wrote for the post.
     * @param thumbnailURL              small size of the photo of the post.
     * @param standardResolutionURL     normal quality of the photo of the post.
     * @param link                      link to original post on Instagram web site.
     * @param completeTags              all tags concatenated in a single String.
     * @return                          a complete built InstagramPost.
     */
    private static InstagramPost createInstagramPost(String createdTime,
                                           String title,
                                           String userName,
                                           String fullName,
                                           ArrayList<String> tagsList,
                                           String thumbnailURL,
                                           String standardResolutionURL,
                                           String link,
                                           String completeTags){
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
                .imageURL(standardResolutionURL)
                .link(link)
                .completeTags(completeTags)
                .build();

        return instagramPost;
    }

    /**
     * Parse the information of a response.
     * @param response  the response of the server.
     * @return  a list of InstagramPosts.
     */
    public static ArrayList<InstagramPost> extractDataFromJson(JSONObject response){
        ArrayList<InstagramPost> posts = new ArrayList<>();
        try {
            JSONArray jsonArrayInstagram = response.getJSONArray(MAIN_JSON_ARRAY_NAME);

            for(int i = 0; i < jsonArrayInstagram.length(); i++){
                try {
                    JSONObject jsonObjectComplete = jsonArrayInstagram.getJSONObject(i);
                    String createdTime = getDate(jsonObjectComplete.getString(JSON_TIME_NAME));
                    String link = jsonObjectComplete.getString(JSON_LINK_NAME);
                    JSONObject captionObject = jsonObjectComplete.getJSONObject(JSON_CAPTION_NAME);
                    String title = captionObject.getString(JSON_TITLE_NAME);
                    JSONObject fromObject = captionObject.getJSONObject(JSON_FROM_NAME);
                    String userName = fromObject.getString(JSON_USERNAME_NAME);
                    String fullName = fromObject.getString(JSON_FULLNAME_NAME);
                    JSONArray tagsArray = jsonObjectComplete.getJSONArray(JSON_TAGS_NAME);
                    ArrayList<String> tagsList = new ArrayList<>();
                    String completeTags = "";
                    for(int j = 0; j < tagsArray.length(); j++){
                        tagsList.add(tagsArray.get(j).toString());
                        completeTags += "#" + tagsArray.get(j).toString() + ", ";
                    }
                    if(tagsArray.length() > 0){
                        completeTags = completeTags.substring(0, completeTags.length() - 2);
                    }
                    JSONObject imagesObject = jsonObjectComplete.getJSONObject(JSON_IMAGES_NAME);
                    JSONObject thumbnailObject = imagesObject.getJSONObject(JSON_THUMBNAIL_NAME);
                    String thumbnailURL = thumbnailObject.getString(JSON_IMAGE_URL_NAME);
                    JSONObject standardResolutionObject = imagesObject
                            .getJSONObject(JSON_STANDARD_RESOLUTION_NAME);
                    String standardResolutionURL = standardResolutionObject
                            .getString(JSON_IMAGE_URL_NAME);

                    InstagramPost post = createInstagramPost(createdTime, title, userName, fullName,
                            tagsList, thumbnailURL, standardResolutionURL, link, completeTags);

                    posts.add(post);

                } catch (JSONException e) {
                    //If there is an exception is because some data came wrong from the service so we
                    //ignore that data and continue with the next object
                    Log.e("error", "Error of an object from the service");
                }

            }
        } catch (JSONException e) {
            Log.e("Error", e.getMessage());
        }

        return posts;
    }

    /**
     * Converts timestamp to date.
     * @param timeStamp date coded in timestamp format.
     * @return  date decoded.
     */
    private static String getDate(String timeStamp) {
        long time = Long.parseLong(timeStamp);
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date currentTimeZone = new java.util.Date(time * 1000);
        return sdf.format(currentTimeZone);
    }
}
