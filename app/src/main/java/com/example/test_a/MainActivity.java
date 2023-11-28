package com.example.test_a;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.Calendar;

// 만보기 제작

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private final static String Tag ="MainActivity";
    private SensorManager sensorManager;
    private Sensor stepCountSensor;
    private int stepCount;
    private int mstepCount;// 현재 걸음 수
    protected TextView countTV;
    private TextView goalTV;
    private Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = findViewById(R.id.set_btn);
        countTV = findViewById(R.id.cnt_txt);
        goalTV = findViewById(R.id.goal_txt);
        // - TYPE_STEP_DETECTOR:  리턴 값이 무조건 1, 앱이 종료되면 다시 0부터 시작
        // - TYPE_STEP_COUNTER : 앱 종료와 관계없이 계속 기존의 값을 가지고 있다가 1씩 증가한 값을 리턴
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        // permission line -------------------------------------------------------------------------
        if(ContextCompat.checkSelfPermission(this, Manifest.
                permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.SET_ALARM)==PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.SET_ALARM}, 0);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.
                permission.RECEIVE_BOOT_COMPLETED) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.RECEIVE_BOOT_COMPLETED}, 0);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.
                permission.FOREGROUND_SERVICE) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.FOREGROUND_SERVICE}, 0);
        }
        // time set line ---------------------------------------------------------------------------

        // 코드 실행 시간 설정
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //  00:00
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 0);

        // Create an Intent for your BroadcastReceiver
        Intent intent = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);

        // AlarmManager service 가져오기
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        // method ----------------------------------------------------------------------------------

        if(stepCountSensor == null){
            Toast.makeText(this, "No Step Sensor", Toast.LENGTH_LONG).show();
        }

        // 앱 종료시 횟수 카운트
        startMyService(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 리셋 버튼
                StepCountPreferenceHelper.saveStepCount(MainActivity.this, 0);
                countTV.setText(String.valueOf("오늘 걸음 : "+0));
;
            }
        });

    }//----------------------------<onCreate>-------------------------------------------------------

    // - SENSOR_DELAY_NORMAL: 20,000 초 딜레이
    // - SENSOR_DELAY_UI: 6,000 초 딜레이
    // - SENSOR_DELAY_GAME: 20,000 초 딜레이
    // - SENSOR_DELAY_FASTEST: 딜레이 없음
    public void onStart(){
        super.onStart();
        if(sensorManager != null) {
            sensorManager.registerListener(this,
                    stepCountSensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }
    // MyService 실행
    public static void startMyService(Context context) {
        Intent serviceIntent = new Intent(context, MyService.class);
        context.startService(serviceIntent);
    }

    // 센서 이벤트 리스너 메소드
    // 센서는 동작을 감지 하면 이벤트를 발생하여 onSensorChanged에 값을 전달합니다.
    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            stepCount = StepCountPreferenceHelper.getStepCount(this);
            if (stepCount < 1) {
                stepCount = (int) event.values[0];
                // Save the updated step count to SharedPreferences
                StepCountPreferenceHelper.saveStepCount(this, (int) event.values[0]);
            }
            mstepCount = (int) event.values[0] - stepCount;
            goalTV.setText(String.valueOf("총 걸음 : "+event.values[0]));
            countTV.setText(String.valueOf("오늘 걸음 : "+mstepCount));
        }
    }

    // no return
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}
class StepCountPreferenceHelper {
    private static final String PREF_NAME = "StepCountPreferences";
    private static final String KEY_STEP_COUNT = "stepCount";

    public static void saveStepCount(Context context, int stepCount) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_STEP_COUNT, stepCount);
        editor.apply();
    }

    public static int getStepCount(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(KEY_STEP_COUNT, 0);
    }
}
