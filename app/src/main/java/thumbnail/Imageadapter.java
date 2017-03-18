package thumbnail;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.piyapong.drawing.MainActivity;
import com.example.piyapong.drawing.R;
import com.example.piyapong.drawing.Variable;

/**
 * Created by Piyapong on 26/02/2017.
 */
public class Imageadapter extends SimpleAdapter {
    private int mResource;
    private int[] mTo;
    private String[] mFrom;

    private List<? extends Map<String, ?>> mData;
    private Context context;

    public Imageadapter(Context context, List<? extends Map<String, ?>> data,
                           int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);

        mResource = resource;
        mData = data;
        mTo = to;
        mFrom = from;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        v = createViewFromResourceEx(v, position, convertView, parent, mResource);

        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = super.getDropDownView(position, convertView, parent);

        v = createViewFromResourceEx(v, position, convertView, parent, mResource);

        return v;
    }

    private View createViewFromResourceEx(View v, int position, View convertView,
                                          ViewGroup parent, int resource) {

        //bindView(position, v);
        //setViewImage((ImageView)v.findViewById(R.id.image), MainActivity.getBitmapFromMemCache("thumbnail"+position));
        //setViewImage((ImageView)v.findViewById(R.id.image), MainActivity.getImagefromCache(position).getString());
        return v;
    }

    private void bindView(int position, View view) {
        final Map dataSet = mData.get(position);
        if (dataSet == null) {
            return;
        }

        final ViewBinder binder = getViewBinder();
        final String[] from = mFrom;
        final int[] to = mTo;
        final int count = to.length;

        for (int i = 0; i < count; i++) {
            final View v = view.findViewById(to[i]);
            if (v != null) {
                final Object data = dataSet.get(from[i]);
                String text = data == null ? "" : data.toString();
                if (text == null) {
                    text = "";
                }

                boolean bound = false;
                if (binder != null) {
                    bound = binder.setViewValue(v, data, text);
                }

                if (!bound) {
                    if (v instanceof ImageView) {
                        if (data instanceof Bitmap) {
                            setViewImage((ImageView) v, (Bitmap)data);
                        }
                    }
                }
            }
        }
    }

    private void setViewImage(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getCount() {
        return Variable.TOTALPAGE;
    }
}
