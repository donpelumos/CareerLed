package com.workchopapp.videoplayer;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by BALE on 15/09/2016.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
    private boolean cameraConfigured;
    private boolean inPreview;
    private Camera mCamera;
    private SurfaceHolder mHolder;
    Camera camera;
    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        this.inPreview = false;
        this.cameraConfigured = false;
        this.mCamera = camera;
        (this.mHolder = this.getHolder()).addCallback(this);
        this.mHolder.setType(3);
    }

    public void refreshCamera(Camera c){
        try {
            c.reconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Camera.Size getBestPreviewSize(final int n, final int n2, final Camera.Parameters cameraParameters) {
        Camera.Size cameraSize = null;
        for (final Camera.Size cameraSize2 : cameraParameters.getSupportedPreviewSizes()) {
            if (cameraSize2.width <= n && cameraSize2.height <= n2) {
                if (cameraSize == null) {
                    cameraSize = cameraSize2;
                }
                else {
                    if (cameraSize2.width * cameraSize2.height <= cameraSize.width * cameraSize.height) {
                        continue;
                    }
                    cameraSize = cameraSize2;
                }
            }
        }
        return cameraSize;
    }

    private void initPreview(final int n, final int n2) {
        if (this.mCamera == null || this.mHolder.getSurface() == null) {
            return;
        }
        while (true) {
            try {
                this.mCamera.setPreviewDisplay(this.mHolder);
                if (!this.cameraConfigured) {
                    final Camera.Parameters parameters = this.mCamera.getParameters();
                    final Camera.Size bestPreviewSize = this.getBestPreviewSize(n, n2, parameters);
                    if (bestPreviewSize != null) {
                        parameters.setPreviewSize(bestPreviewSize.width, bestPreviewSize.height);
                        this.mCamera.setParameters(parameters);
                        this.cameraConfigured = true;
                    }
                }
            }
            catch (Throwable t) {
                continue;
            }
            break;
        }
    }

    private void startPreview() {
        if (this.cameraConfigured && this.mCamera != null) {
            this.mCamera.startPreview();
            this.inPreview = true;
        }
    }

    @Override
    public void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
        if (this.mHolder.getSurface() != null) {
            this.initPreview(n2, n3);
            this.startPreview();
        }
    }

    @Override
    public void surfaceCreated(final SurfaceHolder previewDisplay) {
        try {
            this.mCamera.setPreviewDisplay(previewDisplay);
            this.mCamera.startPreview();
        }
        catch (IOException ex) {}
    }

    @Override
    public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
        //this.mCamera.stopPreview();
        this.mCamera.release();
    }

}
