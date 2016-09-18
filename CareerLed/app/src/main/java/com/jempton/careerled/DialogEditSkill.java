package com.jempton.careerled;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by BALE on 23/09/2015.
 */
public class DialogEditSkill extends DialogFragment {

    Button addButton;
    EditText newAddition;
    LinearLayout collection;
    int index = 0;
    int maxSlots = 7;
    String title;
    TextView dialogTopic;

    public  DialogEditSkill(String title){
        this.title = title;

    }
    public  DialogEditSkill(){

    }

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String value);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    NoticeDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_edit_skill, null);

        builder.setView(view)
        // Add action buttons
        ;
        addButton = (Button)view.findViewById(R.id.add_button);
        collection = (LinearLayout)view.findViewById(R.id.collection);
        newAddition = (EditText)view.findViewById(R.id.new_addition);
        dialogTopic = (TextView)view.findViewById(R.id.dialog_topic);
        dialogTopic.setText(title);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newAddition.getText().length() <3){
                    Toast.makeText(view.getContext(), "Invalid Input",Toast.LENGTH_LONG).show();
                }
                else{
                    maxSlots = maxSlots - 1;
                    if (maxSlots == 0)
                    {
                        newAddition.setEnabled(false); addButton.setEnabled(false);

                    }

                    LinearLayout lin = new LinearLayout(view.getContext());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(10, 1, 10, 1);
                    lin.setOrientation(LinearLayout.HORIZONTAL);
                    lp.gravity = Gravity.CENTER_VERTICAL;
                    lin.setLayoutParams(lp);
                    LinearLayout wrapper = new LinearLayout(view.getContext());
                    wrapper.setBackgroundColor(Color.WHITE);
                    wrapper.setGravity(Gravity.CENTER_VERTICAL);
                    final LinearLayout.LayoutParams wrapper_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    wrapper.setLayoutParams(wrapper_params);
                    final ImageView certificationImage = new ImageView(view.getContext());
                    LinearLayout.LayoutParams image_params = new LinearLayout.LayoutParams(10,10);
                    image_params.setMargins(8, 0, 0, 0);
                    certificationImage.setLayoutParams(image_params);
                    certificationImage.setImageResource(R.drawable.bullet);
                    LinearLayout.LayoutParams text_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    text_params.setMargins(10, 10, -40, 10);
                    final TextView text = new TextView(view.getContext());
                    text.setText(newAddition.getText());
                    text.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                    text.setTextSize(22);
                    text.setLayoutParams(text_params);

                    final ImageView cancel = new ImageView(view.getContext());
                    cancel.setImageResource(R.drawable.blue_cancel_2);
                    LinearLayout.LayoutParams cancel_params = new LinearLayout.LayoutParams(30,30);
                    cancel_params.setMargins(-40, 0, 0, 0);
                    cancel.setLayoutParams(cancel_params);


                    wrapper.addView(certificationImage);
                    wrapper.addView(text);
                    wrapper.addView(cancel);
                    lin.addView(wrapper);

                    collection.addView(lin);
                    text.setClickable(true);
                    newAddition.setText("");

                    cancel.setId(index);
                    text.setId(cancel.getId() + 1001);
                    lin.setId(cancel.getId() + 1);
                    index = index + 1;
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View vv = (ViewGroup) view.findViewById(cancel.getId() + 1);
                            maxSlots = maxSlots + 1;
                            collection.removeView(vv);
                            newAddition.setEnabled(true); addButton.setEnabled(true);
                        }
                    });
                    text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View vv = (ViewGroup) view.findViewById(text.getId() - 1000);
                            maxSlots = maxSlots + 1;
                            collection.removeView(vv);
                            newAddition.setEnabled(true);
                            addButton.setEnabled(true);
                        }
                    });
                }
            }
        });


        return builder.create();

    }
}
