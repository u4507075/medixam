package com.example.piyapong.drawing;

/**
 * Created by Piyapong on 19/02/2017.
 */
public class Variable {

    //Drawing tool
    public static final int EREASER = 0;
    public static final int PAINT_GREEN = 1;
    public static final int PAINT_BULE = 2;
    public static final int PAINT_RED = 3;
    public static final int PEN_GREEN = 4;
    public static final int PEN_BULE = 5;
    public static final int PEN_RED = 6;
    public static int CURRENTTOOL = PEN_RED;

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
