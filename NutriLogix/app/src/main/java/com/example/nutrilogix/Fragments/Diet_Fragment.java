package com.example.nutrilogix.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrilogix.Adapters.BreakfastAdapter;
import com.example.nutrilogix.Classes.Food;
import com.example.nutrilogix.Classes.Meal;
import com.example.nutrilogix.R;

import java.util.ArrayList;
import java.util.List;


public class Diet_Fragment extends Fragment {
    View view;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_diet_, container, false);
        /*
        // Dummy data for testing
        List<Meal> meals = createDummyMeals();

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Set up the adapter
        BreakfastAdapter breakfastAdapter = new BreakfastAdapter(meals);
        recyclerView.setAdapter(breakfastAdapter);
        */
        return view;
    }

    /*private List<Meal> createDummyMeals() {
        List<Meal> meals = new ArrayList<>();

        Meal meal1 = new Meal("Breakfast", 3, R.drawable.calories);
        meal1.addFood(new Food(500, 20, 30, 40));
        meals.add(meal1);

        // Add more meals as needed

        return meals;
    }*/

}