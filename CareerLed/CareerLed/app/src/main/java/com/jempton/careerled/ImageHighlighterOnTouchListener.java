package com.jempton.careerled;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by BALE on 28/08/2015.
 */
public class ImageHighlighterOnTouchListener implements View.OnTouchListener {
    //This
    final ImageView imageButton;

    public ImageHighlighterOnTouchListener(final ImageView imageButton) {
        super();
        this.imageButton = imageButton;
    }


    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            //imageButton.setBackgroundColor(Color.argb(155, 51, 109, 183));
            //imageButton.getLayoutParams().width = 80;
            //imageButton.getLayoutParams().height = 80;
            imageButton.setVisibility(View.INVISIBLE);
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            //imageButton.setBackgroundColor(Color.argb(255, 51, 109, 183));
            //imageButton.getLayoutParams().width = 40;
            //imageButton.getLayoutParams().height = 40;
            imageButton.setVisibility(View.VISIBLE);
        }
        return false;
    }
}
