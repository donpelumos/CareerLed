package com.jempton.careerled;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

/**
 * Created by BALE on 23/09/2015.
 */
public class ActivityAccount extends ActionBarActivity {

    Button saveButton;
    ProgressDialog progress;
    ImageButton backButton;
    String [] levels = {"Junior Secondary","Senior Secondary","University/College"};
    String [] areas = {"Arts","Social Science","Science","Technology"};
    String [] fields = {"Pure Sciences","Health Science","Engineering"};
    String [] courses = {"Computer Science","Micro Biology","Petroleum Engineering"};
    ArrayAdapter<String> a1,a2,a3,a4;
    Spinner s1,s2,s3,s4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//This hides the automatic keyboard when activity starts
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

        saveButton = (Button)findViewById(R.id.save_button);
        saveButton.setOnTouchListener(new ButtonHighlighterOnTouchListener(saveButton));
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButton.setBackground(getResources().getDrawable(R.drawable.button_round_bg));
            }
        });
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
}
