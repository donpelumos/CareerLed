package com.workchopapp.videoplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.Policy;
import java.util.concurrent.TimeUnit;

/**
 * Created by BALE on 15/09/2016.
 */

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    private Camera myCamera;
    private CameraPreview myCameraSurfaceView;
    private MediaRecorder mediaRecorder;
    private Button startVideoButton,cameraSwitchButton,flashButton,playPauseButton;
    private TextView videoRecordingTimerTextView;
    boolean recording = false;
    private FrameLayout myCameraPreview = null; // this layout contains surfaceview
    private boolean cameraFront = false;
    private boolean isCameraFlashOn;
    private boolean isVideoRecordingPause;
    private int setOrientationHint;
    // Defined CountDownTimer variables
    private  long startVideoRecordingTime=10*1000*60*10;
    private CountDownTimer countDownTimer;
    private long timeElapsed;
    private String saveUrl="/sdcard/myvideo.mp4";
    private Bundle extras=null;
    private boolean isTime,isUrl;
    private String videoDuration;
    private Policy.Parameters p;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        intializeCameraPreview();
        initid();
        checkIntentExtra();
    }
    // Intialize camera preview first time
    private void intializeCameraPreview() {
        myCamera = getCameraInstance();     //Get Camera for preview
        if(myCamera == null){
            Toast.makeText(CameraActivity.this,"Failed to open Camera",Toast.LENGTH_LONG).show();
            finish();
        }
        myCameraSurfaceView = new CameraPreview(this, myCamera);
        myCameraPreview = (FrameLayout)findViewById(R.id.videoview);
        myCameraPreview.addView(myCameraSurfaceView);
    }
    private void initid() {
        // TODO Auto-generated method stub
        startVideoButton = (Button)findViewById(R.id.start_video_button);
        startVideoButton.setOnClickListener(this);
        cameraSwitchButton = (Button)findViewById(R.id.camera_switchbutton);
        cameraSwitchButton.setOnClickListener(this);
        playPauseButton=(Button)findViewById(R.id.pause_play_button);
        playPauseButton.setOnClickListener(this);
        flashButton=(Button)findViewById(R.id.flash_button);
        flashButton.setOnClickListener(this);
        videoRecordingTimerTextView=(TextView)findViewById(R.id.timer_textview);
    }
    public void checkIntentExtra(){ // this function check  intent has extras values or not
        extras=getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("time")) {
                startVideoRecordingTime = (extras.getInt("time")+1)*1000;
                isTime=true;
                // TODO: Do something with the value of isNew.
            }
            if(extras.containsKey("url")){
                saveUrl=extras.getString("url");
                isTime=false;
                isUrl=true;
                Log.d("saveUrl", saveUrl);
            }
        }
        else{
            Log.d("extras is null",extras+"");
            isTime=false;
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.start_video_button){
            startVideoRecording();
        }
        if(v.getId()==R.id.camera_switchbutton){
            switchCamera();
        }
        if(v.getId()==R.id.pause_play_button){
            playPauseVideoRecording();
        }
        if(v.getId()==R.id.flash_button){
            if(isFlashAvailable(this)){
                if(isCameraFlashOn){
                    isCameraFlashOn=false;
                    //flashButton.setBackgroundResource(R.drawable.ic_action_flash_off);
                }else{
                    isCameraFlashOn=true;
                    //flashButton.setBackgroundResource(R.drawable.ic_action_flash_on);
                }
            }else{
                Log.d("flash not available", "flash not here");
            }
        }
    }
    // Choose camera with front and back functionaly
    public void chooseCamera() {
        if (cameraFront) { // if the camera preview is the front
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                // open the backFacingCamera
                // set a picture callback
                // refresh the preview
                Log.d("findBackFacingCamera", cameraId+"");
                myCamera = Camera.open(cameraId); // change switch camera icon image
                myCamera.lock();
                myCamera.setDisplayOrientation(90);
                myCameraSurfaceView.refreshCamera(myCamera);
            }
        }
        else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                // open the backFacingCamera
                // set a picture callback
                // refresh the preview
                Log.d("findFrontFacingCamera", cameraId+"");
                myCamera = Camera.open(cameraId); // change switch camera icon image
                myCamera.lock();
                myCamera.setDisplayOrientation(90);
                myCameraSurfaceView.refreshCamera(myCamera);
            }
        }
    }
    // return cameraPreview Id 1 to open front camera
    private int findFrontFacingCamera() {
        //releaseCamera();
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        Log.v("No OF CAMERAS", String.valueOf(numberOfCameras));
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                setOrientationHint=270;
                cameraFront = true;
                break;
            }
        }
        return cameraId;
    }
    // return cameraPreview Id 0 to  open back camera
    private int findBackFacingCamera() {
        //releaseCamera();
        //releaseMediaRecorder();
        int cameraId = -1;
        // Search for the back facing camera
        int numberOfCameras = Camera.getNumberOfCameras(); // get the number of cameras
        // for every camera check
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                setOrientationHint=90;
                cameraFront = false;
                break;
            }
        }
        return cameraId;
    }
    // return camera instance when activity open first time
    private Camera getCameraInstance(){
        // TODO Auto-generated method stub
        releaseCamera();
        releaseMediaRecorder();
        Camera c = null;
        try {
            c =Camera.open(findBackFacingCamera());
            c.setDisplayOrientation(90);
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
    @SuppressLint("InlinedApi")
    private boolean prepareMediaRecorder(){
        mediaRecorder = new MediaRecorder();
        try{
            if(isCameraFlashOn){  // camera flash on off
                setFlashOnOff(true);
            }
            myCamera.unlock();
        }catch(Exception e){
            //e.printStackTrace();
        }
        mediaRecorder.setCamera(myCamera);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setProfile(CamcorderProfile.get(1, CamcorderProfile.QUALITY_HIGH));
        mediaRecorder.setOutputFile(saveUrl);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setPreviewDisplay(myCameraSurfaceView.getHolder().getSurface());
        mediaRecorder.setOrientationHint(setOrientationHint);
        try {
            mediaRecorder.prepare();
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            releaseMediaRecorder();
            return false;
        }
        return true;
    }
    // Setup video recording timer function and start timer
    public void startVideoRecordingTimer(){
        setupCountDown();
        countDownTimer.start();
    }
    // switch camera to front or rear
    private void switchCamera() {
        if (!recording) {
            int camerasNumber = Camera.getNumberOfCameras();
            if (camerasNumber > 1) {
                releaseCamera(); // release the old camera instance
                chooseCamera(); // switch camera, from the front and the back and vice versa
            } else {
                Log.d("camerasNum is not >", "1");
            }
        }
    }
    // start video recording function
    private void startVideoRecording() {
        // TODO Auto-generated method stub
        // check if video recording already started
        if(recording){
            stopVideoRecording(); // stop recording and release camera
            stopRecordingTimer();
        }else{
            Log.d("preparedMediaRecorder", prepareMediaRecorder()+"");
            if(!prepareMediaRecorder()){
                Toast.makeText(CameraActivity.this,
                        "Fail in prepareMediaRecorder()!\n - Ended -",
                        Toast.LENGTH_LONG).show();
                finish();
            }
            startVideoRecordingTimer();
            //Invisible view after start recording
            cameraSwitchButton.setVisibility(View.INVISIBLE);
            flashButton.setVisibility(View.INVISIBLE);
            mediaRecorder.start();
            recording = true;
        }
    }
    /* this function is  not in working now
     * use to pause or resume video when recording
     */
    private void playPauseVideoRecording() {
        if(isVideoRecordingPause){
            isVideoRecordingPause=false;
            //playPauseButton.setBackgroundResource(R.drawable.ic_play_circle_outline_white_48dp);
            mediaRecorder.reset();
            stopRecordingTimer();
        }else{
            isVideoRecordingPause=true;
            //playPauseButton.setBackgroundResource(R.drawable.ic_pause_circle_outline_white_48dp);
        }
    }
    // stop recording timer when stop video record
    private void stopRecordingTimer(){
        countDownTimer.cancel();
    }
    // set camera flash on off
    private void setFlashOnOff(boolean flash){
        if(flash){
            Camera.Parameters params = myCamera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            myCamera.setParameters(params);
        }else{
            Camera.Parameters params = myCamera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            myCamera.setParameters(params);
        }
    }
    // stop video recording
    public void stopVideoRecording(){
        //Invisible view after start recording
        try{
            cameraSwitchButton.setVisibility(View.VISIBLE);
            flashButton.setVisibility(View.VISIBLE);
            mediaRecorder.stop();   // stop the recording
            releaseMediaRecorder(); // release the MediaRecorder object\
            if(isFlashAvailable(this))
                setFlashOnOff(false);
            releaseCamera();
            recording = false;
            stopRecordingTimer();
        /*
         * intent use here to send back response to activity that start this current activity
         */
            Intent returnIntent = new Intent();
            // check that video save url coming from previous activity or not
            if(!isUrl){
                returnIntent.putExtra("url", saveUrl);
            }
            returnIntent.putExtra("videoDuration", videoDuration);
            setResult(1, returnIntent);
            finish();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        /* this code check video recording continue or not when current activity goes in pause state
         *
         */
        if(recording){
            Log.d("onPause", "OK");
            stopVideoRecording();
        }else{
            Log.d("finishActivity", "ok");
            finish();
        }
        //myCameraSurfaceView.onPauseMySurfaceView();
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // myCameraSurfaceView.onResumeMySurfaceView();
    }
    private void releaseMediaRecorder(){
        if (mediaRecorder != null) {
            mediaRecorder.reset();   // clear recorder configuration
            mediaRecorder.release();  // release the recorder object
            mediaRecorder = null;
            myCamera.lock();       // lock camera for later use
        }
    }
    private void releaseCamera(){
        if (myCamera != null){
            myCamera.release();    // release the camera for other applications
            myCamera = null;
        }
    }
    @Override
    public void onBackPressed()
    {
        if(recording){
            stopVideoRecording();
        }else{
            releaseCamera();
            finish();
        }
        Log.d("onBackPressed","yes");
        // finish();
    }
    // function setup countdown timer for video recording
    private void setupCountDown() {
        Log.d("startVideoRecordingTime", startVideoRecordingTime+"");
        countDownTimer = new CountDownTimer(startVideoRecordingTime, 1000) {
            public void onTick(long millisUntilFinished) {
                // check that video recording time coming previous activity or not
                if(!isTime){
                    // if time not coming from previous activity then time will increase
                    timeElapsed = startVideoRecordingTime - millisUntilFinished;
                    // Get video duration
                    videoDuration=String.format("%d :%d ",
                            TimeUnit.MILLISECONDS.toMinutes(timeElapsed),
                            TimeUnit.MILLISECONDS.toSeconds(timeElapsed) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed)));
                    showRecordingTime(timeElapsed);
                }else{
                    timeElapsed = startVideoRecordingTime - millisUntilFinished;
                    // Get video duration
                    videoDuration=String.format("%d :%d ",
                            TimeUnit.MILLISECONDS.toMinutes(timeElapsed),
                            TimeUnit.MILLISECONDS.toSeconds(timeElapsed) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed)));
                    showRecordingTime(millisUntilFinished);
                }
            }
            public void onFinish() {
                Log.d("finish", "yes");
                stopVideoRecording();
            }
        };
    }
    @SuppressLint("InlinedApi")
    public void showRecordingTime(long time){
        Log.d("startVideoRecordingTime", startVideoRecordingTime+"");
        videoRecordingTimerTextView.setText(""+String.format("%d :%d ",
                TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time))));
    }
    /*
     * @return true if a flash is available, false if not
     */
    public static boolean isFlashAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }
}
