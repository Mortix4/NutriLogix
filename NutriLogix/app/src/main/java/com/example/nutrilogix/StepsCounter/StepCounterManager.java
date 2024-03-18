package com.example.nutrilogix.StepsCounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class StepCounterManager implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private int steps;
    private StepListener stepListener;

    public StepCounterManager(Context context, StepListener listener) {
        this.stepListener = listener;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (stepCounterSensor != null) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (steps == 0) {
                // Initial step count
                steps = (int) event.values[0];
            } else {
                // Calculate the number of steps since the last event
                int newSteps = (int) event.values[0];
                int stepCount = newSteps - steps;
                steps = newSteps;

                // Notify the listener of the step count
                stepListener.onStepCountChanged(stepCount);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }

    public interface StepListener {
        void onStepCountChanged(int stepCount);
    }
}

