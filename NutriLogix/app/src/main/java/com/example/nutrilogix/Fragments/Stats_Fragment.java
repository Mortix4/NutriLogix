package com.example.nutrilogix.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.example.nutrilogix.Classes.UserData;
import com.example.nutrilogix.Classes.HelperDB;
import com.example.nutrilogix.R;
import com.example.nutrilogix.ShowStats;

import android.content.SharedPreferences;
import android.widget.Toast;


public class Stats_Fragment extends Fragment {

    EditText weightEditText;
    EditText heightEditText;
    EditText ageEditText;
    RadioGroup genderRadioGroup, activityLevelRadioGroup;
    View view;
    Button saveButton;
    String stUsername, stWeight, stHeight, stAge, stGender, stActivityLevel, error;
    AlertDialog.Builder adb;
    HelperDB db;

    public Stats_Fragment() {}

    public static Stats_Fragment newInstance() {
        return new Stats_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_stats_, container, false);
        InitComp();
        SetFocusFields();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
                getStringsFromEditTexts();
                if(!checkNewCred())
                {
                    showAlertDialog();
                }
                else
                {
                    saveUserDataToDB();
                }
            }
        });
        return view;
    }

    private void setOnFocusChangeListener(EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    closeKeyboard();
                }
            }
        });
    }

    private void saveUserDataToDB() {
        UserData userData = new UserData(stUsername, stWeight, stHeight, stAge, stGender, stActivityLevel);
        HelperDB db = new HelperDB(getContext(), "userstats.db");
        db.addUserStats(userData);
        Toast.makeText(getContext(), "User Stats Added Succesfully!", Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(getActivity(), ShowStats.class));
    }

    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private String getSelectedGender() {
        int selectedId = genderRadioGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.maleRadioButton)
            return "Male";
        else if (selectedId == R.id.femaleRadioButton)
            return "Female";
        else
            return "";
    }

    private String getSelectedActivityLevel() {
        int selectedId = activityLevelRadioGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.sedentaryRadioButton)
            return "Sedentary";
        else if (selectedId == R.id.lightlyActiveRadioButton)
            return "Lightly Active";
        else if (selectedId == R.id.moderatelyActiveRadioButton)
            return "Moderately Active";
        else if (selectedId == R.id.veryActiveRadioButton)
            return "Very Active";
        else
            return "";

    }

    public void InitComp()
    {
        adb = new AlertDialog.Builder(this.getContext());
        weightEditText = view.findViewById(R.id.weightEditText);
        heightEditText = view.findViewById(R.id.heightEditText);
        ageEditText = view.findViewById(R.id.ageEditText);
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);
        activityLevelRadioGroup = view.findViewById(R.id.activityLevelRadioGroup);
        saveButton = view.findViewById(R.id.saveButton);
    }
    public void SetFocusFields()
    {
        setOnFocusChangeListener(weightEditText);
        setOnFocusChangeListener(heightEditText);
        setOnFocusChangeListener(ageEditText);
    }

    public void getStringsFromEditTexts()
    {
        stWeight = weightEditText.getText().toString();
        stHeight = heightEditText.getText().toString();
        stAge = ageEditText.getText().toString();
        stGender = getSelectedGender();
        stActivityLevel = getSelectedActivityLevel();
        SharedPreferences preferences = getActivity().getSharedPreferences("UserDataPrefs", Context.MODE_PRIVATE);
        stUsername = preferences.getString("username", "");
        if (stUsername.isEmpty())
        {
            stUsername = preferences.getString("username", "");
        }
    }

    private boolean checkNewCred() {
        error = "";
        error = isValidWeight();
        error += isValidHeight();
        error += isValidAge();
        error += isValidGender();
        error += isValidActivityLevel();
        if (error.equals(""))
        {
            error += doUserExist();
            return (error.equals(""));
        }
        return (error.equals(""));
    }

    private String doUserExist()
    {
        db = new HelperDB(getContext(), "userstats.db");
        if (db.isUsernameExists(stUsername))
        {
            return "Username Already Exists!";
        }
        else
        {
            return "";
        }
    }

    private String isValidWeight() {
        if (stWeight.isEmpty()) {
            return "Weight must not be empty.\n";
        }

        try {
            float weight = Float.parseFloat(stWeight);
            if (weight <= 0 || weight > 650) {
                return "Weight must be a positive value and not exceed 650.\n";
            }
        } catch (NumberFormatException e) {
            return "Invalid weight format.\n";
        }

        return "";
    }

    private String isValidHeight() {
        if (stHeight.isEmpty()) {
            return "Height must not be empty.\n";
        }

        try {
            float height = Float.parseFloat(stHeight);
            if (height <= 0 || height > 300) {
                return "Height must be a positive value and not exceed 300.\n";
            }
        } catch (NumberFormatException e) {
            return "Invalid height format.\n";
        }

        return "";
    }

    private String isValidAge() {
        if (stAge.isEmpty()) {
            return "Age must not be empty.\n";
        }

        try {
            int age = Integer.parseInt(stAge);
            if (age <= 0 || age > 120) {
                return "Age must be a positive value and not exceed 120.\n";
            }
        } catch (NumberFormatException e) {
            return "Invalid age format.\n";
        }

        return "";
    }

    private String isValidGender() {
        if(genderRadioGroup.getCheckedRadioButtonId() == -1)
        {
            return "Select a gender.\n";
        }

        return "";
    }

    private String isValidActivityLevel() {
        if (activityLevelRadioGroup.getCheckedRadioButtonId() == -1) {
            return "Select an activity level.\n";
        }
        return "";
    }

    public void showAlertDialog()
    {
        adb.setTitle("Invalid stats");
        adb.setCancelable(false);
        adb.setMessage(error);
        adb.setNeutralButton("Ok", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) {}});
        adb.setNegativeButton("Delete", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) {resetFields();}});
        adb.create().show();
    }
    public void resetFields() {
        weightEditText.setText("");
        heightEditText.setText("");
        ageEditText.setText("");
        genderRadioGroup.clearCheck();
        activityLevelRadioGroup.clearCheck();
    }


}
