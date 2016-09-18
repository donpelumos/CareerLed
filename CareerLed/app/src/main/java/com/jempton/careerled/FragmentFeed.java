package com.jempton.careerled;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by BALE on 25/08/2015.
 */
public class FragmentFeed extends Fragment {
    ScrollView baseScroll;
    ListView optionsList;
    View rootView;
    Point size;
    Display display;
    LinearLayout wholeCase;
    int screenWidth;
    int index = 1;
    int screenHeight;
    int currentImage;
    public FragmentFeed(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        baseScroll = (ScrollView)rootView.findViewById(R.id.base_scroll);
        size = new Point();
        WindowManager wm = (WindowManager)rootView.getContext().getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        int [] images={R.drawable.picture,R.drawable.picture01,R.drawable.picture02,R.drawable.picture03,R.drawable.picture04,
                R.drawable.picture05,R.drawable.picture06,R.drawable.picture07};
        wholeCase = (LinearLayout)rootView.findViewById(R.id.whole_case);
        for(int i=0; i<7; i++) {
            currentImage = images[i];
            index = i;
            Random r = new Random();
            int showImage = r.nextInt(2);
            wholeCase.addView(addPostFrame(showImage));
        }


        return rootView;
    }

    public FrameLayout addPostFrame(int a){
        FrameLayout postFrame = new FrameLayout(rootView.getContext());
        LinearLayout.LayoutParams postFrameParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        postFrame.setLayoutParams(postFrameParams);
        postFrame.addView(addPostCase(a));
        return postFrame;
    }

    public LinearLayout addPostCase(int a){
        LinearLayout postCase = new LinearLayout(rootView.getContext());
        postCase.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams postCaseParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        postCase.setLayoutParams(postCaseParams);
        postCaseParams.setMargins(0, 0, 0, 0);
        postCase.setBackgroundColor(Color.argb(0, 0, 0, 0));

        postCase.addView(addHeading());
        postCase.addView(addPostBody(a));
        postCase.addView(addUtilityBar());
        postCase.addView(addComments(a));
        postCase.addView(addSpace());
        return postCase;
    }

    public LinearLayout addHeading() {
        LinearLayout heading = new LinearLayout(rootView.getContext());
        heading.setOrientation(LinearLayout.HORIZONTAL);
        heading.setBackgroundColor(Color.WHITE);
        heading.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams headingParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        headingParams.setMargins(0, 1, 0, 0);
        heading.setLayoutParams(headingParams);
        final ImageView personImage = new ImageView(rootView.getContext());
        LinearLayout.LayoutParams personImageParams = null;
        if(screenHeight > 1000){
            float dimension = getResources().getDisplayMetrics().density * 50;
            personImageParams = new LinearLayout.LayoutParams((int)dimension,(int)dimension);
        }
        else if(screenHeight > 1400){
            personImageParams = new LinearLayout.LayoutParams(80,80);
        }
        else{
            personImageParams = new LinearLayout.LayoutParams(60,60);
        }

        personImageParams.setMargins(0, 0, 0, 0);
        personImage.setLayoutParams(personImageParams);
        personImage.setImageResource(R.drawable.ic_pages);

        LinearLayout subHeading = new LinearLayout(rootView.getContext());
        subHeading.setOrientation(LinearLayout.VERTICAL);
        subHeading.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams subHeadingParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        subHeadingParams.setMargins(3, 3, 3, 3);
        subHeading.setLayoutParams(subHeadingParams);
        TextView subHeadingText = new TextView(rootView.getContext());
        subHeadingText.setText("Firstname and action performed");
        subHeadingText.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        subHeadingText.setTextSize(14);

        TextView subHeadingTextDate = new TextView(rootView.getContext());
        subHeadingTextDate.setText("Date -- Time");
        subHeadingTextDate.setTextSize(12);
        if(screenWidth > 800){
            subHeadingText.setTextSize(16);
            subHeadingTextDate.setTextSize(14);
        }else{
            subHeadingText.setTextSize(14);
            subHeadingTextDate.setTextSize(12);
        }
        subHeadingTextDate.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);

        subHeading.addView(subHeadingText);
        subHeading.addView(subHeadingTextDate);

        heading.addView(personImage);
        heading.addView(subHeading);



        return heading;
    }

    public LinearLayout addPostBody(int a){


        LinearLayout postBody = new LinearLayout(rootView.getContext());
        postBody.setPadding(0, 0, 0, 0);
        postBody.setOrientation(LinearLayout.VERTICAL);
        postBody.setBackgroundColor(Color.rgb(212, 212, 212));
        postBody.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams postBodyParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        postBodyParams.setMargins(0, 2, 0, 0);
        postBody.setLayoutParams(postBodyParams);
        TextView bodyText = new TextView(rootView.getContext());
        LinearLayout.LayoutParams bodyTextParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        bodyText.setLayoutParams(bodyTextParams);
        bodyText.setBackgroundColor(Color.WHITE);
        bodyText.setPadding(10, 10, 10, 10);
        bodyTextParams.setMargins(0, 0, 0, 0);
        bodyText.setText("Detail of sample post of any user. More information can be contained here." + String.valueOf(screenWidth) +
                " " + String.valueOf(screenHeight));
        bodyText.setTextSize(16);
        bodyText.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);

        final ImageView postImage = new ImageView(rootView.getContext());
        //postImage.setImageDrawable(getResources().getDrawable(R.drawable.picture01));
        postImage.setId(index);
        //postImage.setImageResource(currentImage);
        postImage.setImageResource(R.drawable.ic_launcher);
        postImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        drawableToBitmap(postImage.getDrawable()).compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File f = new File(Environment.getExternalStorageDirectory()+File.separator+String.valueOf(index)+"Index.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinearLayout.LayoutParams postImageParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,300);
        if(screenHeight > 600){
            postImageParams = new
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,400);
        }
        postImage.setLayoutParams(postImageParams);
        postImageParams.setMargins(15, 0, 15, 0);

        postBody.addView(bodyText);
        if(a == 0){

        }
        else{
            postBody.addView(postImage);
        }


        postImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String profilePicPath = Environment.getExternalStorageDirectory() + File.separator + String.valueOf(postImage.getId()) + "Index.jpg";
                Intent intent = new Intent(rootView.getContext(), ActivityImageShower.class);
                intent.putExtra("url", profilePicPath);
                startActivity(intent);
            }
        });
        return postBody;
    }

    public LinearLayout addUtilityBar(){
        LinearLayout utilityBar = new LinearLayout(rootView.getContext());
        LinearLayout.LayoutParams utilityBarParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        utilityBarParams.setMargins(0,2,0,0);
        utilityBar.setLayoutParams(utilityBarParams);
        utilityBar.setBackgroundColor(Color.argb(0,0,0,0));
        utilityBar.setOrientation(LinearLayout.HORIZONTAL);

        final LinearLayout util1 = new LinearLayout(rootView.getContext());
        LinearLayout.LayoutParams util1Params = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        util1Params.weight = (float)0.33;
        util1.setLayoutParams(util1Params);
        util1.setOrientation(LinearLayout.HORIZONTAL);
        util1.setGravity(Gravity.CENTER_VERTICAL);
        util1.setPadding(5, 8, 5, 8);
        util1.setBackgroundColor(Color.WHITE);
        util1Params.setMargins(0, 0, 1, 0);
        util1.setGravity(Gravity.CENTER);

        final ImageView likesImage = new ImageView(rootView.getContext());
        likesImage.setImageResource(R.drawable.not_liked_icon);
        LinearLayout.LayoutParams likesImageParams = new LinearLayout.LayoutParams(32,32);
        likesImageParams.setMargins(0, 0, 0, 0);
        likesImage.setLayoutParams(likesImageParams);
        util1.setId(index + 200);
        util1.setTag("unliked");
        likesImage.setId(index+1000);
        likesImage.setTag("unliked");

        final TextView likesText =  new TextView(rootView.getContext());
        LinearLayout.LayoutParams likesTextParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        likesText.setGravity(Gravity.CENTER);
        likesTextParams.setMargins(0, 0, 0, 0);
        likesText.setLayoutParams(likesTextParams);
        likesText.setTextSize(14);
        likesText.setTextColor(getResources().getColor(R.color.bg_color));
        likesText.setText("Like");
        likesText.setId(index+500);
        likesText.setTag("unliked");

        TextView likesCount =  new TextView(rootView.getContext());
        LinearLayout.LayoutParams likesCountParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        likesCount.setBackgroundColor(Color.rgb(232, 232, 232));
        likesCount.setTextColor(getResources().getColor(R.color.bg_color));
        likesCount.setLayoutParams(likesCountParams);
        likesCount.setText("23");
        likesCount.setPadding(2, 2, 2, 2);
        likesCountParams.setMargins(4, 0, 0, 0);


        util1.addView(likesImage);
        util1.addView(likesText);
        util1.addView(likesCount);


        final LinearLayout util2 = new LinearLayout(rootView.getContext());
        util2.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams util2Params = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        util2Params.weight = (float)0.33;
        util2Params.setMargins(0, 0, 0, 0);
        util2.setLayoutParams(util2Params);
        util2.setGravity(Gravity.CENTER_VERTICAL);
        util2.setPadding(5, 8, 5, 8);
        util2.setBackgroundColor(Color.WHITE);
        util2Params.setMargins(0, 0, 1, 0);
        util2.setGravity(Gravity.CENTER);

        ImageView commentImage = new ImageView(rootView.getContext());
        commentImage.setImageResource(R.drawable.comment_icon);
        LinearLayout.LayoutParams commentImageParams = new LinearLayout.LayoutParams(32,32);
        commentImageParams.setMargins(0,0,0,0);
        commentImage.setLayoutParams(likesImageParams);

        final TextView commentText =  new TextView(rootView.getContext());
        LinearLayout.LayoutParams commentTextParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        commentText.setGravity(Gravity.CENTER);
        commentTextParams.setMargins(4,0,0,0);
        commentText.setLayoutParams(likesTextParams);
        commentText.setTextSize(14);
        commentText.setTextColor(getResources().getColor(R.color.bg_color));
        commentText.setText("Comment");

        TextView commentCount =  new TextView(rootView.getContext());
        LinearLayout.LayoutParams commentCountParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        commentCount.setBackgroundColor(Color.rgb(232, 232, 232));
        commentCount.setTextColor(getResources().getColor(R.color.bg_color));
        commentCount.setLayoutParams(likesCountParams);
        commentCount.setText("23");
        commentCount.setPadding(2, 2, 2, 2);
        commentCountParams.setMargins(4, 0, 0, 0);

        util2.addView(commentImage);
        util2.addView(commentText);
        util2.addView(commentCount);


        final LinearLayout util3 = new LinearLayout(rootView.getContext());
        util3.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams util3Params = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        util3Params.weight = (float)0.33;
        util3Params.setMargins(0,0,0,0);
        util3.setLayoutParams(util3Params);
        util3.setGravity(Gravity.CENTER_VERTICAL);
        util3.setPadding(5, 8, 5, 8);
        util3.setBackgroundColor(Color.WHITE);
        util3.setGravity(Gravity.CENTER);

        ImageView repostImage = new ImageView(rootView.getContext());
        repostImage.setImageResource(R.drawable.repost_icon);
        LinearLayout.LayoutParams repostImageParams = new LinearLayout.LayoutParams(32,32);
        repostImageParams.setMargins(0,0,0,0);
        repostImage.setLayoutParams(likesImageParams);

        TextView repostText =  new TextView(rootView.getContext());
        LinearLayout.LayoutParams repostTextParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        repostText.setGravity(Gravity.CENTER);
        repostTextParams.setMargins(4,0,0,0);
        repostText.setLayoutParams(likesTextParams);
        repostText.setTextSize(14);
        repostText.setTextColor(getResources().getColor(R.color.bg_color));
        repostText.setText("Repost");

        TextView repostCount =  new TextView(rootView.getContext());
        LinearLayout.LayoutParams repostCountParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        repostCount.setBackgroundColor(Color.rgb(232, 232, 232));
        repostCount.setTextColor(getResources().getColor(R.color.bg_color));
        repostCount.setLayoutParams(likesCountParams);
        repostCount.setText("23");
        repostCount.setPadding(2, 2, 2, 2);
        repostCountParams.setMargins(4, 0, 0, 0);

        util1.setClickable(true);
        util2.setClickable(true);
        util3.setClickable(true);
        likesText.setClickable(true);
        //commentText.setClickable(true);
        //repostText.setClickable(true);

        util3.addView(repostImage);
        util3.addView(repostText);
        util3.addView(repostCount);

        if(screenHeight > 600){
            util1.setPadding(5,10,5,10);
            likesText.setTextSize(14);
            util2.setPadding(5,10,5,10);
            commentText.setTextSize(14);
            util3.setPadding(5,10,5,10);
            repostText.setTextSize(14);
        }

        likesText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    util1.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            util1.setVisibility(View.VISIBLE);
                        }
                    }, 200);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    util1.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });
        commentText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    util2.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            util2.setVisibility(View.VISIBLE);
                        }
                    }, 200);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    util2.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });
        repostText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    util3.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            util3.setVisibility(View.VISIBLE);
                        }
                    }, 200);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    util3.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });
        util1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    util1.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            util1.setVisibility(View.VISIBLE);
                        }
                    }, 200);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    util1.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });
        util2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    util2.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            util2.setVisibility(View.VISIBLE);
                        }
                    }, 200);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    util2.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });
        util3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    util3.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            util3.setVisibility(View.VISIBLE);
                        }
                    }, 200);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    util3.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });

        likesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likesImage.getTag().equals("unliked")) {
                    likesImage.setImageResource(R.drawable.likes_icon);
                    likesImage.setTag("liked");
                    LinearLayout.LayoutParams newParams = new LinearLayout.LayoutParams(
                            35, 35);
                    likesImage.setLayoutParams(newParams);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            LinearLayout.LayoutParams newParams = new LinearLayout.LayoutParams(
                                    32, 32);
                            likesImage.setLayoutParams(newParams);
                        }
                    }, 100);
                } else {
                    likesImage.setTag("unliked");
                    likesImage.setImageResource(R.drawable.not_liked_icon);
                }
            }
        });

        likesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView v2 = (ImageView)rootView.findViewById(likesText.getId()+500);
                if(v2.getTag().equals("unliked")){
                    v2.setImageResource(R.drawable.likes_icon);
                    v2.setTag("liked");
                }
                else{
                    v2.setImageResource(R.drawable.not_liked_icon);
                    v2.setTag("unliked");
                }

            }
        });

        util1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView v2 = (ImageView)rootView.findViewById(util1.getId()+800);
                if(v2.getTag().equals("unliked")){
                    v2.setImageResource(R.drawable.likes_icon);
                    v2.setTag("liked");
                }
                else{
                    v2.setImageResource(R.drawable.not_liked_icon);
                    v2.setTag("unliked");
                }

            }
        });

        util2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogComments newFragment = new DialogComments();
                newFragment.show(getFragmentManager(), "missiles");
                newFragment.setTargetFragment(FragmentFeed.this, 0);
            }
        });

        utilityBar.addView(util1);
        utilityBar.addView(util2);
        utilityBar.addView(util3);


        return utilityBar;
    }

    public Space addSpace(){
        Space space = new Space(rootView.getContext());
        LinearLayout.LayoutParams spaceParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,15);
        space.setLayoutParams(spaceParams);
        return space;
    }

    public LinearLayout addComments(int a){
        LinearLayout comment = new LinearLayout(rootView.getContext());
        comment.setPadding(0,0,0,0);
        comment.setOrientation(LinearLayout.VERTICAL);
        comment.setBackgroundColor(Color.rgb(255,255,255));
        comment.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams commentParams = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        commentParams.setMargins(0, 2, 0, 0);
        comment.setLayoutParams(commentParams);
        if(a < 1){
            for(int i=0; i<3; i++){
                LinearLayout commentRow = new LinearLayout(rootView.getContext());
                commentRow.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams commentRowParams = new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                commentRowParams.setMargins(5,5,0,5);
                commentRow.setLayoutParams(commentRowParams);
                commentRow.setGravity(Gravity.CENTER_VERTICAL);
                ImageView commentImage = new ImageView(rootView.getContext());
                LinearLayout.LayoutParams commentImageParams = new
                        LinearLayout.LayoutParams(50,50);
                commentImageParams.setMargins(0,0,5,0);
                commentImage.setLayoutParams(commentImageParams);
                commentImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                commentImage.setImageResource(R.drawable.picture01);
                LinearLayout commentSubRow = new LinearLayout(rootView.getContext());
                commentSubRow.setOrientation(LinearLayout.VERTICAL);

                TextView commenter = new TextView(rootView.getContext());
                LinearLayout.LayoutParams bodyTextParams = new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                commenter.setLayoutParams(bodyTextParams);
                commenter.setText("Surname Firstname");
                commenter.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                commenter.setGravity(Gravity.CENTER_VERTICAL);
                commenter.setTextSize(13);

                TextView commentDetails = new TextView(rootView.getContext());
                LinearLayout.LayoutParams commentDetailsParams = new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                commentDetails.setLayoutParams(commentDetailsParams);
                commentDetails.setTextSize(13);
                commentDetails.setGravity(Gravity.CENTER_VERTICAL);
                commentDetails.setText("Comment made by the user posted here.");

                TextView commentDate = new TextView(rootView.getContext());
                LinearLayout.LayoutParams commentDateParams = new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                commentDate.setLayoutParams(commentDateParams);
                commentDate.setTextSize(12);
                commentDate.setGravity(Gravity.CENTER_VERTICAL);
                commentDate.setText("Date -- Time");

                commentSubRow.addView(commenter);
                commentSubRow.addView(commentDetails);
                commentSubRow.addView(commentDate);

                commentRow.addView(commentImage);
                commentRow.addView(commentSubRow);

                comment.addView(commentRow);
            }
        }
        else{

        }
        return comment;
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

}
