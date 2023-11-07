package com.example.test_a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Preview;
import androidx.camera.view.PreviewView;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private PreviewView preview= findViewById(R.id.previewivew);
    private Button button = findViewById(R.id.st_btn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preview;
        button
    }
}