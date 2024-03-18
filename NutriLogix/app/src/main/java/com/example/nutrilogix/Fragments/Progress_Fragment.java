package com.example.nutrilogix.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.github.farshidroohi.LineChart;
import java.util.ArrayList;
import java.util.List;
import com.example.nutrilogix.R;

public class Progress_Fragment extends Fragment {

    private final float[] firstChartEntity = {113000f, 183000f, 188000f, 695000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f};
    private final float[] secondChartEntity = {0f, 245000f, 1011000f, 1000f, 0f, 0f, 47000f, 20000f, 12000f, 124400f, 160000f};
    private final List<String> legendArr = new ArrayList<String>() {{
        add("01/21");
        add("01/22");
        add("01/23");
        add("01/24");
        add("01/25");
        add("01/26");
        add("01/27");
        add("01/28");
        add("01/29");
        add("01/30");
        add("01/31");
    }};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress_, container, false);
        LineChart lineChart = view.findViewById(R.id.lineChart);
        List<float[]> values = new ArrayList<>();
        values.add(firstChartEntity);
        values.add(secondChartEntity);
        lineChart.setLineColor(getResources().getColor(R.color.colorAccent));
        lineChart.setLegend(legendArr);
        return view;
    }
}
