package com.academy.flickrapidemo2;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class GetFlickrJsonData extends AsyncTask<String,Void,List<Photo>> implements GetRawData.OnDownloadComplete {
    private static final String TAG = "GetFlickrJsonData";
    private List<Photo> mPhotoList = null;
    private String mLanguage;
    private String mBaseUrl;
    private final OnDataAvailable mCallback;
    private boolean mMatchAll;
    private boolean runningOnSameThread = false;

    @Override
    protected void onPostExecute(List<Photo> photos) {
        super.onPostExecute(photos);
        if(mCallback != null) {
            mCallback.onDataAvailable(mPhotoList,DownloadStatus.OK);
        }
    }

    @Override
    protected List<Photo> doInBackground(String... strings) {
        String finalUrl = createUri(strings[0],mLanguage,mMatchAll);
        GetRawData downloadJson = new GetRawData(this);
        Log.d(TAG, " finalurl :" + finalUrl);
        //call getRawData on same thread
        downloadJson.runInSameThread(finalUrl);
        Log.d(TAG, "doInBackground: finish");
        return mPhotoList;
    }

    public GetFlickrJsonData(String mLanguage, String mBaseUrl, OnDataAvailable mCallback, boolean mMatchAll) {
        this.mLanguage = mLanguage;
        this.mBaseUrl = mBaseUrl;
        this.mCallback = mCallback;
        this.mMatchAll = mMatchAll;
        
    }

    //defines callback method to be implemented once the raw json has been parsed into photo objects
    //passes the photo list
    interface OnDataAvailable {
        void onDataAvailable(List<Photo> data, DownloadStatus status);
    }

    //it is called in mainactivity and takes the search keyword and creates the url to
    //be passed to the GetRawData
    void executeOnSameThread(String searchCriteria) {
        Log.d(TAG, "executeOnSameThread: start");

        Log.d(TAG, "executeOnSameThread: end");
    }

    //creates the final uri(unified resource identifier) by appending all the required
    //parameters
    String createUri(String searchCriteria,String lang,boolean match) {
        Log.d(TAG, "createUri: start");
//        Uri uri = Uri.parse(mBaseUrl);
//        Uri.Builder builder = uri.buildUpon();
//        builder = builder.appendQueryParameter("tags",searchCriteria);
//        builder = builder.appendQueryParameter("tagmode",match?"ALL":"ANY");
//        builder = builder.appendQueryParameter("lang",lang);
//        builder = builder.appendQueryParameter("format","json");
//        builder = builder.appendQueryParameter("nojsoncallback","1");
//        uri = builder.build();
//        String string = uri.toString();
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
        Log.d(TAG, "onDownloadComplete: starts");
        if(downloadStatus == DownloadStatus.OK) {
            mPhotoList = new ArrayList<Photo>();
            try {
                downloadStatus = DownloadStatus.PROCESSING;
                //whole jsonObject
                JSONObject jsonObject = new JSONObject(data);
                //get jsonarray from object
                JSONArray jsonArray = jsonObject.getJSONArray("items");

                //get each object in array and get the value fields
                for(int i=0; i < jsonArray.length(); i++) {
                    JSONObject photo = jsonArray.getJSONObject(i);
                    String title = photo.getString("title");

                    String media = photo.getJSONObject("media").getString("m");
                    //replaces the first _m found in string with _b
                    //to get the url of the bigger version of image
                    String link = media.replaceFirst("_m","_b");
                    String author = photo.getString("author");
                    String authorId = photo.getString("author_id");
                    String tags = photo.getString("tags");
                    //create and add the photo to the arraylist
                    Photo photoObj = new Photo(title,author,authorId,tags
                            ,media,link);
                    mPhotoList.add(photoObj);
                    Log.d(TAG, "onDownloadComplete: photo object " + i + "\n " + photoObj.toString());
                }
                downloadStatus = DownloadStatus.OK;
            }
            catch (JSONException je) {
                Log.d(TAG, "onDownloadComplete: caught exception" + je.toString());
                downloadStatus = DownloadStatus.FAILED_OR_EMPTY;
            }
        }
        else {
            downloadStatus = DownloadStatus.FAILED_OR_EMPTY;
            Log.e(TAG, "onDownloadComplete: Error = " + downloadStatus);
        }
        if(mCallback != null && runningOnSameThread) {
            mCallback.onDataAvailable(mPhotoList,downloadStatus);
        }
        else {
            Log.d(TAG, "onDownloadComplete: Callback is null");
        }
        Log.d(TAG, "onDownloadComplete: end");
    }
}
