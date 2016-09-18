package com.jempton.careerled;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * Created by BALE on 28/09/2015.
 */
public class ActivityMessages extends ActionBarActivity implements DialogMessageCompose.NoticeDialogListener {
    MenuList [] menuListData;
    ListView messageList;
    ImageButton backButton;
    FragmentManager fm;

    public ActivityMessages(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//This hides the automatic keyboard when activity starts

        backButton = (ImageButton)findViewById(R.id.back_button);
        backButton.setOnTouchListener(new ImageButtonHighlighterOnTouchListener(backButton));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        fm = getFragmentManager();
        messageList = (ListView)findViewById(R.id.message_list);
        menuListData = new MenuList[]{
                new MenuList("Person 1",R.drawable.message_icon_new ), new MenuList("Person 2",R.drawable.message_icon_new  ),
                new MenuList("Person 3",R.drawable.message_icon_new  ),
                new MenuList("Person 4",R.drawable.message_icon_new ), new MenuList("Person 5",R.drawable.message_icon_new ),
                new MenuList("Person 6",R.drawable.message_icon_new  ), new MenuList("Person 7",R.drawable.message_icon_new ),
                new MenuList("Person 8",R.drawable.message_icon_new ), new MenuList("Person 9",R.drawable.message_icon_new ),
                new MenuList("Person 10",R.drawable.message_icon_new ), new MenuList("Person 11",R.drawable.message_icon_new ),
                new MenuList("Person 12",R.drawable.message_icon_new )};


        final MenuListAdapter adp = new MenuListAdapter(getApplicationContext(),R.layout.row_message_list, menuListData );
        messageList.setAdapter(adp);

        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(getApplicationContext(), ActivityGroupMessageChat.class);
                //startActivity(intent);
                DialogMessageCompose newFragment = new DialogMessageCompose();
                newFragment.show(fm, "dialog");
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String value) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogClose(DialogFragment dialog) {
        DialogFragment df = (DialogFragment)fm.findFragmentByTag("dialog");
        df.dismiss();
    }
}
