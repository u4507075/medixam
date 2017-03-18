package thumbnail;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.View;

import com.example.piyapong.drawing.MainActivity;
import com.example.piyapong.drawing.Pageviewer;
import com.example.piyapong.drawing.Variable;

/**
 * Created by Piyapong on 22/02/2017.
 */
public class SaveThumbnail extends AsyncTask<Integer, Integer, Long> {

    Pageviewer mViewPager;
    public SaveThumbnail(Pageviewer mViewPager)
    {
        this.mViewPager = mViewPager;
    }
    protected void onProgressUpdate(Integer... progress) {

    }

    protected void onPostExecute(Long result) {

    }

    @Override
    protected Long doInBackground(Integer... params) {

        new Thumbnailthread(mViewPager).run();
        return null;
    }


}
class Thumbnailthread implements Runnable
{
    private Pageviewer mViewPager;
    public Thumbnailthread(Pageviewer mViewPager)
    {
        this.mViewPager = mViewPager;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        String tag = "EXAM"+mViewPager.getCurrentItem();
        View v1 = mViewPager.findViewWithTag(tag);
        if(v1!=null)
        {
            v1.setDrawingCacheEnabled(true);
            Bitmap bmScreen = Bitmap.createBitmap(v1.getDrawingCache(true));
            Bitmap resized = Bitmap.createScaledBitmap(bmScreen, 310, 495, true);

            //Variable.THUMBNAIL[mViewPager.getCurrentItem()] = resized;
            //mViewPager.getAdapter().notifyDataSetChanged();
            //Main.gridView.invalidateViews();
            //MainActivity.addBitmapToMemoryCache("thumbnail"+mViewPager.getCurrentItem(),new BitmapDrawable(resized));
            MainActivity.addThumbnailtoCache("thumbnail"+mViewPager.getCurrentItem(),resized);
            v1.setDrawingCacheEnabled(false);
        }
    }
}
