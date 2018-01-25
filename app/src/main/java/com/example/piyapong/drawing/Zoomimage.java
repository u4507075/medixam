package com.example.piyapong.drawing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.ScaleGestureDetector;

/**
 * Created by Garena on 23/5/2560.
 */

public class Zoomimage extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    Context context;
    Imageview v = new Imageview();
    public Zoomimage(Context context)
    {
        this.context = context;
    }
    @Override
    public boolean onScale(ScaleGestureDetector detector) {

        if(v!=null &&  v.getDialog()!=null && v.getDialog().isShowing()) {
            //dialog is showing so do something
        } else {
            //dialog is not showing
            v.show(((Activity) context).getFragmentManager(), "Image overview");
        }
        return true;
    }
}
