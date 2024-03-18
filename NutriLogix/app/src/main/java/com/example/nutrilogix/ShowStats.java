package com.example.nutrilogix;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nutrilogix.Classes.HelperDB;
import com.example.nutrilogix.Classes.UserData;
import java.util.ArrayList;

public class ShowStats extends AppCompatActivity {

    HelperDB helperDB;
    SQLiteDatabase db;
    Cursor c;
    ArrayAdapter<UserData> adapter;
    ArrayList<UserData> allUsers;
    ArrayList<String> info;
    ListView lvStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        lvStats = findViewById(R.id.listView);
        info = new ArrayList<>();
        allUsers = new ArrayList<>();

        helperDB = new HelperDB(this, "userstats.db");
        db = helperDB.getReadableDatabase();
        c = db.query(helperDB.USERSTATS_TABLE, null, null, null, null, null, null);

        if (c.getCount() == 0) {
            db.close();
            return;
        }

        String un = HelperDB.USERSTATS_NAME;
        String height = HelperDB.USERSTATS_HEIGHT;
        String weight = HelperDB.USERSTATS_WEIGHT;
        String age = HelperDB.USERSTATS_AGE;
        String gender = HelperDB.USERSTATS_GENDER;
        String activityLevel = HelperDB.USERSTATS_ACTIVITYLEVEL;

        db.close();

        c.moveToFirst();
        int i = 0;
        while (!c.isAfterLast()) {
            UserData user = new UserData("", "", "", "", "", "");
            user.setUsername(c.getString(((int) c.getColumnIndex(un))));
            user.setHeight(c.getString((int) c.getColumnIndex(height)));
            user.setWeight(c.getString((int) c.getColumnIndex(weight)));
            user.setAge(c.getString((int) c.getColumnIndex(age)));
            user.setGender(c.getString((int) c.getColumnIndex(gender)));
            user.setActivityLevel(c.getString((int) c.getColumnIndex(activityLevel)));

            allUsers.add(user);
            info.add(allUsers.get(i).toString());

            Toast.makeText(ShowStats.this, info.get(i), Toast.LENGTH_SHORT).show();
            i++;
            c.moveToNext();
        }

        db.close();
        adapter = new ArrayAdapter<>(ShowStats.this, android.R.layout.simple_list_item_1, allUsers);
        lvStats.setAdapter(adapter);
    }

    public void goback(View view) {
        finish();
    }
}
