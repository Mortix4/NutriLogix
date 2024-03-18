package com.example.nutrilogix.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nutrilogix.Classes.Meal;
import com.example.nutrilogix.R;

import java.util.List;
public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.MealViewHolder> {
    Context context;
    List<Meal> meals;

    public BreakfastAdapter(List<Meal> meals) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal_breakfast, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        holder.mealNametv.setText(meals.get(position).getName());
        holder.mealiv.setImageResource(meals.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder {

        private TextView mealNametv;
        private ImageView mealiv;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealNametv = itemView.findViewById(R.id.mealNametv);
        }

    }

}
