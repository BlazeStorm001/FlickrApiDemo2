package com.academy.flickrapidemo2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements GetFlickrJsonData.OnDataAvailable,
        RecyclerViewItemClickListener.OnRecyclerClickListener {
    private static final String TAG = "MainActivity";
    private String feedUrl = "https://www.flickr.com/services/feeds/photos_public.gne";
    private RecyclerView recyclerView;
    private FlickrRecyclerViewAdapter flickrRecyclerViewAdapter;
    private GetFlickrJsonData getFlickrJsonData;
    static final String PHOTO_TRANSFER = "PHOTO_TRANSFER";

    @Override
    public void onDataAvailable(List<Photo> data, DownloadStatus status) {
        if (status == DownloadStatus.OK) {
            Log.d(TAG, "onDataAvailable: photo processed");
            flickrRecyclerViewAdapter.loadNewData(data);
        } else {
            Log.d(TAG, "onDataAvailable: failed in processing");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateToolbar(false);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        recyclerView = findViewById(R.id.recycler_view);
        //create a new adapter
        flickrRecyclerViewAdapter =
                new FlickrRecyclerViewAdapter(new ArrayList<Photo>(), this);
        //set the layout manager it is necessary in recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //set adapter
        recyclerView.setAdapter(flickrRecyclerViewAdapter);
        RecyclerViewItemClickListener recyclerViewItemClickListener = new
                RecyclerViewItemClickListener(this,recyclerView,this);
        recyclerView.addOnItemTouchListener(recyclerViewItemClickListener);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: start");
        super.onResume();
        getFlickrJsonData = new GetFlickrJsonData(
                "en", feedUrl, this, true);
        getFlickrJsonData.execute("android");
        Log.d(TAG, "onResume: end");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d(TAG, "onCreateOptionsMenu() returned: " + true);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        Log.d(TAG, "onOptionsItemSelected: returned");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this,PhotoDetailActivity.class);
        intent.putExtra(PHOTO_TRANSFER,flickrRecyclerViewAdapter.getPhoto(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
