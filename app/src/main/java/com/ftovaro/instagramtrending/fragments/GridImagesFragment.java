package com.ftovaro.instagramtrending.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.ftovaro.instagramtrending.R;
import com.ftovaro.instagramtrending.adapters.GridImagesAdapter;
import com.ftovaro.instagramtrending.interfaces.CommunicatorListener;
import com.ftovaro.instagramtrending.interfaces.OnRefreshListener;
import com.ftovaro.instagramtrending.model.InstagramPost;
import com.ftovaro.instagramtrending.interfaces.OnDownloadTaskCompleted;
import com.ftovaro.instagramtrending.network.VolleyUtils;

import java.util.ArrayList;

/**
 * Manage the grid view of the application.
 * Created by FelipeTovarMac on 12/5/15.
 */
public class GridImagesFragment extends Fragment implements AdapterView.OnItemClickListener,
        OnRefreshListener{

    /** List of InstagramPosts **/
    private static ArrayList<InstagramPost> posts = new ArrayList<>();
    /** ProgressDialog that shows a loading text **/
    private static ProgressDialog pDialog;
    /** An adapter that has the settings of the list **/
    private static GridImagesAdapter gridImagesAdapter;
    /** Listener with parent Activity **/
    private CommunicatorListener communicatorListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  rootView =  inflater.inflate(R.layout.grid_images_fragment, container, false);

        GridView gridview = (GridView) rootView.findViewById(R.id.grid);
        gridImagesAdapter = new GridImagesAdapter(this.getActivity().getApplicationContext(),
                posts);
        downloadInstagramData();
        gridview.setAdapter(gridImagesAdapter);
        gridview.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void refreshPosts() {
        posts.clear();
        VolleyUtils.updatePostList(getActivity().getApplicationContext(),
                new OnDownloadTaskCompleted() {
                    @Override
                    public void onTaskCompleted(ArrayList<InstagramPost> instagramPosts,
                                                boolean error, String message) {
                        if(!error){
                            posts.addAll(instagramPosts);
                            gridImagesAdapter.swapPosts(posts);
                            gridImagesAdapter.updateDataSet();
                            communicatorListener.sendInstagramPosts(posts);
                            communicatorListener.refreshCompleted();
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    getString(R.string.error_updating_view),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * Send the request to download data from the service and update the view with the response.
     */
    private void downloadInstagramData(){
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage(getString(R.string.loading));
        pDialog.show();
        VolleyUtils.updatePostList(getActivity().getApplicationContext(),
                new OnDownloadTaskCompleted() {
            @Override
            public void onTaskCompleted(ArrayList<InstagramPost> instagramPosts,
                                        boolean error, String message) {
                if(!error){
                    posts.addAll(instagramPosts);
                    gridImagesAdapter.swapPosts(posts);
                    gridImagesAdapter.updateDataSet();
                    hidePDialog();
                    communicatorListener.sendInstagramPosts(posts);
                } else {
                    hidePDialog();
                    Toast.makeText(getActivity().getApplicationContext(),
                            getString(R.string.error_updating_view),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private static void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        communicatorListener.onPostPressed(position);

    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            communicatorListener = (CommunicatorListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement CommunicatorListener");
        }
    }
}
