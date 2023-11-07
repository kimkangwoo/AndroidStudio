package com.example.test_a;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private PreviewView preview;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //layout 불러오기
        button = (Button)findViewById(R.id.st_btn);
        preview= (PreviewView) findViewById(R.id.previewivew);
        FrameLayout frame = (FrameLayout)findViewById(R.id.fram);
        TextView text = (TextView)findViewById(R.id.text1);

        //카메라 권한 설정
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permission == PackageManager.PERMISSION_DENIED){
            //카메라 권한이 없을 때
            requestPermissions(new String[]{
                    Manifest.permission.CAMERA
            }, 1000);
            Log.d(TAG, "DEBUGE :: CAMERA_PERMISSION_실행");
        }

        // start 버튼 클릭 시 카메라 동작 설정
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 cameraProviderFuture.addListener(() -> {
                     try {
                         frame.removeView(text);
                         ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                         bindPreview(cameraProvider);
                         button.setEnabled(false);
                     } catch (ExecutionException | InterruptedException e) {
                         // No errors need to be handled for this Future.
                         // This should never be reached.
                     }
                 }, ContextCompat.getMainExecutor(MainActivity.this));
            }
        });
    }
    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(this.preview.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, preview);
    }


}