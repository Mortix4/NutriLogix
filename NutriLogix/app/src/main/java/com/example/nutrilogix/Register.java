package com.example.nutrilogix;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrilogix.Classes.HelperDB;
import com.example.nutrilogix.Classes.MyUser;
import com.example.nutrilogix.Services.ttsService;

public class Register extends AppCompatActivity {
    EditText etUserName, etFirstName, etLastName, etPhoneNumber, etEmail, etPassword;
    Button registerButton;
    HelperDB Helperdb;
    AlertDialog.Builder adb1;
    MyUser User;
    String stUsername, stFname, stLname, stPhNum, stEmail, stPass, error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InitServices();
        InitComp();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                getStringsFromEditTexts();
                User = new MyUser(stUsername, stFname, stLname, stPhNum, stEmail, stPass);

                //Check
                if (!checkNewCred())
                {
                    showAlertDialog();
                }
                else
                {
                    saveUserData(User);
                }
            }
        });
    }



    public void goback() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itBack)
            finish();
        if(id == R.id.itExit)
            finishAffinity();
        if(id == R.id.itGuide) {}
        return true;

    }

    private void saveUserData(MyUser user) {
        Helperdb.addUser(user);
        Toast.makeText(this, "User Added Succesfully!", Toast.LENGTH_SHORT).show();
        resetEditTexts();
        startActivity(new Intent(Register.this, ShowData.class));
    }

    public boolean checkNewCred() {
        error="";
        error += isValidFirstName();
        error += isValidLastName();
        error += isValidEmail();
        error += isValidPhoneNumber();
        error += isValidUsername();
        error += isValidPassword();
        return (error.equals("") && !Helperdb.isExists(stUsername));
    }

    private String isValidPassword() {
        if(stPass.length() < 6)
            return "Password: Must contain At Least 6 Characters \n";
        if (!stPass.matches(".*[!@#$%^&()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?\\d].*"))
            return "Password: Must contain At Least 1 Number And 1 Special Character\n";

        return "";
    }

    private String isValidUsername() {
        if(stUsername.length() <= 2)
            return "Username: Must contain atLeast 2 characters\n";

        return "";
    }

    private String isValidPhoneNumber() {
        if(!stPhNum.matches("\\d+"))
            return "Number: Must only contain numeric digits\n";
        if(stPhNum.length() < 7)
            return "Number: must contain atLeast 7 digits\n";
        if(stPhNum.length()> 10)
            return "Number: mustn't contain more than 10 digits\n";
        return "";
    }

    private String isValidEmail() {
        if(stEmail.isEmpty())
            return "Email: mustn't be empty\n";
        if(!stEmail.contains("@") || !stEmail.contains("."))
            return"Email: must contain '@' and '.'\n";

        return "";
    }

    private String isValidFirstName() {
        if(stFname.isEmpty())
            return "First Name: mustn't be empty\n";
        if(!stFname.matches("[a-zA-Z]+" ))
            return "First Name: mustn't contain special characters\n";
        if(stFname.length() > 15 )
            return "First Name: mustn't contain more than 15 letters\n";
        if(stFname.length() <=1)
            return "First Name: must contain atLeast 1 letter\n";

        return "";
    }

    private String isValidLastName() {
        if(stFname.isEmpty())
            return "Last Name: mustn't be empty\n";
        if(!stFname.matches("[a-zA-Z]+" ))
            return "Last Name: mustn't contain special characters\n";
        if(stFname.length() > 15 )
            return "Last Name: mustn't contain more than 15 letters\n";
        if(stFname.length() <=1)
            return "Last Name: must contain atLeast 1 letter\n";

        return "";
    }

    public void getStringsFromEditTexts()
    {
        stUsername = etUserName.getText().toString();
        stFname = etFirstName.getText().toString();
        stLname = etLastName.getText().toString();
        stPhNum = etPhoneNumber.getText().toString();
        stEmail = etEmail.getText().toString();
        stPass = etPassword.getText().toString();
    }
    public void resetEditTexts()
    {
        etUserName.setText("");
        etFirstName.setText("");
        etLastName.setText("");
        etPhoneNumber.setText("");
        etPassword.setText("");
        etEmail.setText("");
    }

    public void InitComp() {
        Helperdb = new HelperDB(this, "users.db");
        adb1 = new AlertDialog.Builder(this);
        etUserName = findViewById(R.id.etUserName);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhoneNumber = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPsswd);
        registerButton = findViewById(R.id.registerButton);
    }

    public void InitServices() {
        Intent goService = new Intent(Register.this, ttsService.class);
        goService.putExtra("read", "Welcome To Register Form");
        startService(goService);
    }

    public void showAlertDialog()
    {
        adb1.setTitle("Invalid signup info");
        adb1.setCancelable(false);
        adb1.setMessage(error);
        adb1.setNeutralButton("Ok", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) {}});
        adb1.setNegativeButton("Delete", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) {resetEditTexts();}});
        adb1.create().show();
    }
}