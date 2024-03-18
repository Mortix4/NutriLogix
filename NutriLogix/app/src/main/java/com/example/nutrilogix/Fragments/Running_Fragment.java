package com.example.nutrilogix.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrilogix.R;

import com.example.nutrilogix.StepsCounter.StepCounterManager;

import android.widget.TextView;



public class Running_Fragment extends Fragment implements StepCounterManager.StepListener {

    private TextView stepCountTextView;

    @Override
    public void onStepCountChanged(int stepCount) {
        // Handle the step count change here
        // Update your UI by setting the text of the stepCountTextView
        stepCountTextView.setText("Step Count: " + stepCount);
    }

    public Running_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_running_, container, false);

        //stepCountTextView = view.findViewById(R.id.stepCountTextView);

        // Initialize the StepCounter
        StepCounterManager stepCounter = new StepCounterManager(requireContext(), this);
        return view;
    }


}