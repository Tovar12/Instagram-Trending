package com.ftovaro.instagramtrending.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ftovaro.instagramtrending.R;
import com.ftovaro.instagramtrending.model.InstagramPost;
import com.ftovaro.instagramtrending.network.AppController;

import java.util.ArrayList;

/**
 * Adapter for the fragment that has the grid of images.
 * Created by FelipeTovarMac on 5/12/15.
 */
public class GridImagesAdapter extends BaseAdapter{

    /** Context of the current activity **/
    private Context context;
    /** list of InstagramPosts **/
    private ArrayList<InstagramPost> posts;
    /** loader that user LRU caché for images **/
    ImageLoader imageLoader;

    public GridImagesAdapter(Context context, ArrayList<InstagramPost> posts){
        this.posts = posts;
        this.context = context;
        imageLoader = AppController.getInstance().getImageLoader();
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.grid_image_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.thumbnail = (NetworkImageView) convertView.findViewById(R.id.grid_image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.grid_text);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(posts.get(position).getTitle());
        viewHolder.thumbnail.setImageUrl(posts.get(position).getThumbnailURL(), imageLoader);

        return convertView;

    }

    /**
     * Update the grid with new posts.
     * @param postsLists   the new list of posts.
     */
    public void swapPosts(ArrayList<InstagramPost> postsLists) {
        this.posts = postsLists;
    }

    /**
     * Update the data in the adapter.
     */
    public void updateDataSet(){
        notifyDataSetChanged();
    }

    /**
     * Class that represents the GUI of each element of the grid.
     */
    private static class ViewHolder {
        NetworkImageView thumbnail;
        TextView title;
    }


}
