package com.academy.flickrapidemo2;

import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
//activity to enable the toolbar
public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    static final String FLICKR_QUERY = "FLICKR_QUERY";
    static final String PHOTO_TRANSFER = "PHOTO_TRANSFER";
    Toolbar toolbar;
    void activateToolbar(boolean enableHome) {
        Log.d(TAG, "activateToolbar: start");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar == null) {
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            actionBar = getSupportActionBar();
        }
        if(actionBar != null) {
            Log.d(TAG, "activateToolbar: enable home");
            actionBar.setDisplayHomeAsUpEnabled(enableHome);
        }
        else {
            Log.d(TAG, "activateToolbar: home disabled");
        }
    }
}
