package com.jempton.careerled;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;


public class ActivityTalentsSignUp extends ActionBarActivity {
    LinearLayout ll;
    Button addButton;
    ImageButton nextButton, prevButton;
    TextView slotsLeft;
    ProgressDialog progress;
    LinkedList<String> hobbieList;
    public int index = 0;
    int maxSlots = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents_sign_up);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//This hides the automatic keyboard when activity starts
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        hobbieList = new LinkedList<String>();
        slotsLeft = (TextView)findViewById(R.id.slots_text);
        ll = (LinearLayout)findViewById(R.id.hobbie_list);
        addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnTouchListener(new ButtonHighlighterOnTouchListener(addButton));
        nextButton = (ImageButton)findViewById(R.id.next_button);
        prevButton = (ImageButton)findViewById(R.id.back_button);
        prevButton.setOnTouchListener(new ImageButtonHighlighterOnTouchListener(prevButton));
        nextButton.setOnTouchListener(new ImageButtonHighlighterOnTouchListener(nextButton));
        progress = new ProgressDialog(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = (EditText)findViewById(R.id.hobbie_input);
                final LinearLayout lin = new LinearLayout(getApplicationContext());
                //ViewGroup.LayoutParams vw = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 0, 0, 0);
                lp.gravity = Gravity.CENTER_VERTICAL;
                lin.setLayoutParams(lp);


                lin.setOrientation(LinearLayout.HORIZONTAL);
                if(input.getText().length() <= 1){
                    Toast.makeText(ActivityTalentsSignUp.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    maxSlots = maxSlots - 1;
                    slotsLeft.setText(String.valueOf(maxSlots) + " more left");
                    if (maxSlots == 0)
                    {
                        input.setEnabled(false); addButton.setEnabled(false);
                        input.setVisibility(View.INVISIBLE); addButton.setVisibility(View.INVISIBLE);
                        slotsLeft.setText("No more left.");
                    }
                    final Button b1 = new Button(getApplicationContext()); b1.setText(input.getText());
                    //b1.setBackgroundColor(Color.argb(255,200,200,200));
                    input.setText("");

                    final ImageButton delete_button = new ImageButton(getApplicationContext()); //delete_button.setText("Delete");
                    delete_button.setImageResource(R.drawable.blue_cancel);
                    delete_button.setBackgroundColor(Color.argb(255, 51, 109, 183));
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(50, 50);
                    delete_button.setLayoutParams(params);

                    lin.addView(b1);  lin.addView(delete_button);
                    //ImageButton imb = new ImageButton(getApplicationContext());
                    //b1.setHint(String.valueOf(index));
                    //delete_button.setHint(String.valueOf(index));
                    delete_button.setId(index);
                    lin.setId(delete_button.getId() +1);
                    index = index + 1;
                    ll.addView(lin);
                    delete_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View vv = (ViewGroup) findViewById(delete_button.getId() + 1);

                            ll.removeView(vv);
                            maxSlots = maxSlots + 1;
                            slotsLeft.setText(String.valueOf(maxSlots) + " more left");
                            input.setEnabled(true); addButton.setEnabled(true);
                            input.setVisibility(View.VISIBLE); addButton.setVisibility(View.VISIBLE);
                            //ll.removeView(lin);
                        }
                    });
                }
            }
        });
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
                Intent toNextPage = new Intent(ActivityTalentsSignUp.this, ActivityInterestsSignUp.class);
                startActivity(toNextPage);
            }
        }, 1500);

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
                ActivityTalentsSignUp.this.onBackPressed();
            }
        }, 1500);
    }

    public void skipPage(View view) {
        onNextClicked(view);
    }
}
