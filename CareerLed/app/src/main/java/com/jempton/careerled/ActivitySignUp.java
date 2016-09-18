package com.jempton.careerled;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


public class iActivitySignUp extends ActionBarActivity {
    Spinner s1, s2, s3;
    ProgressDialog progress;
    ImageButton nextButton, prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//This hides the automatic keyboard when activity starts
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //next_button = (ImageButton)findViewById(R.id.next_button);
        //next_button.setOnTouchListener(new ButtonHighlighterOnTouchListener(next_button));

        nextButton = (ImageButton)findViewById(R.id.next_button);
        prevButton = (ImageButton)findViewById(R.id.back_button);
        prevButton.setOnTouchListener(new ImageButtonHighlighterOnTouchListener(prevButton));
        nextButton.setOnTouchListener(new ImageButtonHighlighterOnTouchListener(nextButton));

        String [] day_count = {"1","2","3"};
        String [] month_count = {"January","February","March"};
        String [] year_count = {"1991","1992","1993"};
        s1 = (Spinner)findViewById(R.id.day_count);
        s2 = (Spinner)findViewById(R.id.month_count);
        s3 = (Spinner)findViewById(R.id.year_count);
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, day_count);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> aa2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, month_count);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> aa3=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year_count);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(aa);
        s2.setAdapter(aa2);
        s3.setAdapter(aa3);
        progress = new ProgressDialog(this);
    }

   public void nextSignUpClicked(View view)
   {
       progress.setTitle("CareerLed");
       progress.setMessage("Loading...");
       progress.show();

       // To dismiss the dialog
       Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           public void run() {
               progress.dismiss();
               Intent toNextSignUp = new Intent(ActivitySignUp.this, ActivityCourseSignUp.class);
               startActivity(toNextSignUp);

           }
       }, 1500);

   }
}


