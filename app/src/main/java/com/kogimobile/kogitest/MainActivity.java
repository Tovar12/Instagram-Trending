package com.kogimobile.kogitest;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kogimobile.kogitest.adapters.BigImageAdapter;
import com.kogimobile.kogitest.fragments.BigImageFragment;
import com.kogimobile.kogitest.utils.OnPostPressListener;

public class MainActivity extends AppCompatActivity implements OnPostPressListener {

    ViewPager viewPager = null;
    BigImageAdapter bigImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void inicializeBigImageAdapter(String url){
        viewPager = (ViewPager) findViewById(R.id.pager);
        BigImageAdapter adapter = new BigImageAdapter(getSupportFragmentManager());
        adapter.addFragment(BigImageFragment.newInstance(url));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPostPressed(String url) {
        //BigImageFragment bigImageFragment = new BigImageFragment();
        //bigImageFragment.setBigImage(url);
        inicializeBigImageAdapter(url);
    }
}
