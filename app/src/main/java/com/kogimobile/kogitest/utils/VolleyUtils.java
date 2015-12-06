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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
                            //JSONArray jsonArrayInstagram =  response.getJSONArray("data");
                            Log.d("Response:%n %s", response.toString(4));
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
}
