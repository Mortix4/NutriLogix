package com.example.nutrilogix.Adapters;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrilogix.R;


public class BreakfastViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView nameView;

    public BreakfastViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.mealiv);
        nameView = itemView.findViewById(R.id.mealNametv);
        // foodsView = itemView.findViewById(R.id.email);
    }

}
