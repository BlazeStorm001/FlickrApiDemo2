package com.academy.flickrapidemo2;

import android.util.Log;

import java.util.List;

class GetFlickrJsonData implements GetRawData.OnDownloadComplete {
    private static final String TAG = "GetFlickrJsonData";
    private List<Photo> mPhotoList = null;
    private String mLanguage;
    private String mBaseUrl;
    private final OnDataAvailable mCallback;
    private boolean mMatchAll;

    public GetFlickrJsonData(String mLanguage, String mBaseUrl, OnDataAvailable mCallback, boolean mMatchAll) {
        this.mLanguage = mLanguage;
        this.mBaseUrl = mBaseUrl;
        this.mCallback = mCallback;
        this.mMatchAll = mMatchAll;
    }

    interface OnDataAvailable {
        void onDataAvailable(List<Photo> data, DownloadStatus status);
    }

    @Override
    public void onDownloadComplete(String data, DownloadStatus downloadStatus) {
        if(downloadStatus == DownloadStatus.OK) {
            Log.d(TAG, "onDownloadComplete: data = " + data);
        }
        else {
            Log.e(TAG, "onDownloadComplete: Error = " + downloadStatus);
        }

    }
}
