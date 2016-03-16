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
 * Created by FelipeTovarMac on 12/5/15.
 */
public class GridImagesAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<InstagramPost> posts;
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

    public void updateDataSet(){
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        NetworkImageView thumbnail;
        TextView title;
    }


}
