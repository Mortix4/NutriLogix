package com.example.nutrilogix;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.nutrilogix.Services.ttsService;
import com.example.nutrilogix.Fragments.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.Map;
public class FragmentsMain extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Map<Integer, Class<? extends Fragment>> fragmentMap = new HashMap<>();
    BottomNavigationView bottomNavigationView;

    private static final int MENU_ITEM_DIET = R.id.diet;
    private static final int MENU_ITEM_RUNNING = R.id.exercises;
    private static final int MENU_ITEM_RECIPES = R.id.recipes;
    private static final int MENU_ITEM_PROGRESS = R.id.progress;
    private static final int MENU_ITEM_PersonalDetails = R.id.stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentsmain);


        InitServices();
        InitFragmentsMap();
        InitNavbar();
        InitMainFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Class<? extends Fragment> fragmentClass = fragmentMap.get(item.getItemId());
        if (fragmentClass != null) {
            try {
                Fragment fragment = fragmentClass.newInstance();
                loadFragment(fragment);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, fragment).commit();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.itBack)
            finish();
        if (id == R.id.itExit)
            finishAffinity();
        if (id == R.id.itGuide) {}
        return true;
    }

    public void InitServices()
    {
        Intent goService = new Intent(FragmentsMain.this, ttsService.class);
        goService.putExtra("read", "Welcome To Main Form");
        startService(goService);
    }

    public void InitFragmentsMap()
    {
        fragmentMap.put(MENU_ITEM_DIET, Diet_Fragment.class);
        fragmentMap.put(MENU_ITEM_RUNNING, Running_Fragment.class);
        fragmentMap.put(MENU_ITEM_RECIPES, Recipes_Fragment.class);
        fragmentMap.put(MENU_ITEM_PROGRESS, Progress_Fragment.class);
        fragmentMap.put(MENU_ITEM_PersonalDetails, Stats_Fragment.class);
    }

    public void InitNavbar()
    {
        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }
    public void InitMainFragment()
    {
        loadFragment(new Diet_Fragment());
        // Highlight in Navbar
        MenuItem dietMenuItem = bottomNavigationView.getMenu().findItem(MENU_ITEM_DIET);
        dietMenuItem.setChecked(true);
    }

}
