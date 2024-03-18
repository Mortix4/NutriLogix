package com.example.nutrilogix.Fragments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nutrilogix.FragmentsMain;
import com.example.nutrilogix.MainActivity;
import com.example.nutrilogix.R;
import com.example.nutrilogix.RecipesPage.RecipesHome;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Recipes_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Recipes_Fragment extends Fragment {

    Context context;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public Recipes_Fragment() {
        // Required empty public constructor
    }

    public static Recipes_Fragment newInstance(String param1, String param2) {
        Recipes_Fragment fragment = new Recipes_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        startActivity(new Intent(getActivity(), RecipesHome.class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress_, container, false);
    }
}
