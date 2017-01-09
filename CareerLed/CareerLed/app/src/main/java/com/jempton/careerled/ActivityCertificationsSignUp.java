package com.jempton.careerled;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityCertificationsSignUp extends ActionBarActivity implements DialogCertification.NoticeDialogListener {
    Dialog dialog;
    ProgressDialog progress;
    Spinner chooseCertificate;
    ImageView certificateImage;
    Button addButton;
    DialogFragment newFragment;
    public int index=0;
    LinearLayout certificationSlot;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications_sign_up);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        addButton = (Button)findViewById(R.id.add_button);


        addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnTouchListener(new ButtonHighlighterOnTouchListener(addButton));
        dialog = new Dialog(getApplicationContext());
        progress = new ProgressDialog(this);



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFragment = new DialogCertification();
                newFragment.show(getFragmentManager(), "missiles");
            }
        });

        certificationSlot = (LinearLayout)findViewById(R.id.certification_slot);

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String values) {
        // Use this instance of the interface to deliver action events
        Toast.makeText(ActivityCertificationsSignUp.this, values, Toast.LENGTH_SHORT).show();
        final LinearLayout lin = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 5, 0);
        lin.setOrientation(LinearLayout.HORIZONTAL);
        lp.gravity = Gravity.CENTER_VERTICAL;
        lin.setLayoutParams(lp);
        final ImageView certificationImage = new ImageView(getApplicationContext());
        certificationImage.setImageResource(R.drawable.waec);
        LinearLayout.LayoutParams imp = new LinearLayout.LayoutParams(70,70);
        certificationImage.setLayoutParams(imp);
        lin.addView(certificationImage);

        LinearLayout sub_lin = new LinearLayout(getApplicationContext());
        sub_lin.setOrientation(LinearLayout.VERTICAL);
        sub_lin.setLayoutParams(lp);
        TextView tt1 = new TextView(getApplicationContext());
        TextView tt2 = new TextView(getApplicationContext());
        tt1.setText("Certification Name"); tt2.setText("Certification Authority");
        tt1.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL));
        tt1.setTextSize(16);
        tt2.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL));
        tt2.setTextSize(16);
        sub_lin.addView(tt1);
        sub_lin.addView(tt2);


        final ImageButton delete_button = new ImageButton(getApplicationContext()); //delete_button.setText("Delete");
        delete_button.setImageResource(R.drawable.blue_cancel);
        delete_button.setBackgroundColor(Color.argb(255, 51, 109, 183));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(50, 50);
        delete_button.setLayoutParams(params);
        lin.addView(sub_lin);
        lin.addView(delete_button);


        delete_button.setId(index);
        lin.setId(delete_button.getId() +1);

        index = index + 1;
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View vv = (ViewGroup) findViewById(delete_button.getId() + 1);

                certificationSlot.removeView(vv);

            }
        });
        certificationSlot.addView(lin);



    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

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
                Intent toNextPage = new Intent(ActivityCertificationsSignUp.this, ActivityProfileSignUp.class);
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
                ActivityCertificationsSignUp.this.onBackPressed();
            }
        }, 1500);

    }

    public void skipPage(View view)
    {
        onNextClicked(view);
    }

}
