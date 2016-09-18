package com.jempton.careerled;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


public class ActivityCourseSignUp extends ActionBarActivity {
    ProgressDialog progress;
    ArrayAdapter<String> a1,a2,a3,a4;
    ImageButton nextButton, prevButton;
    Spinner s1,s2,s3,s4;
    String [] levels = {"Junior Secondary","Senior Secondary","University/College"};
    String [] areas = {"Arts","Social Science","Science","Technology"};
    String [] fields = {"Pure Sciences","Health Science","Engineering"};
    String [] courses = {"Computer Science","Micro Biology","Petroleum Engineering"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_sign_up);



        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//This hides the automatic keyboard when activity starts

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        nextButton = (ImageButton)findViewById(R.id.next_button);
        prevButton = (ImageButton)findViewById(R.id.back_button);
        prevButton.setOnTouchListener(new ImageButtonHighlighterOnTouchListener(prevButton));
        nextButton.setOnTouchListener(new ImageButtonHighlighterOnTouchListener(nextButton));

        progress = new ProgressDialog(this);
        s1 = (Spinner)findViewById(R.id.level);
        s2 = (Spinner)findViewById(R.id.area);
        s3 = (Spinner)findViewById(R.id.field);
        s4 = (Spinner)findViewById(R.id.course);
        a1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,levels);
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,areas);
        a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,fields);
        a3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,courses);
        a4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(a1);
        s2.setAdapter(a2);
        s3.setAdapter(a3);
        s4.setAdapter(a4);
        s1.setOnItemSelectedListener(new Spinner_Listener());
        s2.setOnItemSelectedListener(new Spinner_Listener());
        s3.setOnItemSelectedListener(new Spinner_Listener());
        //s4.setOnItemSelectedListener(new Spinner_Listener());
    }

    public class Spinner_Listener implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            progress.setTitle("CareerLed");
            progress.setMessage("Loading...");
            progress.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    progress.dismiss();
                }
            }, 1500);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    public void onPreviousClicked(View view)
    {
        progress.setTitle("CareerLed");
        progress.setMessage("Loading...");
        progress.show();

        // To dismiss the dialog
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progress.dismiss();
                ActivityCourseSignUp.this.onBackPressed();
                //By using the above over the below it prevents the creation of a new SignUp.class activity which complicates issues
                //Intent toPreviousPage = new Intent(CourseSignUp.this, SignUp.class);
                //startActivity(toPreviousPage);
            }
        }, 1500);

    }

    public void onNextClicked(View view)
    {
        progress.setTitle("CareerLed");
        progress.setMessage("Loading...");
        progress.show();

        // To dismiss the dialog
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progress.dismiss();
                Intent toNextPage = new Intent(ActivityCourseSignUp.this, ActivityHobbiesSignUp.class);
                startActivity(toNextPage);
            }
        }, 1500);

    }

    public void onSkipPage(View view)
    {
        //Not needed for this
        //onNextClicked(view);
        Intent toNextPage = new Intent(ActivityCourseSignUp.this, ActivityHobbiesSignUp.class);
        startActivity(toNextPage);
    }
}
