package com.jempton.careerled;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by BALE on 22/10/2015.
 */
public class DialogComments extends DialogFragment {
    LinearLayout commentsCase;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_comments, null);
        commentsCase = (LinearLayout)view.findViewById(R.id.comments_case);
        int [] images={R.drawable.picture,R.drawable.picture01,R.drawable.picture02,R.drawable.picture03,R.drawable.picture04,
                R.drawable.picture05,R.drawable.picture06,R.drawable.picture07};
        Random random = new Random();
        for(int i=0; i<10; i++){
            LinearLayout commentRow = new LinearLayout(view.getContext());
            commentRow.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams commentRowParams = new
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            //commentRowParams.setMargins(5,5,5,5);
            commentRow.setLayoutParams(commentRowParams);
            commentRow.setGravity(Gravity.TOP);
            ImageView commentImage = new ImageView(view.getContext());
            LinearLayout.LayoutParams commentImageParams = new
                    LinearLayout.LayoutParams(60,60);
            commentImageParams.setMargins(5, 5, 5, 5);
            commentImage.setLayoutParams(commentImageParams);
            commentImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //commentImage.setImageResource(images[random.nextInt(6)]);
            commentImage.setImageResource(R.drawable.ic_communities);
            LinearLayout commentSubRow = new LinearLayout(view.getContext());
            commentSubRow.setOrientation(LinearLayout.VERTICAL);

            TextView commenter = new TextView(view.getContext());
            LinearLayout.LayoutParams bodyTextParams = new
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            commenter.setLayoutParams(bodyTextParams);
            commenter.setText("Surname Firstname");
            commenter.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
            //commenter.setGravity(Gravity.TOP);
            commenter.setTextColor(Color.BLACK);
            commenter.setTextSize(13);

            TextView commentDetails = new TextView(view.getContext());
            LinearLayout.LayoutParams commentDetailsParams = new
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            commentDetails.setLayoutParams(commentDetailsParams);
            commentDetails.setTextSize(13);
            //commentDetails.setGravity(Gravity.CENTER_VERTICAL);
            commentDetails.setText("Response comment made by a user");

            TextView commentDate = new TextView(view.getContext());
            LinearLayout.LayoutParams commentDateParams = new
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            commentDate.setLayoutParams(commentDateParams);
            commentDate.setTextSize(11);
            //commentDate.setGravity(Gravity.CENTER_VERTICAL);
            commentDate.setText("Date -- Time");

            commentSubRow.addView(commenter);
            commentSubRow.addView(commentDetails);
            commentSubRow.addView(commentDate);

            commentRow.addView(commentImage);
            commentRow.addView(commentSubRow);

            commentsCase.addView(commentRow);
            commentsCase.addView(addSpace(view));
        }
        builder.setView(view);
        return builder.create();
    }
    public Space addSpace(View v){
        Space space = new Space(v.getContext());
        LinearLayout.LayoutParams spaceParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,10);
        space.setLayoutParams(spaceParams);
        return space;
    }
}
