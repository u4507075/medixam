package exam;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.piyapong.drawing.MainActivity;
import com.example.piyapong.drawing.R;

import java.util.ArrayList;
import Nicetext.Style;
/**
 * Created by bon on 27/01/18.
 */

public class Content {
    public void addQuestion(Context context, LinearLayout question_content, Exam.Examination examination)
    {
        ArrayList<Exam.Question> question = examination.getQuestion();

        if(question!=null)
        {
            for(int i=0;i<question.size();i++)
            {
                if(question.get(i).getType().equals("text"))
                {
                    question_content.addView(getText(context, question.get(i).getValue(), question.get(i).getStyle()));
                }
                else if(question.get(i).getType().equals("image"))
                {
                    question_content.addView(addImage(context, question.get(i).getValue()));
                }
            }
        }
    }
    private TextView getText(Context context, String text, ArrayList stylelist){
        /*
         <TextView
            android:id="@+id/question"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textColor="@color/colorText"
            android:textSize="21sp"
            android:paddingBottom="20px"
            />
         */
        TextView t = new TextView(context);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        t.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        t.setTextColor(context.getResources().getColor(R.color.colorText));
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP,21);
        t.setPadding(0,0,0,20);
        t.setText(setTextlayout(context, text, stylelist));

        return t;
    }
    private CharSequence setTextlayout(Context context, String text, ArrayList stylelist)
    {
        /*
            // make the text twice as large
            styledString.setSpan(new RelativeSizeSpan(2f), 0, 5, 0);

            // make text bold
            styledString.setSpan(new StyleSpan(Typeface.BOLD), 7, 11, 0);

            // underline text
            styledString.setSpan(new UnderlineSpan(), 13, 23, 0);

            // make text italic
            styledString.setSpan(new StyleSpan(Typeface.ITALIC), 25, 31, 0);

            styledString.setSpan(new StrikethroughSpan(), 33, 46, 0);

            // change text color
            styledString.setSpan(new ForegroundColorSpan(Color.GREEN), 48, 55, 0);

            // highlight text
            styledString.setSpan(new BackgroundColorSpan(Color.CYAN), 57, 68, 0);

            // superscript
            styledString.setSpan(new SuperscriptSpan(), 72, 83, 0);
            // make the superscript text smaller
            styledString.setSpan(new RelativeSizeSpan(0.5f), 72, 83, 0);

            // subscript
            styledString.setSpan(new SubscriptSpan(), 87, 96, 0);
            // make the subscript text smaller
            styledString.setSpan(new RelativeSizeSpan(0.5f), 87, 96, 0);

            // url
            styledString.setSpan(new URLSpan("http://www.google.com"), 98, 101, 0);
            */
        SpannableStringBuilder cs = new SpannableStringBuilder(text);

        Style s = new Style();

        if(stylelist!=null)
        {
            for(int i=0;i<stylelist.size();i++)
            {
                ArrayList l = (ArrayList)stylelist.get(i);
                for(int j=0;j<l.size();j++)
                {
                    if(l.get(j).toString().equals(s.BOLD()))
                    {
                        cs.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    else if(l.get(j).toString().equals(s.ITALIC()))
                    {
                        cs.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    else if(l.get(j).toString().equals(s.SUBSCRIPT()))
                    {
                        cs.setSpan(new SubscriptSpan(), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        cs.setSpan(new RelativeSizeSpan(0.5f), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    else if(l.get(j).toString().equals(s.SUPERSCRIPT()))
                    {
                        cs.setSpan(new SuperscriptSpan(), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        cs.setSpan(new RelativeSizeSpan(0.5f), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    else if(l.get(j).toString().equals(s.STRIKE()))
                    {
                        cs.setSpan(new StrikethroughSpan(), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    else if(l.get(j).toString().equals(s.UNDERLINE()))
                    {
                        cs.setSpan(new UnderlineSpan(), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }
        }
        return cs;
    }
    private ImageView addImage(Context context, String key)
    {
        /*
        <ImageView
            android:id="@+id/imagesample"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@drawable/image1"
            android:adjustViewBounds="true"
            android:scaleType="centerCrop"
            android:layout_gravity="center"
            android:maxHeight="500dp"
            android:paddingBottom="20px"
            />
         */
        ImageView image = new ImageView(context);
        image.setImageBitmap(MainActivity.getThumbnailtoCache(key));
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.gravity = Gravity.CENTER;
        image.setLayoutParams(params);
        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image.setMaxHeight(500);
        image.setPadding(0,0,0,20);
        return image;
    }
}
