package com.example.nutrilogix.RecipesPage.RoomDB;

import androidx.room.*;
import com.example.nutrilogix.RecipesPage.RoomDB.*;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM recipe")
    List<User> getAll();

}
