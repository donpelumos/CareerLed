package com.jempton.careerled;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by BALE on 18/09/2015.
 */
public class DialogMessageCompose extends DialogFragment  {

    ImageButton cancelButton;

    public  DialogMessageCompose(){

    }


    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String value);
        public void onDialogNegativeClick(DialogFragment dialog);
        public void onDialogClose(DialogFragment dialog);
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
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_message_compose, null);
        cancelButton = (ImageButton)view.findViewById(R.id.cancel_button);
        cancelButton.setOnTouchListener(new ImageButtonHighlighterOnTouchListener(cancelButton));

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onDialogClose(DialogMessageCompose.this);
            }
        });

        builder.setView(view);
        return builder.create();

    }
}
