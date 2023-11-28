package com.example.test_a;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service implements SensorEventListener {

    private SensorManager sensorManager;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 센서 리스너 등록
        registerSensorListener();
        Log.d("MyService", "onStartCommand");

        // Foreground Service로 설정
        startForeground(1, createNotification());

        // 서비스가 종료되어도 자동으로 재시작하도록 설정
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("Error", "onTaskRemoved - 강제 종료 " + rootIntent);
        stopSelf(); //서비스 종료
    }

    private Notification createNotification() {
        // Notification을 생성하여 반환
        // 필요에 따라 NotificationChannel 설정 (Android 8.0 이상)
        // NotificationManagerCompat을 사용하여 Notification을 보낼 수 있음
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "stepForeground");

        builder.setContentTitle("MissionAlarm")
                .setContentText("당신의 도보를 측정중 입니다.")
                .setSmallIcon(R.drawable.stepimage)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Android 8.0 이상에서는 Notification Channel을 생성하여 설정해야 합니다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "stepForeground",
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        // 알림을 탭했을 때 앱을 열도록 Intent 설정
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);

        // Notification을 반환
        return builder.build();
    }

    private void registerSensorListener() {
        // 걸음 센서를 위한 기본 센서 가져오기
        Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        // 센서 리스너 등록
        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_GAME);
        } else {
            // 걸음 센서를 사용할 수 없는 경우 처리
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            // 걸음 수 변경에 대한 기존 로직
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
        // 정확도 변경에 대한 처리 (필요한 경우)
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 서비스가 파괴될 때 센서 리스너 등록 해제
        Log.d("MyService", "onDestroy");
        sensorManager.unregisterListener(this);
    }
}