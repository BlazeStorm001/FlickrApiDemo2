package com.academy.flickrapidemo2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//enum for storing the status of download
enum DownloadStatus{IDLE,PROCESSING,NOT_INITIALIZED,FAILED_OR_EMPTY,OK};

public class GetRawData extends AsyncTask<String,Void,String> {
    private static final String TAG = "DownloadJson";
    private DownloadStatus downloadStatus;
    //stores the instance of the MainActivity object which implements OnDownloadComplete
    //whose method onDownloadComplete will be called
    //in OnPostExecute
    //an interface acts as a contract and makes sure the method onDownloadComplete is always present
    // in the class
    private final OnDownloadComplete mCallback;
    interface OnDownloadComplete {
        void onDownloadComplete(String s,DownloadStatus downloadStatus);
    }
    public GetRawData(OnDownloadComplete mCallback) {
        this.downloadStatus = DownloadStatus.IDLE;
        this.mCallback = mCallback;
    }
    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: parameter = " + s);
        if(mCallback != null) {
            mCallback.onDownloadComplete(s,downloadStatus);
        }
        Log.d(TAG, "onPostExecute: finished");
    }

    @Override
    protected String doInBackground(String... strings) {
        if(strings[0] == null) {
            downloadStatus = DownloadStatus.NOT_INITIALIZED;
        }
        String jsonFeed;
        jsonFeed = downloadJson(strings[0]);
        if(jsonFeed == null) {
            downloadStatus = DownloadStatus.FAILED_OR_EMPTY;
        }
        Log.d(TAG, "doInBackground: " + jsonFeed);
        return jsonFeed;
    }

    private String downloadJson(String urlPath) {
        StringBuilder jsonResult = new StringBuilder();
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            connection = (HttpURLConnection) url.openConnection();
            int response = connection.getResponseCode();

            Log.d(TAG, "downloadXML: The response code was " + response);

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            //null is written first to emphasize its importance
            while (null != (line = reader.readLine()) ) {
                jsonResult.append(line).append("\n");
            }
            downloadStatus = DownloadStatus.OK;
            return jsonResult.toString();
        } catch (MalformedURLException e) {
            Log.e(TAG, "downloadXML: Invalid URL " + e.getMessage());
        } catch (SecurityException e) {
            Log.d(TAG, "downloadXML: Needs internet permission");
        } catch (IOException e) {
            Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
        }
        finally {
            if(connection != null) {
                connection.disconnect();
            }
            try{
                if(reader != null) {
                    reader.close();
                }
            }
          catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
