package com.workchopapp.a2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    ImageView image, image2, image3, imageOverlay;
    int state = 1;
    FrameLayout overLayCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final float density = getResources().getDisplayMetrics().density;
        overLayCase = (FrameLayout)findViewById(R.id.overlayCase);
        overLayCase.setVisibility(View.GONE);
        image =  (ImageView)findViewById(R.id.image);
        image2 =  (ImageView)findViewById(R.id.image2);
        image3 =  (ImageView)findViewById(R.id.image3);
        imageOverlay = (ImageView)findViewById(R.id.imageOverlay);
        image.setOnTouchListener(new ImageHighlighterOnTouchListener(image));
        image2.setOnTouchListener(new ImageHighlighterOnTouchListener(image2));
        image3.setOnTouchListener(new ImageHighlighterOnTouchListener(image3));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, second.class);
                startActivity(intent);
            }
        }, 4000);

        imageOverlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overLayCase.setVisibility(View.GONE);
                state = 1;
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(state == 1){
                    state = 2;
                    imageOverlay.setImageDrawable(image.getDrawable());
                    overLayCase.setVisibility(View.VISIBLE);
                    //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    //        (int)(250*density));
                    //params.setMargins(0,0,0,0);
                    //image.setLayoutParams(params);
                }
                else{
                    state = 1;
                    overLayCase.setVisibility(View.GONE);
                    //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    //        (int)(200*density));
                    //params.setMargins((int)(70*density),0,0,0);
                    //image.setLayoutParams(params);
                }

            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(state == 1){
                    state = 2;
                    imageOverlay.setImageDrawable(image2.getDrawable());
                    overLayCase.setVisibility(View.VISIBLE);
                }
                else{
                    state = 1;
                    overLayCase.setVisibility(View.GONE);
                }

            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(state == 1){
                    state = 2;
                    imageOverlay.setImageDrawable(image3.getDrawable());
                    overLayCase.setVisibility(View.VISIBLE);
                }
                else{
                    state = 1;
                    overLayCase.setVisibility(View.GONE);
                }

            }

        });
    }

    private class ImageHighlighterOnTouchListener implements View.OnTouchListener {
        //This
        final ImageView imageButton;

        public ImageHighlighterOnTouchListener(final ImageView imageButton) {
            super();
            this.imageButton = imageButton;
        }

        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //grey color filter, you can change the color as you like
                imageButton.setAlpha((float)0.6);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                imageButton.setAlpha((float)1.0);
            }
            return false;
        }

    }
}
