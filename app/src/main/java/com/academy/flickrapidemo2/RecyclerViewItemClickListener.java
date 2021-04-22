package com.academy.flickrapidemo2;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewItemClickListener extends  RecyclerView.SimpleOnItemTouchListener {
    interface OnRecyclerClickListener {
        void onItemClick(View v,int position);
        void onItemLongClick(View view, int position);
    }

    private static final String TAG = "RecyclerViewItemClickLi";
    private final GestureDetectorCompat gestureDetector;
    private final OnRecyclerClickListener mListener;
    public RecyclerViewItemClickListener(Context context,final RecyclerView recyclerView, OnRecyclerClickListener listener) {
        super();
        this.mListener = listener;
        gestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
            //return value in each of these methods will be the value returned by
            //onInterceptTouchEvent
            //return true if event has been handled
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.d(TAG, "onSingleTapConfirmed: called");
                //gets the view on which click was made using coordinates passed through
                // MotionEvent object
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView != null && mListener != null) {
                    Log.d(TAG, "onSingleTapConfirmed: callback onItemClick");
                    //getting the position of the view in recycler view by passing it to
                    //getChildAdapterPosition
                    mListener.onItemClick(childView,recyclerView.getChildAdapterPosition(childView));
                }
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public boolean onDown(MotionEvent e) {
                Log.d(TAG, "onDown: called");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Log.d(TAG, "onShowPress: called");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.d(TAG, "onSingleTapUp: called");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.d(TAG, "onScroll: called");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.d(TAG, "onLongPress: called");
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView != null && mListener != null) {
                    Log.d(TAG, "onSingleTapConfirmed: callback onItemClick");
                    mListener.onItemLongClick(childView,recyclerView.getChildAdapterPosition(childView));
                }
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d(TAG, "onFling: called");
                return false;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        Log.d(TAG, "onInterceptTouchEvent: starts");
        //if we have handled all the touch events then return true
        //which implies that recycler view will not scroll
        //many touch events are created so it is better to let the system handle them
        //and just return false

        if(gestureDetector != null) {
            boolean result = gestureDetector.onTouchEvent(e);
            Log.d(TAG, "onInterceptTouchEvent: result " + result);
            return result;
        }
        Log.d(TAG, "onInterceptTouchEvent: returned false");
        return false;
    }
}
