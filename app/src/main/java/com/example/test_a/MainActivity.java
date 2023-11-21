package com.example.test_a;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// 만보기 제작

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor stepSensor;
    private int stepCount = 0;
    private TextView countTV; private TextView goalTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countTV = (TextView)findViewById(R.id.cnt_txt);
        goalTV = (TextView) findViewById(R.id.goal_txt);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            if (stepSensor != null) {
                sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }

    }//----------------------------<onCreate>------------------------


    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            stepCount = (int) event.values[0];
            countTV.setText("Step Count: " + stepCount);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){
        // no return
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listener to save power when the app is in the background
        sensorManager.unregisterListener(this, stepSensor);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Register the sensor listener again when the app is resumed
        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}