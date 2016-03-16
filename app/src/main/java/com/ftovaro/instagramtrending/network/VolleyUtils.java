package com.ftovaro.instagramtrending.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ftovaro.instagramtrending.interfaces.OnDownloadTaskCompleted;
import com.ftovaro.instagramtrending.utils.JsonParser;

import org.json.JSONObject;


/**
 * A helper class to consume a service with the Volley lib.
 * Created by FelipeTovar on 6/12/15.
 */
public class VolleyUtils {

    private static final String URL = "https://api.instagram.com/v1/media/popular?client_id=" +
            "05132c49e9f148ec9b8282af33f88ac7";

    /**
     * Download data from the service and deliver a list of InstagramPosts back to the caller of the
     * method.
     * @param context       context of the caller activity.
     * @param taskCompleted interface that allows to give back an answer with the result of the
     *                       process.
     */
    public static void updatePostList(Context context, final OnDownloadTaskCompleted taskCompleted)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            taskCompleted.onTaskCompleted(JsonParser.extractDataFromJson(response),
                                    false, null);
                        } catch (Exception e) {
                            Log.e("Error", e.getMessage());
                            taskCompleted.onTaskCompleted(null, true, e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                taskCompleted.onTaskCompleted(null, true, error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);
    }

}
