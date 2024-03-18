package com.example.nutrilogix.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class HelperDB extends SQLiteOpenHelper {
    public Context context;
    public static final int DATABASE_VERSION = 1;
    public static final String DB_FILE = "users.db";
    public static final String DB_FILE2 = "userstats.db";
    public static final String USER_TABLE = "users";
    public static final String USERSTATS_TABLE = "userstats";
    public static final String COLUMN_ID = "id";
    public static final String USER_NAME = "username";
    public static final String USER_FNAME = "first_name";
    public static final String USER_LNAME = "last_name";
    public static final String USER_PHONE = "phone_number";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASS = "password";

    //userStats
    public static final String USERSTATS_NAME = "username";
    public static final String USERSTATS_HEIGHT = "height";
    public static final String USERSTATS_WEIGHT = "weight";
    public static final String USERSTATS_AGE = "age";
    public static final String USERSTATS_GENDER = "gender";
    public static final String USERSTATS_ACTIVITYLEVEL = "activity_level";



    private static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    USER_NAME + " TEXT," +
                    USER_FNAME + " TEXT, " +
                    USER_LNAME + " TEXT, " +
                    USER_PHONE + " TEXT, " +
                    USER_EMAIL + " TEXT, " +
                    USER_PASS + " TEXT)";

    private static final String CREATE_USERSTATS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + USERSTATS_TABLE + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    USER_NAME + " TEXT," +
                    USERSTATS_HEIGHT + " TEXT, " +
                    USERSTATS_WEIGHT + " TEXT, " +
                    USERSTATS_AGE + " TEXT, " +
                    USERSTATS_GENDER + " TEXT, " +
                    USERSTATS_ACTIVITYLEVEL + " TEXT)";


    public HelperDB(Context context, String dbName) {
        super(context, dbName, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_USERSTATS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean isExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String whereSearch = USER_NAME + "=?";
            String[] whatSearch = {username};

            cursor = db.query(
                    USER_TABLE,
                    null,
                    whereSearch,
                    whatSearch,
                    null,
                    null,
                    null
            );

            return cursor != null && cursor.getCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("UserExists", "Error checking user existence: " + e.getMessage());
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    public boolean isUsernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String whereSearch = USERSTATS_NAME + "=?";
            String[] whatSearch = {username};

            cursor = db.query(
                    USERSTATS_TABLE,
                    null,
                    whereSearch,
                    whatSearch,
                    null,
                    null,
                    null
            );

            return cursor != null && cursor.getCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("UsernameExists", "Error checking username existence: " + e.getMessage());
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    public void addUser(MyUser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getUsername());
        values.put(USER_FNAME, user.getFirstName());
        values.put(USER_LNAME, user.getLastName());
        values.put(USER_PHONE, user.getPhoneNumber());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASS, user.getPassword());
        SharedPreferences preferences = context.getSharedPreferences("UserDataPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", user.getUsername());
        editor.apply();

        try {
            db.insert(USER_TABLE, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = "Error adding user: " + e.getMessage();
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public void addUserStats(UserData userStats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERSTATS_NAME, userStats.getUsername());
        values.put(USERSTATS_HEIGHT, userStats.getHeight());
        values.put(USERSTATS_WEIGHT, userStats.getWeight());
        values.put(USERSTATS_AGE, userStats.getAge());
        values.put(USERSTATS_GENDER, userStats.getGender());
        values.put(USERSTATS_ACTIVITYLEVEL, userStats.getActivityLevel());

        try {
            db.insert(USERSTATS_TABLE, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = "Error adding user stats: " + e.getMessage();
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        }
        finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

}
