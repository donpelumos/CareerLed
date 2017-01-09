package com.jempton.careerled;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by BALE on 25/08/2015.
 */
public class ActivityHome extends ActionBarActivity implements DialogForumOptions.NoticeDialogListener{

    ActionBarDrawerToggle mDrawerToggle;
    LinearLayout ll;
    ImageView profilePic,coverPic;
    DrawerLayout mDrawerLayout;
    RoundedImage roundedImage, roundedImage2;
    ListView menuList, optionsList;
    MenuList [] menuListData;
    Menu menu;
    TextView fragmentTitle;
    ImageView notificationIcon;
    FrameLayout notificationIconCase;
    ImageView guideIcon;
    ImageView messageIcon, groupMessageIcon, notificationImageIcon;
    int current=1;
    int optionsShown = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//This hides the automatic keyboard when activity starts

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        profilePic = (ImageView)findViewById(R.id.profile_pic);
        Bitmap bm = drawableToBitmap(profilePic.getDrawable());
        roundedImage = new RoundedImage(bm);
        profilePic.setImageDrawable(roundedImage);
        optionsList = (ListView)findViewById(R.id.options);
        optionsList.setVisibility(View.INVISIBLE);

        messageIcon = (ImageView)findViewById(R.id.im1);
        messageIcon.setOnTouchListener(new ImageHighlighterOnTouchListener(messageIcon));
        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(ll);
                Intent intent = new Intent(ActivityHome.this, ActivityMessages.class);
                startActivity(intent);
            }
        });

        groupMessageIcon = (ImageView)findViewById(R.id.im2);
        groupMessageIcon.setOnTouchListener(new ImageHighlighterOnTouchListener(groupMessageIcon));
        groupMessageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(ll);
                Intent intent = new Intent(ActivityHome.this, ActivityGroupMessage.class);
                startActivity(intent);
            }
        });

        notificationImageIcon = (ImageView)findViewById(R.id.im3);
        notificationImageIcon.setOnTouchListener(new ImageHighlighterOnTouchListener(notificationImageIcon));
        notificationImageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(ll);
                Intent intent = new Intent(ActivityHome.this, ActivityNotification.class);
                startActivity(intent);
            }
        });

/*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.personal);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.options_icon);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.argb(255, 51, 109, 183)));

*/
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.argb(255, 51, 109, 183)));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP);

        fragmentTitle = (TextView)getSupportActionBar().getCustomView().findViewById(R.id.fragment_title);
        guideIcon = (ImageView)getSupportActionBar().getCustomView().findViewById(R.id.guide_icon);
        guideIcon.setOnTouchListener(new ImageHighlighterOnTouchListener(guideIcon));
        notificationIcon = (ImageView)getSupportActionBar().getCustomView().findViewById(R.id.notification_icon);
        notificationIcon.setOnTouchListener(new ImageHighlighterOnTouchListener(notificationIcon));
        notificationIconCase = (FrameLayout)getSupportActionBar().getCustomView().findViewById(R.id.notification_icon_case);
        Toolbar toolbar=(Toolbar)getSupportActionBar().getCustomView().getParent();
        toolbar.setContentInsetsAbsolute(0,0);


        guideIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionsShown == 0) {
                    optionsList.setVisibility(View.VISIBLE);
                    optionsShown = 1;
                } else {
                    optionsList.setVisibility(View.GONE);
                    optionsShown = 0;
                }
            }
        });
        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, ActivityNotification.class);
                ActivityHome.this.startActivity(intent);
            }
        });




/*
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setHomeButtonEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255, 51, 109, 183)));
        actionBar.setIcon(R.drawable.personal);

        actionBar.setHomeAsUpIndicator(R.drawable.options_icon);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Home");


*/

        ll = (LinearLayout)findViewById(R.id.drawer_body);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.options_icon,
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                switch (current)
                {
                    case 0:
                        fragmentTitle.setText("Connect");
                        guideIcon.setVisibility(View.GONE);
                        notificationIconCase.setVisibility(View.GONE);
                        break;
                    case 1:
                        fragmentTitle.setText("Feed");
                        guideIcon.setVisibility(View.VISIBLE);
                        notificationIconCase.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        fragmentTitle.setText("Forums");
                        guideIcon.setVisibility(View.GONE);
                        notificationIconCase.setVisibility(View.GONE);
                        break;
                    case 3:
                        fragmentTitle.setText("Guide");
                        break;
                    case 4:
                        fragmentTitle.setText("My Network");
                        break;
                    case 5:
                        fragmentTitle.setText("Settings");
                        guideIcon.setVisibility(View.GONE);
                        notificationIconCase.setVisibility(View.GONE);
                        break;
                    case 6:
                        fragmentTitle.setText("Log Out");
                        break;
                }
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                fragmentTitle.setText("Main Menu");
                guideIcon.setVisibility(View.GONE);
                notificationIconCase.setVisibility(View.GONE);
                //calling onPrepareOptionsMenu() to hide action bar icons
                //invalidateOptionsMenu();
            }
        };
        menuList = (ListView)findViewById(R.id.menu_list);
        ArrayList<String> list = new ArrayList<String>();
        list.add("Create Forum");list.add("Create Group"); list.add("New Post");
        list.add("Profile");list.add("Settings");
        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        optionsList.setAdapter(adp2);
        menuListData = new MenuList[]{
                new MenuList("Connect",R.drawable.connect_icon ), new MenuList("Feed",R.drawable.ic_communities ),
                new MenuList("Forums",R.drawable.forum_icon ), new MenuList("Guide",R.drawable.guide_icon_ ),
                new MenuList("My Network",R.drawable.ic_pages ), new MenuList("Settings",R.drawable.settings_icon),
                new MenuList("Log Out",R.drawable.ic_launcher)};

        final MenuListAdapter adp = new MenuListAdapter(getApplicationContext(),R.layout.row_menu, menuListData );
        menuList.setAdapter(adp);

        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //menuList.setSelection(position);
                current = position;
                displayView(position);
            }
        });

        optionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(optionsList.getVisibility() == View.GONE)
                {
                    optionsList.setVisibility(View.VISIBLE);
                }
                else{
                    optionsList.setVisibility(View.INVISIBLE);
                }

                //optionsShown = 0;
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        Intent intent = getIntent();
        int key = intent.getExtras().getInt("index");
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(key);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...
        return true;
        //return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        if(current ==1){
            boolean drawerOpen = mDrawerLayout.isDrawerOpen(ll);
            //menu.findItem(R.id.action_settings).setVisible(false);
            //menu.findItem(R.id.extra_icon1).setVisible(true);
            //menu.findItem(R.id.extra_icon2).setVisible(false);

        }
        else{
            //menu.findItem(R.id.extra_icon1).setVisible(false);
            //menu.findItem(R.id.extra_icon2).setVisible(false);
            guideIcon.setVisibility(View.GONE);
            notificationIconCase.setVisibility(View.GONE);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void displayView(int position) {
        // update the main content by replacing fragments

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentConnect();
                fragment.setHasOptionsMenu(true);
                getSupportActionBar().setTitle("Connect");
                super.onPrepareOptionsMenu(menu);
                break;
            case 1:
                fragment = new FragmentFeed();
                getSupportActionBar().setTitle("Feed");
                break;
            case 2:
                fragment = new FragmentForums();
                getSupportActionBar().setTitle("Forums");
                break;
            case 3:
                fragment = new FragmentFeed();
                break;
            case 4:
                fragment = new FragmentNetwork();
                break;
            case 5:
                fragment = new FragmentSettings();
                break;
            case 6:
                fragment = new FragmentFeed();
                break;

            default:
                break;
        }

        if (fragment != null) {
            mDrawerLayout.closeDrawer(ll);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
            menuList.setItemChecked(position, true);
            menuList.setSelection(position);


        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }

    }

        public void make(View v){
        optionsList.setVisibility(View.INVISIBLE);
        optionsShown = 0;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        //getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
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


    @Override
    public void onSelected(DialogFragment dialog, String value) {
        FragmentManager fm = getFragmentManager();
        FragmentForums ef = (FragmentForums)fm.findFragmentByTag(FragmentForums.TAG);
        DialogForumOptions df = (DialogForumOptions)fm.findFragmentByTag("missless");
        df.dismiss();
        //longClickOnly = 0;
        //ef.doit();
    }
}
