package com.jempton.careerled;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * Created by BALE on 27/09/2015.
 */
public class ActivityGroupMessageChat extends ActionBarActivity {
    MenuList [] menuListData;
    ListView settingsList;
    ImageButton cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_message_chat);

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
