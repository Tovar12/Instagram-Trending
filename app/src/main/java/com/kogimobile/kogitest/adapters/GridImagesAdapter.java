package com.kogimobile.kogitest.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.kogimobile.kogitest.R;
import com.kogimobile.kogitest.model.InstagramPost;
import com.kogimobile.kogitest.utils.AppController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FelipeTovarMac on 12/5/15.
 */
public class GridImagesAdapter extends BaseAdapter{

    private Activity activity;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<InstagramPost> posts;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public GridImagesAdapter(Activity activity, ArrayList<InstagramPost> posts){
        this.activity = activity;
        this.posts = posts;
        this.context = activity.getApplicationContext();
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
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
        View myView = convertView;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            //grid = new View(context);
            myView = inflater.inflate(R.layout.grid_image_item, parent, false);

        }
        TextView textView = (TextView) myView.findViewById(R.id.grid_text);
        NetworkImageView imageView = (NetworkImageView) myView.findViewById(R.id.grid_image);
        textView.setText(posts.get(position).getTitle());
        //imageView.setImageResource(position);
        String url = "https://scontent.cdninstagram.com/hphotos-xtf1/t51.2885-15/s150x150/e35/12292825_846485895465349_1202246531_n.jpg";
        imageView.setImageUrl(posts.get(position).getThumbnailURL() ,imageLoader);

        return myView;

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

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
            R.drawable.sample_0,
            R.drawable.sample_3,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
            R.drawable.sample_0,
            R.drawable.sample_3,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
            R.drawable.sample_0,
            R.drawable.sample_3,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
            R.drawable.sample_0,
            R.drawable.sample_3,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
            R.drawable.sample_0,
            R.drawable.sample_3,
            R.drawable.sample_1
    };


}
