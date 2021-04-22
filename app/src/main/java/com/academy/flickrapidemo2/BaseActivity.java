package com.academy.flickrapidemo2;

import android.app.ActionBar;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    static final String FLICKR_QUERY = "FLICKR_QUERY";
    static final String PHOTO_TRANSFER = "PHOTO_TRANSFER";

    void activateToolbar(boolean enableHome) {
        Log.d(TAG, "activateToolbar: start");
        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            actionBar = getActionBar();
        }
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(enableHome);
        }
    }
}
