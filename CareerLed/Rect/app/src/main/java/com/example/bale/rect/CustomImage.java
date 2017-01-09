package com.example.bale.rect;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by BALE on 20/09/2015.
 */
public class CustomImage extends Drawable {
    private final Bitmap mBitmap;
    private final Paint mPaint;
    private final RectF mRectF;
    int e; int f; int g; int h;

    private final int mBitmapWidth;
    private final int mBitmapHeight;
    Canvas can;

    public CustomImage(Bitmap bitmap) {
        mBitmap = bitmap;

        mRectF = new RectF();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        final BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //mPaint.setShader(shader);

        mBitmapWidth = mBitmap.getHeight();
        mBitmapHeight = mBitmap.getHeight();
        can = new Canvas(mBitmap);
        //mBitmapHeight = 500;
        //mBitmapWidth=200;
    }
    public CustomImage(Bitmap bitmap, int a, int b, int c, int d) {
        mBitmap = bitmap;

        mRectF = new RectF();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        final BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //mPaint.setShader(shader);
        e = a; f = b; g = c; h = d;
        mBitmapWidth = mBitmap.getHeight();
        mBitmapHeight = mBitmap.getHeight();

        //mBitmapHeight = h - f;
        //mBitmapWidth=g - e;
    }

    @Override
    public void draw(Canvas canvas) {
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mRectF.set(e,f,g,h);

        canvas.drawRect(mRectF, mPaint);
        /*if (e >20){
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.STROKE);
            mRectF.set(e,f,g,h);

            canvas.drawRect(mRectF, mPaint);
        }
        else{
            mPaint.setColor(Color.MAGENTA);
            mPaint.setStyle(Paint.Style.STROKE);
            mRectF.set(12, 42, 200, 500);

            canvas.drawRect(mRectF, mPaint);
        }*/
        //canvas.drawOval(mRectF, mPaint);
    }

    public void shift(){
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStyle(Paint.Style.STROKE);
        mRectF.set(e,f,g,h);

        can.drawRect(mRectF, mPaint);
    }


    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mRectF.set(bounds);
    }

    @Override
    public void setAlpha(int alpha) {
        if (mPaint.getAlpha() != alpha) {
            mPaint.setAlpha(alpha);
            invalidateSelf();
        }
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmapWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmapHeight;
    }

    public void setAntiAlias(boolean aa) {
        mPaint.setAntiAlias(aa);
        invalidateSelf();
    }

    @Override
    public void setFilterBitmap(boolean filter) {
        mPaint.setFilterBitmap(filter);
        invalidateSelf();
    }

    @Override
    public void setDither(boolean dither) {
        mPaint.setDither(dither);
        invalidateSelf();
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

}
