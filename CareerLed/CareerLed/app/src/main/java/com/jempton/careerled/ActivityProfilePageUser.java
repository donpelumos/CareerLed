package com.jempton.careerled;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
import android.widget.EditText;
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
 * Created by BALE on 19/09/2015.
 */
public class ActivityProfilePageUser extends ActionBarActivity implements DialogEditSkill.NoticeDialogListener {
    ImageView profilePic, coverPic;
    EditText tag1, tag2, tag3, tag4, bio;
    ImageView subEdit1,subEdit2,subEdit3,subEdit4;
    Button editProfile;
    Button addButton;
    RoundedImage roundedImage;
    ImageView oldProfilePic;
    ScrollView scroller;
    FrameLayout swiper;
    LinearLayout subSwiper;
    FragmentManager fm;
    int editable = 0;

    int y1 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage_user);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//This hides the automatic keyboard when activity starts
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fm = getFragmentManager();

        editProfile = (Button)findViewById(R.id.edit_profile);

        profilePic = (ImageView)findViewById(R.id.profile_pic);
        coverPic = (ImageView)findViewById(R.id.cover_pic);
        oldProfilePic = profilePic;
        subEdit1 = (ImageView)findViewById(R.id.sub_edit1);
        subEdit2 = (ImageView)findViewById(R.id.sub_edit2);
        subEdit3 = (ImageView)findViewById(R.id.sub_edit3);
        subEdit4 = (ImageView)findViewById(R.id.sub_edit4);
        subEdit1.setClickable(true);
        subEdit2.setClickable(true);
        subEdit3.setClickable(true);
        subEdit4.setClickable(true);
        subEdit1.setOnTouchListener(new ImageHighlighterOnTouchListener(subEdit1));
        subEdit2.setOnTouchListener(new ImageHighlighterOnTouchListener(subEdit2));
        subEdit3.setOnTouchListener(new ImageHighlighterOnTouchListener(subEdit3));
        subEdit4.setOnTouchListener(new ImageHighlighterOnTouchListener(subEdit4));
        tag1 = (EditText)findViewById(R.id.tag1);
        tag2 = (EditText)findViewById(R.id.tag2);
        tag3 = (EditText)findViewById(R.id.tag3);
        tag4 = (EditText)findViewById(R.id.tag4);
        bio = (EditText)findViewById(R.id.bio);
        subEdit1.setVisibility(View.INVISIBLE);
        subEdit2.setVisibility(View.INVISIBLE);
        subEdit3.setVisibility(View.INVISIBLE);
        subEdit4.setVisibility(View.INVISIBLE);


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

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editable == 1) {
                    editProfile.setBackground(getResources().getDrawable(R.drawable.button_round_bg));
                    editProfile.setPadding(8, 8, 8, 8);
                    editProfile.setText("Edit Profile");
                    editable = 0;
                    subEdit1.setVisibility(View.INVISIBLE);
                    subEdit2.setVisibility(View.INVISIBLE);
                    subEdit3.setVisibility(View.INVISIBLE);
                    subEdit4.setVisibility(View.INVISIBLE);
                    tag1.setEnabled(false);
                    tag2.setEnabled(false);
                    tag3.setEnabled(false);
                    tag4.setEnabled(false);
                    bio.setEnabled(false);
                } else if (editable == 0) {
                    editProfile.setBackground(getResources().getDrawable(R.drawable.button_round_bg_red));
                    editProfile.setPadding(8, 8, 8, 8);
                    editable = 1;
                    editProfile.setText("Done");
                    subEdit1.setVisibility(View.VISIBLE);
                    subEdit2.setVisibility(View.VISIBLE);
                    subEdit3.setVisibility(View.VISIBLE);
                    subEdit4.setVisibility(View.VISIBLE);
                    tag1.setEnabled(true);
                    tag2.setEnabled(true);
                    tag3.setEnabled(true);
                    tag4.setEnabled(true);
                    bio.setEnabled(true);
                }

            }
        });

        coverPic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        y1 = (int) event.getY();
                        Toast.makeText(ActivityProfilePageUser.this, "left2right swipe " + String.valueOf(y1) + " ",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        float y2 = (int) event.getY();
                        float deltaY = y2 - y1;
                        if (deltaY < -10) {
                            Toast.makeText(ActivityProfilePageUser.this, "left2right swipe " + String.valueOf(y1) + " " + String.valueOf(y2),
                                    Toast.LENGTH_SHORT).show();
                            swiper.setVisibility(View.GONE);
                            subSwiper.setVisibility(View.VISIBLE);
                        } else {
                            // consider as something else - a screen tap for example
                            Toast.makeText(ActivityProfilePageUser.this, "left2right swipe " + String.valueOf(y1) + " " + String.valueOf(y2),
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return false;
            }
        });

        subEdit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditSkill newFragment = new DialogEditSkill("Hobbies");
                newFragment.show(fm, "dialog");
            }
        });
        subEdit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditSkill newFragment = new DialogEditSkill("Interests");
                newFragment.show(fm, "dialog");
            }
        });
        subEdit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditSkill newFragment = new DialogEditSkill("Talents/Skills");
                newFragment.show(fm, "dialog");
            }
        });
        subEdit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditSkill newFragment = new DialogEditSkill("Certifications");
                newFragment.show(fm, "dialog");
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
                        Toast.makeText(ActivityProfilePageUser.this, "left2right swipe " + String.valueOf(y1) + " ",
                                Toast.LENGTH_SHORT).show();
                        swiper.setVisibility(View.VISIBLE);
                        subSwiper.setVisibility(View.GONE);
                        break;
                    case MotionEvent.ACTION_UP:
                        float y2 = (int) event.getY();
                        float deltaY = y2 - y1;
                        if (deltaY > 10) {
                            Toast.makeText(ActivityProfilePageUser.this, "left2right swipe " + String.valueOf(y1) + " " + String.valueOf(y2),
                                    Toast.LENGTH_SHORT).show();
                            swiper.setVisibility(View.VISIBLE);
                            subSwiper.setVisibility(View.GONE);
                        } else {
                            // consider as something else - a screen tap for example
                            Toast.makeText(ActivityProfilePageUser.this, "left2right swipe " + String.valueOf(y1) + " " + String.valueOf(y2),
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
                Intent intent = new Intent(ActivityProfilePageUser.this, ActivityImageShower.class);
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
                Intent intent = new Intent(ActivityProfilePageUser.this, ActivityImageShower.class);
                intent.putExtra("url", profilePicPath);
                startActivity(intent);

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
        Paint p = new Paint();
        p.setColor(Color.RED);
        Rect r = new Rect(20,20,20,20);

        canvas.drawRect(r, p);

        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());

        drawable.draw(canvas);
        return bitmap;
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String value) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
