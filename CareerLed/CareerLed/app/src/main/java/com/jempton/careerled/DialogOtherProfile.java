package com.jempton.careerled;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by BALE on 08/09/2015.
 */
public class DialogOtherProfile extends DialogFragment {

    ImageView profilePic, coverPic;
    Button addButton;
    RoundedImage roundedImage;
    LinearLayout extraInfo;
    int network;
    Bundle extras;
    int requestSent;

    public  DialogOtherProfile(){

    }


    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String value);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_profile_other_user, null);

        builder.setView(view)
                // Add action buttons
                ;

        profilePic = (ImageView)view.findViewById(R.id.profile_pic);
        coverPic = (ImageView)view.findViewById(R.id.cover_pic);
        addButton = (Button)view.findViewById(R.id.view_profile_button);
        addButton.setOnTouchListener(new ButtonHighlighterOnTouchListener(addButton));
        addButton.setPadding(10, 0, 10, 0);
        extraInfo = (LinearLayout)view.findViewById(R.id.extra_info);
        extraInfo.setVisibility(View.GONE);

        Bitmap bm = drawableToBitmap(profilePic.getDrawable());
        roundedImage = new RoundedImage(bm);
        profilePic.setImageDrawable(roundedImage);


//write the bytes in file


// remember close de FileOutput


        extras = this.getArguments();
        network = extras.getInt("network");

        if(network == 0){
            requestSent = 1;
            extraInfo.setVisibility(View.VISIBLE);
        }
        else if(network == -1){
            requestSent = 0;
            extraInfo.setVisibility(View.VISIBLE);
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(network == 1) {
                    addButton.setBackground(getResources().getDrawable(R.drawable.button_round_bg));
                    addButton.setPadding(10, 0, 10, 0);
                    extraInfo.setVisibility(View.GONE);
                    dismiss();
                    Intent intent = new Intent(inflater.getContext(), ActivityProfilePageOther.class);
                    startActivity(intent);
                }
                else if(network == 0){
                    if(requestSent == 1){
                        addButton.setBackground(getResources().getDrawable(R.drawable.button_round_bg));
                        addButton.setPadding(10, 0, 10, 0);
                        addButton.setText("Add To Network");
                        extraInfo.setVisibility(View.VISIBLE);
                        requestSent = 0;
                    }
                    else {
                        addButton.setBackground(getResources().getDrawable(R.drawable.button_round_bg_red));
                        addButton.setPadding(10, 0, 10, 0);
                        addButton.setText("Request Sent");
                        extraInfo.setVisibility(View.VISIBLE);
                        requestSent = 1;
                    }
                }
                else{
                    if(requestSent == 1){
                        addButton.setBackground(getResources().getDrawable(R.drawable.button_round_bg));
                        addButton.setPadding(10, 0, 10, 0);
                        addButton.setText("Add To Network");
                        extraInfo.setVisibility(View.VISIBLE);
                        requestSent = 0;
                    }
                    else {
                        addButton.setBackground(getResources().getDrawable(R.drawable.button_round_bg_red));
                        addButton.setPadding(10, 0, 10, 0);
                        addButton.setText("Request Sent");
                        extraInfo.setVisibility(View.VISIBLE);
                        requestSent = 1;
                    }
                }
            }
        });


        if (network==1){
            addButton.setBackground(getResources().getDrawable(R.drawable.button_round_bg));
            addButton.setPadding(10, 0, 10, 0);
        }
        else if(network == 0){
            addButton.setBackground(getResources().getDrawable(R.drawable.button_round_bg_red));
            addButton.setPadding(10, 0, 10, 0);
            addButton.setText("Request Sent");
        }
        else if(network == -1){
            addButton.setBackground(getResources().getDrawable(R.drawable.button_round_bg));
            addButton.setPadding(10, 0, 10, 0);
            addButton.setText("Add To Network");
        }

        int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.8);
        int height = (int)(getResources().getDisplayMetrics().heightPixels * 0.8);
        //getDialog().getWindow().setLayout(width, height);
        //view.getLayoutParams().width = width;
        //view.getLayoutParams().height = height;


        return builder.create();

    }



    public Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


}
