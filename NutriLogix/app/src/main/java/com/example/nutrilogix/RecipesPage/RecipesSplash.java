package com.example.nutrilogix.RecipesPage;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.example.nutrilogix.R;

public class RecipesSplash extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_splash);

        // Start HomeActivity after 1 second
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(RecipesSplash.this,RecipesHome.class));
                finish();
            }
        },1000);

    }
}