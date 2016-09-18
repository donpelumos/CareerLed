package com.jempton.careerled;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

/**
 * Created by BALE on 18/08/2015.
 */
public class DialogCertification extends DialogFragment  {

    ImageView certificateImage;
    Spinner chooseCertificate;
    LinearLayout lin;
    ArrayAdapter<String> aa;
    String values = null;
    EditText certificationName;

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String value);
        public void onDialogNegativeClick(DialogFragment dialog);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_certification, null);
        certificationName = (EditText)view.findViewById(R.id.certification_name);

        // Inflate and set the layout for the dialog

        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        mListener.onDialogPositiveClick(DialogCertification.this, values);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getDialog().cancel();
                        mListener.onDialogNegativeClick(DialogCertification.this);
                    }
                });
        certificateImage = (ImageView)view.findViewById(R.id.certification_image);
        final String [] certificate_count = {"Waec","Jamb","Neco","Other"};
        lin = (LinearLayout)view.findViewById(R.id.others);

        chooseCertificate = (Spinner)view.findViewById(R.id.certification_authority);
        aa=new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, certificate_count);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        chooseCertificate.setAdapter(aa);

        chooseCertificate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    certificateImage.setImageResource(R.drawable.waec);
                    certificateImage.setVisibility(View.VISIBLE);
                    values = "0";
                    lin.setVisibility(View.GONE);
                } else if (position == 1) {
                    certificateImage.setImageResource(R.drawable.jamb);
                    certificateImage.setVisibility(View.VISIBLE);
                    values= "1";
                    lin.setVisibility(View.GONE);
                } else if (position == 3) {
                    certificateImage.setImageResource(R.drawable.jamb);
                    values = "3";
                    lin.setVisibility(View.VISIBLE);
                } else {
                    certificateImage.setImageResource(R.drawable.neco);
                    certificateImage.setVisibility(View.VISIBLE);
                    values="4";
                    lin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return builder.create();

    }



    public void setvalues()
    {

    }

    public String getvalues()
    {
        return values;
    }



}
