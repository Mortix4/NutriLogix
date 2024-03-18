package com.example.nutrilogix;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.Cursor;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nutrilogix.Classes.HelperDB;
import com.example.nutrilogix.Classes.MyUser;
import java.util.ArrayList;

public class ShowData extends AppCompatActivity {
    private HelperDB helperDB;
    private SQLiteDatabase db;
    Cursor c;
    ArrayAdapter<MyUser> adapter;
    ArrayList<MyUser> allUsers;
    ArrayList<String> info;
    ListView lvUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        lvUsers = findViewById(R.id.listView);
        info = new ArrayList<>();
        allUsers = new ArrayList<>();


        helperDB = new HelperDB(this, "users.db");
        db = helperDB.getReadableDatabase();
        c = db.query(helperDB.USER_TABLE, null, null, null, null, null, null);
        if (c.getCount() == 0) {
            db.close();
            return;
        }
        String un = HelperDB.USER_NAME;
        String ufn = HelperDB.USER_FNAME;
        String uln = HelperDB.USER_LNAME;
        String uph = HelperDB.USER_PHONE;
        String upw = HelperDB.USER_PASS;
        String ue = HelperDB.USER_EMAIL;
        db.close();

        c.moveToFirst();
        int i = 0;
        while (!c.isAfterLast())
        {
            MyUser user = new MyUser("", "", "", "", "", "");
            user.setUsername(c.getString(((int) c.getColumnIndex(un))));
            user.setFirstName(c.getString(((int) c.getColumnIndex(ufn))));
            user.setLastName(c.getString(((int) c.getColumnIndex(uln))));
            user.setPhoneNumber(c.getString(((int) c.getColumnIndex(uph))));
            user.setPassword(c.getString(((int) c.getColumnIndex(upw))));
            user.setEmail(c.getString(((int) c.getColumnIndex(ue))));

            allUsers.add(user);
            info.add(allUsers.get(i).toString());

            Toast.makeText(ShowData.this, info.get(i), Toast.LENGTH_SHORT).show();
            i++;
            c.moveToNext();
        }
        db.close();
        adapter = new ArrayAdapter<>(ShowData.this, android.R.layout.simple_list_item_1, allUsers);
        lvUsers.setAdapter(adapter);
    }

    public void goback(View view) {
        finish();
    }
}
