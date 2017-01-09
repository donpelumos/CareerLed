package com.jempton.careerled;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ActivityLoginPage extends ActionBarActivity {
    Button myButton, myButton2; ProgressDialog progress;
    int login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_login_page);
        Intent in1 = getIntent();
        login = in1.getExtras().getInt("signup");
        if(login == 1){
            Toast.makeText(ActivityLoginPage.this, "Confirmation email has been sent. Pls verify email to login", Toast.LENGTH_LONG).show();
        }
        myButton = (Button) findViewById(R.id.loginButton);
        myButton2 = (Button) findViewById(R.id.signUpButton);
        myButton.setOnTouchListener(new ButtonHighlighterOnTouchListener(myButton));
        myButton2.setOnTouchListener(new ButtonHighlighterOnTouchListener(myButton2));
        progress = new ProgressDialog(this);

    }


    public void onSignUpClicked(View view)
    {
        progress.setTitle("CareerLed");
        progress.setMessage("Loading...");
        progress.show();

        // To dismiss the dialog
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progress.dismiss();
                Intent toSignUp = new Intent(ActivityLoginPage.this, ActivitySignUp.class);
                startActivity(toSignUp);
            }
        }, 1500);


    }

    public void onLoginClicked(View view)
    {
        progress.setTitle("CareerLed");
        progress.setMessage("Loading...");
        progress.show();

        // To dismiss the dialog
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progress.dismiss();
                Intent toSignUp = new Intent(ActivityLoginPage.this, ActivityHome.class);
                toSignUp.putExtra("index",1);
                startActivity(toSignUp);
            }
        }, 1500);


    }


}
