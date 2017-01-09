package com.workchopapp.videoplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by BALE on 15/09/2016.
 */

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private Button openCamera;
    private int requestCodeForCamera=1;
    private TextView timerTextView,videoDurationTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        openCamera=(Button)findViewById(R.id.button);
        openCamera.setOnClickListener(this);
        timerTextView=(TextView)findViewById(R.id.timer);
        videoDurationTextView=(TextView)findViewById(R.id.videoDurationTextView);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent intent=new Intent(MainActivity2.this,CameraActivity.class);
        //intent.putExtra("time", 15);
        //intent.putExtra("url", "/sdcard/recordingvideo.mp4");
        startActivityForResult(intent, requestCodeForCamera);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCodeForCamera==requestCode){
            try{
                Bundle extras=data.getExtras();
                if(extras!=null){
                    if(extras.containsKey("url")){
                        timerTextView.setText(extras.getString("url").toString());
                    }
                    if(extras.containsKey("videoDuration")){
                        timerTextView.setText(extras.getString("videoDuration").toString());
                    }
                }else{
                    timerTextView.setText("not url");
                }
            }catch(Exception e){
            }
        }
    }
}
