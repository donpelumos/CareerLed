package com.jempton.careerled;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by BALE on 07/09/2015.
 */
public class ActivityProfilePageOther extends ActionBarActivity implements DialogMessageCompose.NoticeDialogListener {
    ImageView profilePic, coverPic, messageIcon;
    Button addButton;
    RoundedImage roundedImage;
    ImageView oldProfilePic;
    ScrollView scroller;
    FrameLayout swiper;
    LinearLayout subSwiper;
    FragmentManager fm;

    int y1 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage_other);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//This hides the automatic keyboard when activity starts
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fm = getFragmentManager();

        messageIcon = (ImageView)findViewById(R.id.message_icon);
        profilePic = (ImageView)findViewById(R.id.profile_pic);
        coverPic = (ImageView)findViewById(R.id.cover_pic);
        oldProfilePic = profilePic;
        messageIcon.setOnTouchListener(new ImageHighlighterOnTouchListener(messageIcon));



        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        drawableToBitmap(coverPic.getDrawable()).compress(Bitmap.CompressFormat.JPEG, 40, bytes);

        ByteArrayOutputStream bytes2 = new ByteArrayOutputStream();
        drawableToBitmap(oldProfilePic.getDrawable()).compress(Bitmap.CompressFormat.JPEG, 40, bytes2);
        //Log.v("V",bytes.toString());
        TextView tt = (TextView)findViewById(R.id.bio);
        //tt.setText(Environment.getExternalStorageDirectory() + File.separator + "test.jpg");
        final String coverPicPath = Environment.getExternalStorageDirectory() + File.separator + "test.jpg";
        final String profilePicPath = Environment.getExternalStorageDirectory() + File.separator + "test2.jpg";

        File f = new File(Environment.getExternalStorageDirectory() + File.separator + "test.jpg");
        File f2 = new File(Environment.getExternalStorageDirectory() + File.separator + "test2.jpg");
        try {
            f.createNewFile();
            f2.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            FileOutputStream fo2 = new FileOutputStream(f2);
            fo.write(bytes.toByteArray());
            fo.close();
            fo2.write(bytes2.toByteArray());
            fo2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bm = drawableToBitmap(profilePic.getDrawable());
        roundedImage = new RoundedImage(bm);
        profilePic.setImageDrawable(roundedImage);

        scroller = (ScrollView)findViewById(R.id.scroller);
        swiper = (FrameLayout)findViewById(R.id.swiper);
        subSwiper = (LinearLayout)findViewById(R.id.sub_swiper);
        subSwiper.setVisibility(View.GONE);

        coverPic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        y1 = (int) event.getY();
                        Toast.makeText(ActivityProfilePageOther.this, "left2right swipe " + String.valueOf(y1) + " ",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        float y2 = (int) event.getY();
                        float deltaY = y2 - y1;
                        if (deltaY < -10) {
                            Toast.makeText(ActivityProfilePageOther.this, "left2right swipe " + String.valueOf(y1) + " " + String.valueOf(y2),
                                    Toast.LENGTH_SHORT).show();
                            swiper.setVisibility(View.GONE);
                            subSwiper.setVisibility(View.VISIBLE);
                        } else {
                            // consider as something else - a screen tap for example
                            Toast.makeText(ActivityProfilePageOther.this, "left2right swipe " + String.valueOf(y1) + " " + String.valueOf(y2),
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return false;
            }
        });

        subSwiper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swiper.setVisibility(View.VISIBLE);
                subSwiper.setVisibility(View.GONE);
            }
        });
        subSwiper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        y1 = (int) event.getY();
                        Toast.makeText(ActivityProfilePageOther.this, "left2right swipe " + String.valueOf(y1) + " ",
                                Toast.LENGTH_SHORT).show();
                        swiper.setVisibility(View.VISIBLE);
                        subSwiper.setVisibility(View.GONE);
                        break;
                    case MotionEvent.ACTION_UP:
                        float y2 = (int) event.getY();
                        float deltaY = y2 - y1;
                        if (deltaY > 10) {
                            Toast.makeText(ActivityProfilePageOther.this, "left2right swipe " + String.valueOf(y1) + " " + String.valueOf(y2),
                                    Toast.LENGTH_SHORT).show();
                            swiper.setVisibility(View.VISIBLE);
                            subSwiper.setVisibility(View.GONE);
                        } else {
                            // consider as something else - a screen tap for example
                            Toast.makeText(ActivityProfilePageOther.this, "left2right swipe " + String.valueOf(y1) + " " + String.valueOf(y2),
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return false;
            }
        });

        coverPic.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                coverPic.setVisibility(View.INVISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        coverPic.setVisibility(View.VISIBLE);
                    }
                }, 100);
                Intent intent = new Intent(ActivityProfilePageOther.this, ActivityImageShower.class);
                intent.putExtra("url", coverPicPath);
                startActivity(intent);
                return false;
            }
        });
        coverPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                coverPic.setVisibility(View.INVISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        coverPic.setVisibility(View.VISIBLE);
                    }
                }, 100);
                Intent intent = new Intent(ProfilePageOther.this, ImageShower.class);
                intent.putExtra("url", coverPicPath);
                startActivity(intent);*/
            }
        });

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePic.setVisibility(View.INVISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        profilePic.setVisibility(View.VISIBLE);
                    }
                }, 100);
                Intent intent = new Intent(ActivityProfilePageOther.this, ActivityImageShower.class);
                intent.putExtra("url", profilePicPath);
                startActivity(intent);

            }
        });

        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogMessageCompose newFragment = new DialogMessageCompose();
                newFragment.show(fm, "dialog");
            }
        });

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

    public void onPreviousClicked(View view)
    {
        ActivityProfilePageOther.this.onBackPressed();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String value) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogClose(DialogFragment dialog) {
        DialogFragment df = (DialogFragment)fm.findFragmentByTag("dialog");
        df.dismiss();
    }
}
