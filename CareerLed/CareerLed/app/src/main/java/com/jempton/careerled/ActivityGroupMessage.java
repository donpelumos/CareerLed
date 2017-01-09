package com.jempton.careerled;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * Created by BALE on 27/09/2015.
 */
public class ActivityGroupMessage extends ActionBarActivity implements DialogGroupOptions.NoticeDialogListener{
    MessageList [] messageListData;
    ListView settingsList;
    ImageButton backButton;
    FragmentManager fm;
    int longClickOnly = 0;

    public ActivityGroupMessage(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_message);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//This hides the automatic keyboard when activity starts

        backButton = (ImageButton)findViewById(R.id.back_button);
        backButton.setOnTouchListener(new ImageButtonHighlighterOnTouchListener(backButton));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        fm = getFragmentManager();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        settingsList = (ListView)findViewById(R.id.group_message_list);
        messageListData = new MessageList[]{
                new MessageList("Group 1",R.drawable.groups_icon,"" ), new MessageList("Group 2",R.drawable.groups_icon,"" ),
                new MessageList("Group 3",R.drawable.groups_icon,"" ),
                new MessageList("Group 4",R.drawable.groups_icon,"" ), new MessageList("Group 5",R.drawable.groups_icon,""),
                new MessageList("Group 6",R.drawable.groups_icon,"" ), new MessageList("Group 7",R.drawable.groups_icon,""),
                new MessageList("Group 8",R.drawable.groups_icon,""), new MessageList("Group 9",R.drawable.groups_icon,""),
                new MessageList("Group 10",R.drawable.groups_icon,""), new MessageList("Group 11",R.drawable.groups_icon,""),
                new MessageList("Group 12",R.drawable.groups_icon,"")};

        final MessageListAdapter adp = new MessageListAdapter(getApplicationContext(),R.layout.row_group_message, messageListData );
        settingsList.setAdapter(adp);

        settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (longClickOnly == 0) {
                    Intent intent = new Intent(getApplicationContext(), ActivityGroupMessageChat.class);
                    startActivity(intent);
                }
            }
        });
        settingsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClickOnly = 1;
                DialogGroupOptions newFragment = new DialogGroupOptions();
                newFragment.show(fm, "dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        longClickOnly = 0;
                    }
                }, 2000);
                return false;

            }
        });

    }

    @Override
    public void onSelected(DialogFragment dialog, String value) {
        DialogGroupOptions df = (DialogGroupOptions)fm.findFragmentByTag("dialog");
        df.dismiss();
        longClickOnly = 0;
    }
}
