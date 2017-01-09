package com.jempton.careerled;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by BALE on 11/10/2015.
 */
public class DialogForumOptions extends DialogFragment {

    ListView options;


    public interface NoticeDialogListener {
        public void onSelected(DialogFragment dialog, String value);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (NoticeDialogListener)activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_group_options, null);

        builder.setView(view)
        // Add action buttons
        ;

        options = (ListView)view.findViewById(R.id.group_options);
        ArrayList<String> list = new ArrayList<String>();
        list.add("Forum Info");list.add("Leave Forum");
        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,list);
        options.setAdapter(adp2);

        options.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mListener.onSelected(DialogForumOptions.this, "");
                } else if (position == 1) {
                    mListener.onSelected(DialogForumOptions.this, "");
                }
            }
        });

        return builder.create();

    }




}