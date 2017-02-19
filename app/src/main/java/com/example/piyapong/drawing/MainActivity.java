package com.example.piyapong.drawing;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


/*
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        hideSystemUI();
*/
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }
/*
    // This snippet hides the system bars.
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    // This snippet shows the system bars. It does this by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
    */
    @Override
    protected void onResume()
    {
        super.onResume();
        //hideSystemUI();
    }

    public void onWindowFocusChanged (boolean hasWindowFocus){
        //hideSystemUI();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exam_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            WebView question = (WebView) rootView.findViewById(R.id.question);
            String summary = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body {text-align: justify;font-size:22px;line-height: 150%;}" +
                    "sup   {font-size:10px; vertical-align: super; line-height: 100%}" +
                    "sub   {font-size:10px; vertical-align: sub; line-height: 100%}" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>"+ "You scored <b>192</b> points. Which of these H<sub>2</sub>O X<sup>4</sup> combinations of clinical features is most suggestive of mixed mitral valve disease with a predominance of mitral regurgitation?"
                    +"Last night's wind drove the fire South, away from us, so we are still out of danger for now. No new expansion of alert areas. Thanks to all of you praying. The fire is still expanding, just not toward us."
                    +"</body></html>";
            question.loadData(summary, "text/html; charset=utf-8", null);
            question.setBackgroundColor(Color.TRANSPARENT);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }

    }

    public void selectTool(View view) {
        ((ImageButton) findViewById(R.id.eraser)).setAlpha(0.2f);
        ((ImageButton) findViewById(R.id.paint_blue)).setAlpha(0.2f);
        ((ImageButton)findViewById(R.id.paint_blue)).setBackgroundResource(R.drawable.paint_blue);
        ((ImageButton) findViewById(R.id.paint_green)).setAlpha(0.2f);
        ((ImageButton)findViewById(R.id.paint_green)).setBackgroundResource(R.drawable.paint_green);
        ((ImageButton) findViewById(R.id.paint_red)).setAlpha(0.2f);
        ((ImageButton)findViewById(R.id.paint_red)).setBackgroundResource(R.drawable.paint_red);
        ((ImageButton) findViewById(R.id.pen_blue)).setAlpha(0.2f);
        ((ImageButton)findViewById(R.id.pen_blue)).setBackgroundResource(R.drawable.pen_blue);
        ((ImageButton) findViewById(R.id.pen_green)).setAlpha(0.2f);
        ((ImageButton)findViewById(R.id.pen_green)).setBackgroundResource(R.drawable.pen_green);
        ((ImageButton) findViewById(R.id.pen_red)).setAlpha(0.2f);
        ((ImageButton)findViewById(R.id.pen_red)).setBackgroundResource(R.drawable.pen_red);
        ((ImageButton) findViewById(view.getId())).setAlpha(1f);

        if(view.getId()==R.id.paint_blue)
        {
            ((ImageButton)findViewById(R.id.paint_blue)).setBackgroundResource(R.drawable.paint_blue_filled);
        }
        else if(view.getId()==R.id.paint_green)
        {
            ((ImageButton)findViewById(R.id.paint_green)).setBackgroundResource(R.drawable.paint_green_filled);
        }
        else if(view.getId()==R.id.paint_red)
        {
            ((ImageButton)findViewById(R.id.paint_red)).setBackgroundResource(R.drawable.paint_red_filled);
        }
        else if(view.getId()==R.id.pen_blue)
        {
            ((ImageButton)findViewById(R.id.pen_blue)).setBackgroundResource(R.drawable.pen_blue_filled);
        }
        else if(view.getId()==R.id.pen_green)
        {
            ((ImageButton)findViewById(R.id.pen_green)).setBackgroundResource(R.drawable.pen_green_filled);
        }
        else if(view.getId()==R.id.pen_red)
        {
            ((ImageButton)findViewById(R.id.pen_red)).setBackgroundResource(R.drawable.pen_red_filled);
        }
    }

    public void showOverviewexam(View v) {



        Examoverview vv = new Examoverview();

        vv.show(getFragmentManager(),"Exam overview");
    }

}
