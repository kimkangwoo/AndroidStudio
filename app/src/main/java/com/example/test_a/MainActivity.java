package com.example.test_a;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// 만보기 제작

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private final static String Tag ="MainActivity";
    private SensorManager sensorManager;
    private Sensor stepCountSensor;
    int stepCount = 0; // 현재 걸음 수
    private TextView countTV; private TextView goalTV;
    private Button reset;

    @RequiresApi(api= Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = findViewById(R.id.set_btn);
        countTV = findViewById(R.id.cnt_txt);
        // - TYPE_STEP_DETECTOR:  리턴 값이 무조건 1, 앱이 종료되면 다시 0부터 시작
        // - TYPE_STEP_COUNTER : 앱 종료와 관계없이 계속 기존의 값을 가지고 있다가 1씩 증가한 값을 리턴
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        // permission
        if(ContextCompat.checkSelfPermission(this, Manifest.
                permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        if(stepCountSensor == null){
            Toast.makeText(this, "No Step Sensor", Toast.LENGTH_SHORT).show();
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 리셋 버튼 클릭시 이벤트
                stepCount = 0;
                countTV.setText(String.valueOf(stepCount));
            }
        });
    }//----------------------------<onCreate>------------------------

    // - SENSOR_DELAY_NORMAL: 20,000 초 딜레이
    // - SENSOR_DELAY_UI: 6,000 초 딜레이
    // - SENSOR_DELAY_GAME: 20,000 초 딜레이
    // - SENSOR_DELAY_FASTEST: 딜레이 없음
    public void onStart(){
        super.onStart();
        if(stepCountSensor != null){
            sensorManager.registerListener(this,
                    stepCountSensor,
                    SensorManager.SENSOR_DELAY_FASTEST);
        }
    }


    // 센서 이벤트 리스너 메소드
    // 센서는 동작을 감지 하면 이벤트를 발생하여 onSensorChanged에 값을 전달합니다.
    @Override
    public void onSensorChanged(SensorEvent event){
        Log.d("StepCounter", "Sensor event received");
        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            if(event.values[0]==1.f){
                stepCount++;
                countTV.setText(String.valueOf(stepCount));
            }
        }
    }

    // no return
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}