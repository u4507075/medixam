package com.example.piyapong.drawing;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Piyapong on 21/02/2017.
 */
public class Mypath {
    private Paint paint;
    private Path path;
    private boolean visible = true;
    public Mypath(Path path, Paint paint)
    {
        this.path = path;
        this.paint = paint;
    }
    public void setPaint(Paint paint)
    {
        this.paint = paint;
    }
    public Paint getPaint()
    {
        return paint;
    }
    public void setPath(Path path)
    {
        this.path = path;
    }
    public Path getPath()
    {
        return path;
    }
    public void setVisible()
    {
        this.visible = true;
    }
    public void setInvisible()
    {
        this.visible = false;
    }
    public boolean getVisibility()
    {
        return visible;
    }
}
