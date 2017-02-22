package com.example.piyapong.drawing;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;

import java.util.ArrayList;

/**
 * Created by Piyapong on 19/02/2017.
 */
public class Variable {



    public static Bitmap bitmap = Bitmap.createBitmap(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels, Bitmap.Config.ARGB_8888);
    public static Canvas canvas = new Canvas(bitmap);
    public static int TOTALPAGE = 200;
    public  static ArrayList[] HANDDRAWINGPATH = new ArrayList[TOTALPAGE];
    public  static ArrayList[] HIGHLIGHTPATH = new ArrayList[TOTALPAGE];

    //Thumbnail
    public static Bitmap[] THUMBNAIL = new Bitmap[TOTALPAGE];

    //Drawing tool
    public static final int EREASER = 0;
    public static final int PEN = 1;
    public static final int PAINT = 2;
    public static int CURRENTTOOLID = R.id.pen_red;
    /*
    public static final int PAINT_GREEN = 1;
    public static final int PAINT_BULE = 2;
    public static final int PAINT_RED = 3;
    public static final int PEN_GREEN = 4;
    public static final int PEN_BULE = 5;
    public static final int PEN_RED = 6;
    public static int CURRENTTOOL = PEN_RED;
    */
    public static int CURRENTTOOL = PEN;
    public static int CURRENTCOLOR = R.color.colorRed;
    //html template
    public static String htmlquestiontemplate =  "<html>" +
            "<head>" +
            "<style>" +
            "body {text-align: justify;font-size:22px;line-height: 150%;}" +
            "sup   {font-size:10px; vertical-align: super; line-height: 100%}" +
            "sub   {font-size:10px; vertical-align: sub; line-height: 100%}" +
            "</style>\n" +
            "</head>\n" +
            "<body>%s</body></html>";
    public static String htmlchoicetemplate =  "<html>" +
            "<head>" +
            "<style>" +
            "body {text-align: justify;font-size:18px; padding-top:0px; margin-top:5px;}" +
            "sup   {font-size:10px; vertical-align: super; line-height: 100%}" +
            "sub   {font-size:10px; vertical-align: sub; line-height: 100%}" +
            "</style>\n" +
            "</head>\n" +
            "<body>%s</body></html>";
}
