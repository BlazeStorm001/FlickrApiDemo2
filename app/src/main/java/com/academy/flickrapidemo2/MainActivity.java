package com.academy.flickrapidemo2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GetFlickrJsonData.OnDataAvailable {
    private static final String TAG = "MainActivity";
    private String feedUrl = "https://www.flickr.com/services/feeds/photos_public.gne";

    @Override
    public void onDataAvailable(List<Photo> data, DownloadStatus status) {
        if(status == DownloadStatus.OK) {
            Log.d(TAG, "onDataAvailable: photo processed");
        }
        else {
            Log.d(TAG, "onDataAvailable: failed in processing");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        GetFlickrJsonData getFlickrJsonData = new GetFlickrJsonData(
                "en",feedUrl,this,true);
        getFlickrJsonData.executeOnSameThread("android");
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


}
