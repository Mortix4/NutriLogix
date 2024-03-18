package com.example.nutrilogix.StepsCounter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nutrilogix.R;


public class StepsMain extends AppCompatActivity implements stepsCallback {

    private TextView TV_STEPS;
    private TextView TV_CALORIES;
    private TextView TV_DISTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_running_);

        TV_STEPS = findViewById(R.id.TV_STEPS);
        TV_CALORIES = findViewById(R.id.TV_CALORIES);
        TV_DISTANCE = findViewById(R.id.TV_DISTANCE);

        Intent intent = new Intent(this, StepDetectorService.class);
        startService(intent);

        StepDetectorService.Subscribe.register(this);
    }

    @Override
    public void subscribeSteps(int steps) {
        TV_STEPS.setText(String.valueOf(steps));
        TV_CALORIES.setText(GeneralHelper.getCalories(steps));
        TV_DISTANCE.setText(GeneralHelper.getDistanceCovered(steps));
    }
}
