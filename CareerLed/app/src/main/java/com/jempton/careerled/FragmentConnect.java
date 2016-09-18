package com.jempton.careerled;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import adapter.ConnectRow;
import adapter.ConnectRowAdapter;

/**
 * Created by BALE on 28/08/2015.
 */
public class FragmentConnect  extends Fragment implements DialogCertification.NoticeDialogListener{
    LinearLayout tab1,tab2, subTab1, subTab2, tab1Body, tab2Body, containerLayout;
    ImageView tb1,tb2;
    int tabIndex = 1;
    ListView connectList1, connectList2;
    ConnectRow [] menuListData;
    CheckBox checkBox;
    int visibility = 1;
    LinearLayout spinners;
    Button advancedSearch;
    int x1,y1;
    ProgressDialog progress;
    ArrayAdapter<String> a1,a2,a3,a4;
    Spinner s1,s2,s3,s4;
    String [] levels = {"Junior Secondary","Senior Secondary","University/College"};
    String [] areas = {"Arts","Social Science","Science","Technology"};
    String [] fields = {"Pure Sciences","Health Science","Engineering"};
    String [] courses = {"Computer Science","Micro Biology","Petroleum Engineering"};

    DialogOtherProfile newFragment;

    public FragmentConnect(){

    }

    public LinearLayout specialrow(View rootView, final int i){
        final LinearLayout row = new LinearLayout(rootView.getContext());
        row.setClickable(true);
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(0, 1, 0, 0);
        row.setLayoutParams(rowParams);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setBackgroundColor(Color.rgb(240, 240, 240));

        ImageView rowImage = new ImageView(rootView.getContext());
        rowImage.setImageResource(R.drawable.picture02);
        LinearLayout.LayoutParams rowImageParams = new LinearLayout.LayoutParams(70,70);
        rowImageParams.setMargins(10, 10, 10, 10);
        rowImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        rowImage.setLayoutParams(rowImageParams);
        row.addView(rowImage);


        LinearLayout lin = new LinearLayout(rootView.getContext());
        LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lin.setLayoutParams(linParams);
        lin.setGravity(Gravity.CENTER_VERTICAL);
        lin.setOrientation(LinearLayout.VERTICAL);
        TextView fullname = new TextView(rootView.getContext());
        fullname.setText("Surname + Firstname");
        fullname.setTextSize(22);
        lin.addView(fullname);

        LinearLayout sub1 = new LinearLayout(rootView.getContext());
        sub1.setLayoutParams(linParams);

        LinearLayout subLeft = new LinearLayout(rootView.getContext());
        subLeft.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams slParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        slParams.weight = (float)0.5;
        subLeft.setLayoutParams(slParams);
        ImageView im1 = new ImageView(rootView.getContext());
        im1.setImageResource(R.drawable.bullet);
        LinearLayout.LayoutParams imParams = new LinearLayout.LayoutParams(15,15);
        im1.setLayoutParams(imParams);
        imParams.setMargins(0, 0, 10, 0);
        subLeft.addView(im1);
        TextView level = new TextView(rootView.getContext());
        level.setText("Level");
        level.setTextSize(16);
        subLeft.addView(level);
        sub1.addView(subLeft);

        LinearLayout subRight = new LinearLayout(rootView.getContext());
        subRight.setGravity(Gravity.CENTER_VERTICAL);
        subRight.setLayoutParams(slParams);
        ImageView im2 = new ImageView(rootView.getContext());
        im2.setImageResource(R.drawable.bullet2);
        im2.setLayoutParams(imParams);
        imParams.setMargins(0, 0, 10, 0);
        subRight.addView(im2);
        TextView area = new TextView(rootView.getContext());
        area.setText("Area");
        area.setTextSize(16);
        subRight.addView(area);
        sub1.addView(subRight);


        lin.addView(sub1);

        LinearLayout sub2 = new LinearLayout(rootView.getContext());
        sub2.setLayoutParams(linParams);

        LinearLayout subLeft2 = new LinearLayout(rootView.getContext());
        subLeft2.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams slParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        slParams2.weight = (float)0.5;
        subLeft2.setLayoutParams(slParams2);
        ImageView im3 = new ImageView(rootView.getContext());
        im3.setImageResource(R.drawable.bullet3);
        LinearLayout.LayoutParams imParams2 = new LinearLayout.LayoutParams(15,15);
        im3.setLayoutParams(imParams2);
        imParams2.setMargins(0, 0, 10, 0);
        subLeft2.addView(im3);
        TextView field = new TextView(rootView.getContext());
        field.setText("Field");
        field.setTextSize(16);
        subLeft2.addView(field);
        sub2.addView(subLeft2);

        LinearLayout subRight2 = new LinearLayout(rootView.getContext());
        subRight2.setGravity(Gravity.CENTER_VERTICAL);
        subRight2.setLayoutParams(slParams2);
        ImageView im4 = new ImageView(rootView.getContext());
        im4.setImageResource(R.drawable.bullet4);
        im4.setLayoutParams(imParams2);
        imParams2.setMargins(0, 0, 10, 0);
        subRight2.addView(im4);
        TextView course = new TextView(rootView.getContext());
        course.setText("Course");
        course.setTextSize(16);
        subRight2.addView(course);
        sub2.addView(subRight2);


        lin.addView(sub2);
        row.addView(lin);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFragment = new DialogOtherProfile();
                Bundle extras = new Bundle();
                extras.putInt("network", i);
                newFragment.setArguments(extras);
                newFragment.show(getFragmentManager(), "missiles");
                newFragment.setTargetFragment(FragmentConnect.this, 0);
            }
        });
        row.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    x1 = (int)event.getX(); y1 = (int)event.getY();
                    row.setBackgroundColor(Color.argb(155,240, 240, 240));
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            row.setBackgroundColor(Color.argb(255,240,240,240));
                        }
                    }, 500);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    row.setBackgroundColor(Color.argb(255,240, 240, 240));
                }
                return false;
            }
        });
        return row;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_connect, container, false);
        tab1 = (LinearLayout)rootView.findViewById(R.id.tab1);
        tab2 = (LinearLayout)rootView.findViewById(R.id.tab2);
        subTab1 = (LinearLayout)rootView.findViewById(R.id.sub_tab1);
        subTab2 = (LinearLayout)rootView.findViewById(R.id.sub_tab2);
        tb1 = (ImageView)rootView.findViewById(R.id.tab1_img);
        tb2 = (ImageView)rootView.findViewById(R.id.tab2_img);
        spinners = (LinearLayout)rootView.findViewById(R.id.spinners);
        checkBox = (CheckBox)rootView.findViewById(R.id.checked);


        tab1Body = (LinearLayout)rootView.findViewById(R.id.tab1_body);
        tab2Body = (LinearLayout)rootView.findViewById(R.id.tab2_body);
        tab2Body.setVisibility(View.GONE);
        advancedSearch = (Button)rootView.findViewById(R.id.advanced_search);
        advancedSearch.setOnTouchListener(new ButtonHighlighterOnTouchListener(advancedSearch));


        containerLayout = (LinearLayout)rootView.findViewById(R.id.container);

        containerLayout.addView(specialrow(rootView,1));
        containerLayout.addView(specialrow(rootView,1));
        containerLayout.addView(specialrow(rootView,1));
        containerLayout.addView(specialrow(rootView,1));
        containerLayout.addView(specialrow(rootView,1));
        containerLayout.addView(specialrow(rootView,1));
        //containerLayout.addView(row2);
        //connectList1.setBackgroundColor(getResources().getColor(R.color.bg_color));
        //connectList2.setBackgroundColor(getResources().getColor(R.color.bg_color));


/*
        menuListData = new ConnectRow[]{
                new ConnectRow("Surname + Firstname",R.drawable.picture01 ), new ConnectRow("Surname + Firstname",R.drawable.picture02 ),
                new ConnectRow("Surname + Firstname",R.drawable.picture03 ), new ConnectRow("Surname + Firstname",R.drawable.picture04 ),
                new ConnectRow("Surname + Firstname",R.drawable.picture05 ), new ConnectRow("Surname + Firstname",R.drawable.picture06),
                new ConnectRow("Surname + Firstname",R.drawable.picture07)};

                */

        //final ConnectRowAdapter adp = new ConnectRowAdapter(rootView.getContext(),R.layout.row_connect, menuListData );
        //connectList1.setAdapter(adp);
        //connectList2.setAdapter(adp);

        progress = new ProgressDialog(rootView.getContext());
        s1 = (Spinner)rootView.findViewById(R.id.level);
        s2 = (Spinner)rootView.findViewById(R.id.area);
        s3 = (Spinner)rootView.findViewById(R.id.field);
        s4 = (Spinner)rootView.findViewById(R.id.course);
        a1 = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_item,levels);
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a2 = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_item,areas);
                a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                a3 = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_item,fields);
                a3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                a4 = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_item,courses);
                a4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s1.setAdapter(a1);
                s2.setAdapter(a2);
                s3.setAdapter(a3);
                s4.setAdapter(a4);
                s1.setOnItemSelectedListener(new Spinner_Listener());
                s2.setOnItemSelectedListener(new Spinner_Listener());
                s3.setOnItemSelectedListener(new Spinner_Listener());


        advancedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advancedSearch.setBackground(getResources().getDrawable(R.drawable.button_round_bg));
                advancedSearch.setPadding(10, 0, 10, 0);
            }
        });

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabIndex == 2){
                    tab2.setBackgroundColor(Color.rgb(255, 255, 255));
                    tab1.setBackgroundColor(Color.rgb(204, 204, 204));
                    subTab2.setVisibility(View.INVISIBLE);
                    tab2Body.setVisibility(View.GONE);
                    subTab1.setVisibility(View.VISIBLE);
                    tab1Body.setVisibility(View.VISIBLE);
                    containerLayout.removeAllViews();
                    containerLayout.addView(specialrow(rootView, 1));
                    containerLayout.addView(specialrow(rootView, 1));
                    containerLayout.addView(specialrow(rootView, 1));
                    containerLayout.addView(specialrow(rootView,1));
                    containerLayout.addView(specialrow(rootView,1));
                    containerLayout.addView(specialrow(rootView,1));
                    tabIndex = 1;
                }
                else if(tabIndex == 1){

                }
            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabIndex == 2){

                }
                else if(tabIndex == 1){
                    tab1.setBackgroundColor(Color.rgb(255, 255, 255));
                    tab2.setBackgroundColor(Color.rgb(204, 204, 204));
                    subTab1.setVisibility(View.INVISIBLE);
                    tab1Body.setVisibility(View.GONE);
                    subTab2.setVisibility(View.VISIBLE);
                    tab2Body.setVisibility(View.VISIBLE);
                    containerLayout.removeAllViews();
                    containerLayout.addView(specialrow(rootView,0));
                    containerLayout.addView(specialrow(rootView,0));
                    containerLayout.addView(specialrow(rootView,0));
                    containerLayout.addView(specialrow(rootView,0));
                    containerLayout.addView(specialrow(rootView,0));
                    containerLayout.addView(specialrow(rootView,0));
                    tabIndex = 2;
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(visibility == 1){
                    visibility = 0;
                    checkBox.setChecked(false);
                    spinners.setVisibility(View.GONE);
                }
                else if(visibility == 0){
                    visibility = 1;
                    checkBox.setChecked(true);
                    spinners.setVisibility(View.VISIBLE);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String value) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_connect, menu);
    }
}
