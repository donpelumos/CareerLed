package com.jempton.careerled;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * Created by BALE on 16/10/2015.
 */
public class ActivityNotification extends ActionBarActivity {
    NotificationList [] notificationListData;
    ListView notificationList;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        backButton = (ImageButton)findViewById(R.id.back_button);
        backButton.setOnTouchListener(new ImageButtonHighlighterOnTouchListener(backButton));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        notificationList = (ListView)findViewById(R.id.notification_list);
        notificationListData = new NotificationList[]{
                new NotificationList("Surname + Firstname",R.drawable.ic_pages ), new NotificationList("Surname + Firstname",R.drawable.ic_pages ),
                new NotificationList("Surname + Firstname",R.drawable.ic_pages ), new NotificationList("Surname + Firstname",R.drawable.ic_pages ),
                new NotificationList("Surname + Firstname",R.drawable.ic_pages ), new NotificationList("Surname + Firstname",R.drawable.ic_pages ),
                new NotificationList("Surname + Firstname",R.drawable.ic_pages ), new NotificationList("Surname + Firstname",R.drawable.ic_pages),
                new NotificationList("Surname + Firstname",R.drawable.ic_pages), new NotificationList("Surname + Firstname",R.drawable.ic_pages ),
                new NotificationList("Surname + Firstname",R.drawable.ic_pages ), new NotificationList("Surname + Firstname",R.drawable.ic_pages ),};

        final NotificationListAdapter adp = new NotificationListAdapter(getApplicationContext(),R.layout.row_notifications, notificationListData );
        notificationList.setAdapter(adp);

        notificationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(getApplicationContext(), ActivityProfilePageOther.class);
                //startActivity(intent);

            }
        });

    }
}
