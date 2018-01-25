package com.example.piyapong.drawing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import thumbnail.SaveThumbnail;

/**
 * Created by Piyapong on 19/02/2017.
 */
public class Handdrawingview extends View{

    public static final float PEN_STROKE_WIDTH = 4f;
    public static final float STROKE_WIDTH = 4f;
    public static final float PAINT_STROKE_WIDTH = 35f;

    //public static final float PEN_STROKE_WIDTH = 4f;
    //public static final float PAINT_STROKE_WIDTH = 35f;

    /** Need to track this so the dirty region can accommodate the stroke. **/
    //private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
    private Paint paint = new Paint();
    //private Path path = new Path();
    private ArrayList previouspath;
    private int initprevioussize;
    /**
     * Optimizes painting by invalidating the smallest possible area.
     */
    private float lastTouchX;
    private float lastTouchY;
    private final RectF dirtyRect = new RectF();
    Paintdrawingview paintview;
    Context context;
    ScaleGestureDetector scale;
    Pageviewer mViewPager;
    public Handdrawingview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(PEN_STROKE_WIDTH);

        //Zoom image
        scale = new ScaleGestureDetector(this.getContext(), new Zoomimage(context));

    }

    /**
     * Erases the signature.
     */
/*
    public void setPaintview(Paintdrawingview paintview)
    {
        this.paintview = paintview;
    }
    public void setPath(ArrayList previouspath)
    {
        this.previouspath = previouspath;
    }
    public void clear() {
        path.reset();
        // Repaints the entire view.
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if(previouspath!=null)
        {
            for(int i=0;i<previouspath.size();i++)
            {
                canvas.drawPath(((Mypath)previouspath.get(i)).getPath(), ((Mypath)previouspath.get(i)).getPaint());
            }
        }
        //canvas.drawPath(path, paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getToolType(0) == MotionEvent.TOOL_TYPE_STYLUS) {
            float eventX = event.getX();
            float eventY = event.getY();
            Path mypath = this.path;
            if(Variable.CURRENTTOOL==Variable.PEN)
            {
                mypath = this.path;
            }
            else if (Variable.CURRENTTOOL==Variable.PAINT)
            {
                mypath = paintview.getPath();
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //paint.setStrokeWidth(STROKE_WIDTH);
                    paint.setColor(ContextCompat.getColor(context, Variable.CURRENTCOLOR));
                    mypath.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    // There is no end point yet, so don't waste cycles invalidating.
                    return true;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    if(Variable.CURRENTTOOL==Variable.PEN)
                    {
                        // Start tracking the dirty region.
                        resetDirtyRect(eventX, eventY);
                    }
                    else if (Variable.CURRENTTOOL==Variable.PAINT)
                    {
                        resetDirtyRect(eventX, lastTouchY);
                    }

                    // When the hardware tracks events faster than they are delivered, the
                    // event will contain a history of those skipped points.
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);;
                        expandDirtyRect(historicalX, historicalY);
                        if(Variable.CURRENTTOOL==Variable.PEN)
                        {
                            mypath.lineTo(historicalX, historicalY);
                        }
                        else if (Variable.CURRENTTOOL==Variable.PAINT)
                        {
                            mypath.lineTo(historicalX, lastTouchY);
                        }

                    }
                    if(Variable.CURRENTTOOL==Variable.PEN)
                    {
                        // After replaying history, connect the line to the touch point.
                        mypath.lineTo(eventX, eventY);

                        Paint mypaint = new Paint();
                        mypaint.setAntiAlias(true);
                        int min = 0;
                        int max = 80;
                        Random rnd = new Random();
                        mypaint.setColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                        mypaint.setStyle(paint.getStyle());
                        mypaint.setStrokeJoin(paint.getStrokeJoin());
                        mypaint.setStrokeCap(paint.getStrokeCap());
                        mypaint.setStrokeWidth(paint.getStrokeWidth());
                        Mypath m = new Mypath(mypath,paint);
                        //m.setPath(mypath);
                        //m.setPaint(paint);
                        previouspath.add(m);
                    }
                    else if (Variable.CURRENTTOOL==Variable.PAINT)
                    {
                        mypath.lineTo(eventX, lastTouchY);
                    }

                    break;
                default:
                    return false;
            }
            // Include half the stroke width to avoid clipping.
            if(Variable.CURRENTTOOL==Variable.PEN)
            {
                //paint.setColor(ContextCompat.getColor(context, Variable.CURRENTCOLOR));
                invalidate(
                        (int) (dirtyRect.left - (STROKE_WIDTH / 2)),
                        (int) (dirtyRect.top - (STROKE_WIDTH / 2)),
                        (int) (dirtyRect.right + (STROKE_WIDTH / 2)),
                        (int) (dirtyRect.bottom + (STROKE_WIDTH / 2)));
            }
            else if (Variable.CURRENTTOOL==Variable.PAINT)
            {
                paintview.drawPaint(dirtyRect);
            }

            lastTouchX = eventX;

            if(Variable.CURRENTTOOL==Variable.PEN)
            {
                lastTouchY = eventY;
            }
            else if (Variable.CURRENTTOOL==Variable.PAINT)
            {
                //lastTouchY is the same
            }
        }
        return true;
    }


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

    private void resetDirtyRect(float eventX, float eventY) {

        dirtyRect.left = Math.min(lastTouchX, eventX);
        dirtyRect.right = Math.max(lastTouchX, eventX);
        dirtyRect.top = Math.min(lastTouchY, eventY);
        dirtyRect.bottom = Math.max(lastTouchY, eventY);
    }


*/






























    //private Bitmap bitmap;
    //private Canvas canvas;
    private Path path = new Path();
    private Paint bitmapPaint = new Paint(Paint.DITHER_FLAG);

    //private Paint paint;

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

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
    public void setPageviewer(Pageviewer mViewPager)
    {
        this.mViewPager = mViewPager;
    }
    @Override
    protected void onDraw(Canvas canvas) {


            if(Variable.bitmap!=null) {
                if(Variable.CURRENTTOOL==Variable.PEN && !selectchoice)
                {
                    //canvas.drawBitmap(Variable.bitmap, 0, 0, bitmapPaint);
                    canvas.drawPath(path,paint);
                }
        }
        if(previouspath!=null) {
            Variable.bitmap.eraseColor(Color.TRANSPARENT);
            for (int i = 0; i < previouspath.size(); i++) {
                if(((Mypath) previouspath.get(i)).getVisibility()) {
                    canvas.drawPath(((Mypath) previouspath.get(i)).getPath(), ((Mypath) previouspath.get(i)).getPaint());
                }
            }
        }
        //else if (Variable.CURRENTTOOL==Variable.PAINT)
        {
            //paintview.getCanvas().drawBitmap(bitmap, 0, 0, bitmapPaint);
            //paintview.getCanvas().drawPath(mypath, paint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //zoom image
        scale.onTouchEvent(event);

        //select choice
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            float x = event.getX();
            float y = event.getY();
            selectchoice(x,y);
            //new SaveThumbnail(mViewPager).execute();
        }

        if(event.getToolType(0) == MotionEvent.TOOL_TYPE_STYLUS && !selectchoice) {
            float x = event.getX();
            float y = event.getY();
            paint.setColor(ContextCompat.getColor(context, Variable.CURRENTCOLOR));
            //Path mypath = this.path;
            if(Variable.CURRENTTOOL==Variable.PEN)
            {
                //mypath = this.path;
                paint.setStrokeJoin(Paint.Join.ROUND);
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setStrokeWidth(PEN_STROKE_WIDTH);
            }
            else if (Variable.CURRENTTOOL==Variable.PAINT)
            {
                //mypath = paintview.getPath();
                paint.setStrokeJoin(Paint.Join.MITER);
                paint.setStrokeCap(Paint.Cap.SQUARE);
                paint.setStrokeWidth(PAINT_STROKE_WIDTH);
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchStart(path, x, y);
                    if(Variable.CURRENTTOOL==Variable.PEN)
                    {
                        invalidate();
                    }
                    else if(Variable.CURRENTTOOL==Variable.PAINT)
                    {
                        if(paintview!=null) {
                            paintview.drawPath(path, paint);
                        }
                    }
                    break;
                case MotionEvent.ACTION_MOVE:

                    if(Variable.CURRENTTOOL==Variable.PEN)
                    {
                        touchMove(path, x, y);
                    }
                    else if (Variable.CURRENTTOOL==Variable.PAINT)
                    {
                        touchMove(path, x, mY);
                    }
                    else if (Variable.CURRENTTOOL==Variable.EREASER)
                    {
                        touchMoveeraser(x,y);
                    }

                    if(Variable.CURRENTTOOL==Variable.PEN || Variable.CURRENTTOOL==Variable.EREASER)
                    {
                        invalidate();
                    }
                    else if(Variable.CURRENTTOOL==Variable.PAINT)
                    {
                        if(paintview!=null) {
                            paintview.drawPath(path, paint);
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    touchUp(path);
                    if(Variable.CURRENTTOOL==Variable.PEN)
                    {
                        invalidate();
                    }
                    else if(Variable.CURRENTTOOL==Variable.PAINT)
                    {
                        if(paintview!=null) {
                            paintview.drawPath(path, paint);
                        }
                    }
                    break;
            }
        }
        return true;
    }
    public void setPaintview(Paintdrawingview paintview)
    {
        this.paintview = paintview;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //Variable.bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        //Variable.canvas = new Canvas(Variable.bitmap);
    }




    private void touchStart(Path path, float x, float y) {
        path.reset();
        path.moveTo(x, y);
        mX = x;
        mY = y;

    }

    private void touchMove(Path path, float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }
    private void touchMoveeraser(float x, float y)
    {
        for (int i=0;i<previouspath.size();i++) {
            Mypath p = (Mypath) previouspath.get(i);
            RectF pBounds = new RectF();
            p.getPath().computeBounds(pBounds, true);
            if (pBounds.contains(x, y)) {
                p.setInvisible();
            }
        }
        if(paintview!=null)
        {
            paintview.erasePath(x,y);
        }

    }

    private void touchUp(Path path) {
        path.lineTo(mX, mY);

        Path finalpath = new Path(path);
        // kill this so we don't double draw
        path.reset();
        // commit the path to our offscreen
        Variable.canvas.drawPath(finalpath, paint);

        Mypath m = new Mypath(new Path(finalpath),new Paint(paint));

        if(Variable.CURRENTTOOL==Variable.PEN)
        {
            previouspath.add(m);
        }
        else if (Variable.CURRENTTOOL==Variable.PAINT)
        {
            if(paintview!=null) {
                paintview.addPath(m);
            }
        }
        //MainActivity.saveThumbnail();
    }
    ArrayList choices;
    boolean selectchoice = false;
    public void setChoices(ArrayList choices)
    {
        this.choices = choices;
    }
    private void selectchoice(float x, float y)
    {
        selectchoice = false;
        for(int i=0;i<choices.size();i++)
        {
            TextView c = (TextView)choices.get(i);
            int[] xy = new int[2];
            c.getLocationOnScreen(xy);
            int[] mainxy = new int[2];
            this.getLocationOnScreen(mainxy);
            RectF r = new RectF(xy[0]-mainxy[0],xy[1]-mainxy[1],xy[0]+c.getWidth()-mainxy[0],xy[1]+c.getHeight()-mainxy[1]);
            if(r.contains(x,y))
            {
                c.setBackgroundResource(R.drawable.select);
                c.invalidate();
                selectchoice = true;
                for(int j=0;j<choices.size();j++)
                {
                    TextView cc = (TextView)choices.get(j);
                    if(j!=i)
                    {
                        cc.setBackgroundResource(R.drawable.unselect);
                        cc.invalidate();
                    }
                }
                //MainActivity.saveThumbnail();
                break;
            }

        }

    }


/*
    public Bitmap getBitmap() {
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);

        return bmp;
    }
/*
    public void setBitmap(Bitmap bitmap, Canvas canvas)
    {
        this.bitmap = bitmap;
        this.canvas = canvas;
    }
*/
/*
    //Clear screen
    public void clear() {
        Variable.bitmap.eraseColor(Color.WHITE);
        invalidate();
        System.gc();

    }

    public void setPathColor(int color) {
        paint.setColor(color);
    }
*/
}
