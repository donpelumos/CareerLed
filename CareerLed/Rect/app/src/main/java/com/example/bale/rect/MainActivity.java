package com.example.bale.rect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity
{
    Button button;
    ImageView image3;
    ImageButton im;
    CustomImage bm;
    int startX; int startY;
    int widthX, widthY;
    int x1=0; int y1=0; int x2; int y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        button = (Button)findViewById(R.id.button);
        image3 = (ImageView)findViewById(R.id.image3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });

        /*
        im = (ImageButton)findViewById(R.id.image);
        //bm = new CustomImage(drawableToBitmap(im.getDrawable()), 50,50,400,200);
        Canvas can = new Canvas(drawableToBitmap(im.getDrawable()));
        RectF rect = new RectF();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        rect.set(5,5,40,40);
        can.drawRect(rect,paint);
        //im.setImageDrawable(bm);
        startX = startY = 50;
        widthX = 400;
        widthY = 200;
        //bm = new CustomImage(drawableToBitmap(im.getDrawable()));
        //im.setImageDrawable(bm);


        im.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(getApplicationContext(), "Hala", Toast.LENGTH_LONG).show();
                        x1 = (int) event.getX();
                        y1 = (int) event.getY();

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    x2 = (int) event.getX();
                    y2 = (int) event.getY();
                    Toast.makeText(getApplicationContext(), x1 + " " + y1 + "---" + x2 + " " + y2, Toast.LENGTH_LONG).show();
                    bm = new CustomImage(drawableToBitmap(im.getDrawable()), startX + (x2 - x1),
                            startY + (y2 - y1), startX + (x2 - x1) + 350,
                            startY + (y2 - y1) + 150);
                    im.setImageDrawable(bm);
                    startX = startX + (x2 - x1);
                    startY = startY + (y2 - y1);

                }

                return false;
            }
        });

        */

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
                    image3.setImageDrawable(d);
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
                    startActivityForResult(cropIntent, 2);
                }
                catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }
        else if(requestCode == 2)
        {
            Bundle extras = data.getExtras();
            //get the cropped bitmap
            Bitmap thePic = extras.getParcelable("data");
            image3.setImageBitmap(thePic);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
