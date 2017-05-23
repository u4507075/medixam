package com.example.piyapong.drawing;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import thumbnail.MyAdapter;

/**
 * Created by Garena on 23/5/2560.
 */

public class Imageview  extends DialogFragment {
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

        WebView view = new WebView(getActivity());
        view.getSettings().setBuiltInZoomControls(true);

        view.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='image1.jpg' />", "text/html", "utf-8", null);


        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Imageview.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
