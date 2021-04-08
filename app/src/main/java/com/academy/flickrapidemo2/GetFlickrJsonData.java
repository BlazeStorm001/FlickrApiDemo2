package com.academy.flickrapidemo2;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    void executeOnSameThread(String searchCriteria) {
        Log.d(TAG, "executeOnSameThread: start");
        GetRawData downloadJson = new GetRawData(this);
        String finalUrl = createUri(searchCriteria,mLanguage,mMatchAll);
        Log.d(TAG, "executeOnSameThread: finalurl :" + finalUrl);
        downloadJson.execute(finalUrl);
        Log.d(TAG, "executeOnSameThread: end");
    }

    String createUri(String searchCriteria,String lang,boolean match) {
        Log.d(TAG, "createUri: start");
        return Uri.parse(mBaseUrl).buildUpon()
                .appendQueryParameter("tags",searchCriteria)
                .appendQueryParameter("tagmode",match?"ALL":"ANY")
                .appendQueryParameter("lang",lang)
                .appendQueryParameter("format","json")
                .appendQueryParameter("nojsoncallback","1")
                .build().toString();
    }
    @Override
    public void onDownloadComplete(String data, DownloadStatus downloadStatus) {
        if(downloadStatus == DownloadStatus.OK) {
            mPhotoList = new ArrayList<Photo>();
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("items");

                for(int i=0; i < jsonArray.length(); i++) {
                    JSONObject photo = jsonArray.getJSONObject(i);
                    String title = photo.getString("title");
                    String link = photo.getString("link");

                    String media = photo.getJSONObject("media").getString("m");
                    String author = photo.getString("author");
                    String authorId = photo.getString("author_id");
                    String tags = photo.getString("tags");
                    Photo photoObj = new Photo(title,author,authorId,tags
                    ,media,link);
                    mPhotoList.add(photoObj);
                    Log.d(TAG, "onDownloadComplete: photo object " + i + "\n " + photoObj.toString());
                }
            }
            catch (JSONException je) {
                Log.d(TAG, "onDownloadComplete: caught exception" + je.toString());
            }
        }
        else {
            Log.e(TAG, "onDownloadComplete: Error = " + downloadStatus);
        }
        Log.d(TAG, "onDownloadComplete: end");
    }
}
