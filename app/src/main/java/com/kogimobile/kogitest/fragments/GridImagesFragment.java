package com.kogimobile.kogitest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.kogimobile.kogitest.R;
import com.kogimobile.kogitest.adapters.grid_images_adapter;

/**
 * Created by FelipeTovarMac on 12/5/15.
 */
public class GridImagesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  rootView =  inflater.inflate(R.layout.grid_images_fragment, container, false);

        GridView gridview = (GridView) rootView.findViewById(R.id.gridViewImages);
        gridview.setAdapter(new grid_images_adapter(getActivity()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
