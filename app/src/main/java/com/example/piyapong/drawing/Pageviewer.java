package com.example.piyapong.drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by Piyapong on 19/02/2017.
 */
public class Pageviewer extends ViewPager{

    public Pageviewer(Context context) {
        super(context);
        setMyScroller();
    }

    public Pageviewer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMyScroller();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        if(event.getToolType(0) == MotionEvent.TOOL_TYPE_FINGER)
        {
            return super.onInterceptTouchEvent(event);
        }
        else if(event.getToolType(0) == MotionEvent.TOOL_TYPE_STYLUS)
        {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages

        if(event.getToolType(0) == MotionEvent.TOOL_TYPE_FINGER)
        {
            return super.onTouchEvent(event);
        }
        else if(event.getToolType(0) == MotionEvent.TOOL_TYPE_STYLUS)
        {
            return false;
        }
        return super.onTouchEvent(event);
    }

    //down one is added for smooth scrolling

    private void setMyScroller() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this, new MyScroller(getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MyScroller extends Scroller {
        public MyScroller(Context context) {
            super(context, new DecelerateInterpolator());
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, 350 /*1 secs*/);
        }
    }
}
