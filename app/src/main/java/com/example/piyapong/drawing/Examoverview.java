package com.example.piyapong.drawing;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Piyapong on 18/02/2017.
 */
public class Examoverview extends DialogFragment {

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();


        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
// Each row in the list stores country name, currency and flag
        /*
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<50;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            //hm.put("txt", ""+i);
            hm.put("flag", Integer.toString(R.drawable.noimage) );
            aList.add(hm);
        }*/

        List<HashMap<String,Bitmap>> aList = new ArrayList<HashMap<String,Bitmap>>();
        for(int i=0;i<Variable.TOTALPAGE;i++){
            HashMap<String, Bitmap> hm = new HashMap<String,Bitmap>();
            //hm.put("txt", ""+i);
            hm.put("flag", Variable.THUMBNAIL[i] );
            aList.add(hm);
        }

        // Keys used in Hashmap
        //String[] from = { "flag","txt"};
        String[] from = { "flag"};

        // Ids of views in listview_layout
        //int[] to = { R.id.flag,R.id.txt};
        int[] to = { R.id.flag};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), aList, R.layout.grid_single, from, to);
        // Prepare grid view
        GridView gridView = new GridView(getActivity());

        gridView.setAdapter(adapter);
        //gridView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, aList));
        gridView.setColumnWidth(300);
        gridView.setVerticalSpacing(15);
        gridView.setHorizontalSpacing(15);
        gridView.setGravity(Gravity.CENTER);
        //gridView.setNumColumns(10);
        gridView.setNumColumns(GridView.AUTO_FIT);
        //gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // do something here
            }
        });
        builder.setView(gridView)
                /*
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // sign in the user ...
            }
        })
                */
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Examoverview.this.getDialog().cancel();
                    }
                });
        /*
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.exam_overview, null))
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Examoverview.this.getDialog().cancel();
                    }
                });
        */
        return builder.create();
    }

}
