package com.jempton.careerled;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class ActivityProfileSignUp extends ActionBarActivity {
    ProgressDialog progress;
    Button addProfilePic, addCoverPic;
    ImageView profilePic,coverPic;
    TextView profilePictureInput, coverPictureInput;
    RoundedImage roundedImage, roundedImage2;
    int SELECT_PHOTO = 1;
    int COVER_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_sign_up);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//This hides the automatic keyboard when activity starts

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        progress = new ProgressDialog(this);
        addProfilePic = (Button)findViewById(R.id.add_profilepic_button);
        addCoverPic = (Button)findViewById(R.id.add_coverpic_button);
        profilePic = (ImageView)findViewById(R.id.profile_pic);

        Bitmap bm = drawableToBitmap(profilePic.getDrawable());
        roundedImage = new RoundedImage(bm);
        profilePic.setImageDrawable(roundedImage);

        coverPic = (ImageView)findViewById(R.id.cover_pic);
        profilePictureInput = (TextView)findViewById(R.id.profile_pic_input);
        coverPictureInput = (TextView)findViewById(R.id.cover_pic_input);
        profilePictureInput.setEnabled(false);    coverPictureInput.setEnabled(false);

        addProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                //Toast.makeText(ProfileSignUp.this, "started", Toast.LENGTH_SHORT).show();
            }
        });

        addCoverPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, COVER_PHOTO);
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



    @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK)
            {
                //Toast.makeText(ProfileSignUp.this, "valid Input", Toast.LENGTH_SHORT).show();
                try
                {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    //profilePic.setImageBitmap(selectedImage);
                    Drawable d = new BitmapDrawable(selectedImage);
                    profilePic.setImageDrawable(d);
                    profilePictureInput.setText(data.getDataString());
                    profilePictureInput.setTextSize(11);
                    Intent cropIntent = new Intent("com.android.camera.action.CROP");
                    cropIntent.setDataAndType(imageUri, "image/*");
                    //set crop properties
                    cropIntent.putExtra("crop", "true");
                    //indicate aspect of desired crop
                    cropIntent.putExtra("aspectX", 1);
                    cropIntent.putExtra("aspectY", 1);
                    //indicate output X and Y
                    cropIntent.putExtra("outputX", 256);
                    cropIntent.putExtra("outputY", 256);
                    //retrieve data on return
                    cropIntent.putExtra("return-data", true);
                    //start the activity - we handle returning in onActivityResult
                    startActivityForResult(cropIntent, 3);
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityProfileSignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(ActivityProfileSignUp.this, "Invalid File Input", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == 2){
            if(resultCode == RESULT_OK)
            {
                //Toast.makeText(ProfileSignUp.this, "valid Input", Toast.LENGTH_SHORT).show();
                try
                {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    //profilePic.setImageBitmap(selectedImage);
                    Drawable d = new BitmapDrawable(selectedImage);
                    coverPic.setImageDrawable(d);
                    //coverPic.getLayoutParams().width =selectedImage.getWidth();
                    //coverPic.getLayoutParams().height = (int)(0.3 * selectedImage.getWidth());
                    //coverPic.getLayoutParams().height = (int)300;
                    //Toast.makeText(ProfileSignUp.this, String.valueOf(0.6 * selectedImage.getWidth()), Toast.LENGTH_SHORT).show();
                    coverPictureInput.setText(data.getDataString());
                    coverPictureInput.setTextSize(11);
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityProfileSignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(ActivityProfileSignUp.this, "Invalid File Input", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == 3){
            if(resultCode == RESULT_OK)
            {
                //Toast.makeText(ProfileSignUp.this, "valid Input", Toast.LENGTH_SHORT).show();
                try
                {
                    Bundle extras = data.getExtras();
                    //get the cropped bitmap
                    Bitmap thePic = extras.getParcelable("data");
                    profilePic.setImageBitmap(thePic);
                    Bitmap bm = drawableToBitmap(profilePic.getDrawable());
                    roundedImage = new RoundedImage(bm);
                    profilePic.setImageDrawable(roundedImage);

                }
                catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityProfileSignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(ActivityProfileSignUp.this, "Invalid File Input", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void onNextClicked(View view)
    {
        progress.setTitle("CareerLed");
        progress.setMessage("Creating ActivityAccount...");
        progress.show();

        // To dismiss the dialog
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progress.dismiss();
                Intent toNextPage = new Intent(ActivityProfileSignUp.this, ActivityLoginPage.class);
                //This clears all previous tasks/activities/intents and as such when user back presses in the next activity, there is
                //none to go back to.
                toNextPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                toNextPage.putExtra("signup", 1);
                startActivity(toNextPage);
            }
        }, 2500);

    }

    public void onPreviousClicked(View view) {
        progress.setTitle("CareerLed");
        progress.setMessage("Loading...");
        progress.show();

        // To dismiss the dialog
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progress.dismiss();
                ActivityProfileSignUp.this.onBackPressed();
            }
        }, 1500);
    }

    public void skipPage(View view)
    {
        onNextClicked(view);
    }
}
