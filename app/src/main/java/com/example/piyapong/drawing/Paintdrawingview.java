package com.example.piyapong.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Piyapong on 19/02/2017.
 */
public class Paintdrawingview extends View{

    //public static final float PEN_STROKE_WIDTH = 4f;
    //public static final float PAINT_STROKE_WIDTH = 20f;

    private float STROKE_WIDTH = 35f;
    /** Need to track this so the dirty region can accommodate the stroke. **/
    //private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
    private Paint paint = new Paint();
    private Path mypath = new Path();
    private Paint bitmapPaint = new Paint(Paint.DITHER_FLAG);
    private ArrayList previouspath;
    private int initprevioussize;
    /**
     * Optimizes painting by invalidating the smallest possible area.
     */
    //private float lastTouchX;
    //private float lastTouchY;
    //private final RectF dirtyRect = new RectF();

    public Paintdrawingview(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeWidth(STROKE_WIDTH);
    }
    public Path getPath()
    {
        return  mypath;
    }
    public void addPath(Mypath path)
    {
        previouspath.add(path);
    }
    public void drawPath(Path path, Paint paint)
    {
        this.paint = paint;
        this.mypath = path;
        invalidate();
    }
    public void erasePath(float x, float y)
    {
        if(previouspath!=null)
        {
            for (int i=0;i<previouspath.size();i++) {
                Mypath p = (Mypath) previouspath.get(i);
                RectF pBounds = new RectF();
                p.getPath().computeBounds(pBounds, true);
                //add width of highlight area
                pBounds.set(pBounds.left,pBounds.top-23,pBounds.right,pBounds.bottom+23);
                if (pBounds.contains(x, y)) {
                    p.setInvisible();
                }
            }
        }
        invalidate();
    }
    public void setPath(ArrayList previouspath)
    {
        //mypath.reset();
        Variable.bitmap.eraseColor(Color.TRANSPARENT);

        if(previouspath!=null)
        {
            //Variable.canvas.drawBitmap(Variable.bitmap,0,0,bitmapPaint);
            initprevioussize = previouspath.size();
            //for(int i=0;i<previouspath.size();i++)
            {
                //Variable.canvas.drawPath(((Mypath)previouspath.get(i)).getPath(), ((Mypath)previouspath.get(i)).getPaint());
                //initpreviouspath.add(previouspath.get(i));
                //mypath.addPath(((Mypath)previouspath.get(i)).getPath());
                //this.paint = ((Mypath)previouspath.get(i)).getPaint();
                //mypath.reset();

            }
        }
        this.previouspath = previouspath;
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
            //canvas.drawPath(path, paint);
        //if(Variable.CURRENTTOOL==Variable.PEN) {
            if(Variable.bitmap!=null) {
                canvas.drawBitmap(Variable.bitmap, 0, 0, bitmapPaint);
                canvas.drawPath(mypath,paint);
                //canvas.drawPath(mypath, paint);
                if(previouspath!=null) {
                    for (int i = 0; i < previouspath.size(); i++) {
                        if(((Mypath) previouspath.get(i)).getVisibility()) {
                            canvas.drawPath(((Mypath) previouspath.get(i)).getPath(), ((Mypath) previouspath.get(i)).getPaint());
                        }
                    }
                }

            }
        //}
    }
    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getToolType(0) == MotionEvent.TOOL_TYPE_STYLUS &&(Variable.CURRENTTOOL==Variable.PAINT_BULE || Variable.CURRENTTOOL==Variable.PAINT_GREEN || Variable.CURRENTTOOL==Variable.PAINT_RED)) {
            float eventX = event.getX();
            float eventY = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //paint.setStrokeWidth(STROKE_WIDTH);
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    // There is no end point yet, so don't waste cycles invalidating.
                    return true;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    // Start tracking the dirty region.
                    resetDirtyRect(eventX, eventY);
                    // When the hardware tracks events faster than they are delivered, the
                    // event will contain a history of those skipped points.
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);;
                        expandDirtyRect(historicalX, historicalY);
                        path.lineTo(historicalX, historicalY);
                    }
                    // After replaying history, connect the line to the touch point.
                    path.lineTo(eventX, eventY);
                    break;
                default:
                    return false;
            }
            // Include half the stroke width to avoid clipping.

            invalidate(
                    (int) (dirtyRect.left - (STROKE_WIDTH / 2)),
                    (int) (dirtyRect.top - (STROKE_WIDTH / 2)),
                    (int) (dirtyRect.right + (STROKE_WIDTH / 2)),
                    (int) (dirtyRect.bottom + (STROKE_WIDTH / 2)));
            lastTouchX = eventX;
            lastTouchY = eventY;
        }
        return true;
    }
    /**
     * Called when replaying history to ensure the dirty region includes all
     * points.
     */
    /*
    private void expandDirtyRect(float historicalX, float historicalY) {
        if (historicalX < dirtyRect.left) {
            dirtyRect.left = historicalX;
        } else if (historicalX > dirtyRect.right) {
            dirtyRect.right = historicalX;
        }
        if (historicalY < dirtyRect.top) {
            dirtyRect.top = historicalY;
        } else if (historicalY > dirtyRect.bottom) {
            dirtyRect.bottom = historicalY;
        }
    }
    /**
     * Resets the dirty region when the motion event occurs.
     */
    /*
    private void resetDirtyRect(float eventX, float eventY) {
        // The lastTouchX and lastTouchY were set when the ACTION_DOWN
        // motion event occurred.
        dirtyRect.left = Math.min(lastTouchX, eventX);
        dirtyRect.right = Math.max(lastTouchX, eventX);
        dirtyRect.top = Math.min(lastTouchY, eventY);
        dirtyRect.bottom = Math.max(lastTouchY, eventY);
    }
    */
}
