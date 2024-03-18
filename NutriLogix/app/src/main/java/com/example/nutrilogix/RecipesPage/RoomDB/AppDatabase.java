package com.example.nutrilogix.RecipesPage.RoomDB;

import androidx.room.*;

@Database(entities = {User.class}, exportSchema = false, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
