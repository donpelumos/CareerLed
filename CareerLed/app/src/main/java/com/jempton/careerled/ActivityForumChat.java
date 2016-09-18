package com.jempton.careerled;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

/**
 * Created by BALE on 27/09/2015.
 */
public class ActivityForumChat extends ActionBarActivity {

    Button saveButton;
    ProgressDialog progress;
    ImageButton cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_chat);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//This hides the automatic keyboard when activity starts
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        cancelButton = (ImageButton)findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        cancelButton.setOnTouchListener(new ImageButtonHighlighterOnTouchListener(cancelButton));
    }

}