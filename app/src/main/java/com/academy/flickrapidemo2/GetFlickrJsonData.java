package com.academy.flickrapidemo2;

import android.util.Log;

import java.util.List;

class GetFlickrJsonData implements GetRawData.OnDownloadComplete {
    private static final String TAG = "GetFlickrJsonData";
    private List<Photo> mPhotoList = null;
    private String mLanguage;
    private String mBaseUrl;
    private
    public GetFlickrJsonData() {
        super();
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
