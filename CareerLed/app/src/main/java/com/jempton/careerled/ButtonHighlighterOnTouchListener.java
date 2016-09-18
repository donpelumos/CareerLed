package com.jempton.careerled;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by BALE on 02/08/2015.
 */
public class ButtonHighlighterOnTouchListener implements View.OnTouchListener {
    //This
    final Button imageButton;

    public ButtonHighlighterOnTouchListener(final Button imageButton) {
        super();
        this.imageButton = imageButton;
    }



    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            //grey color filter, you can change the color as you like
            imageButton.setBackgroundColor(Color.argb(155, 51, 109, 183));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            imageButton.setBackgroundColor(Color.argb(255, 51, 109, 183));
        }
        return false;
    }

}