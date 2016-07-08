package com.example.velocitytrackerdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * Created by Administrator on 2015/10/14.
 */
public class VelocityView extends View{
    private VelocityTracker mTracker;
    private OnTrackerListener mListener;
    public VelocityView(Context context){
        super(context);
    }

    public VelocityView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public interface OnTrackerListener{
        void getXSpeed(int speed);
        void getYSpeed(int speed);
    }

    public void setTrackerListener(OnTrackerListener listener){
        mListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        getTracker(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(mTracker==null){
                    mTracker = VelocityTracker.obtain();
                }else{
                    mTracker.clear();
                }
                mTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mTracker.addMovement(event);
                mTracker.computeCurrentVelocity(1000);
                getSpeed();
                break;
            case MotionEvent.ACTION_UP:
                cancelTracker();
                break;
        }
        return true;
    }

    private void cancelTracker(){
        if(mTracker!=null){
            mTracker.recycle();
            mTracker = null;
        }
    }

    private void getSpeed(){
        mTracker.computeCurrentVelocity(1000);
        int xSpeed = (int)Math.abs(mTracker.getXVelocity());
        int ySpeed = (int)Math.abs(mTracker.getYVelocity());
        if(mListener!=null){
            mListener.getXSpeed(xSpeed);
            mListener.getYSpeed(ySpeed);
        }
    }

    private void getTracker(MotionEvent event){
        if(mTracker==null){
            mTracker = VelocityTracker.obtain();
            mTracker.addMovement(event);
        }
    }
}
