package com.jempton.careerled;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.TextView;

public class ActivitySplashScreen extends ActionBarActivity {
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.personal);
        actionBar.hide();

        text1 = (TextView)findViewById(R.id.textView);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Tahoma.ttf");
        text1.setTypeface(type,Typeface.BOLD); text1.setTextColor(getResources().getColor(R.color.white_bg));

        //int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(ActivitySplashScreen.this, ActivityLoginPage.class).putExtra("signup",0));
                finish();

            }
        }, 3000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }


}
