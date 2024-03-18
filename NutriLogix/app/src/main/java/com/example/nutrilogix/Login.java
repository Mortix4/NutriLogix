package com.example.nutrilogix;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nutrilogix.Classes.HelperDB;
import com.example.nutrilogix.Services.ttsService;
import java.util.Locale;

public class Login extends AppCompatActivity {

    Context context;
    String[] speaker = {"One", "Two", "Three"};
    TextView tvCount;
    CountDownTimer cdt;
    TextToSpeech tts;
    int c, language;
    EditText etUsername, etPass;
    Button loginButton, regButton;
    String stUsername, stPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitServices();
        InitComp();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                showConfirmationDialog();
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to proceed to the login page?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startLoginCountdown();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.show();
    }

    private void startLoginCountdown() {
        c = 3;
        cdt = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {
                tvCount.setText(String.valueOf(c));
                if (c > 0) {
                    c--;
                }

                if (c >= 0) {
                    initializeTTS();
                    if (language == TextToSpeech.LANG_AVAILABLE) {
                        speakNumber(speaker[c]);
                    } else {
                        showToast("Text To Speech not available in the selected language.");
                    }
                }
            }


            @Override
            public void onFinish() {
                if (checkCredentials()) {
                    startActivity(new Intent(Login.this, FragmentsMain.class));
                } else {
                    showToast("Incorrect credentials. Please try again.");
                }
            }
        };
        cdt.start();
    }

    private void initializeTTS() {
        if (tts == null) {
            tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = tts.setLanguage(Locale.US);
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.e("TTS", "Language is not available or not supported");
                        }
                    } else {
                        Log.e("TTS", "Initialization failed with status: " + status);
                    }
                }

            });
        }
    }

    private void speakNumber(String number) {
        if (tts != null && language == TextToSpeech.LANG_AVAILABLE) {
            tts.speak(number, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private boolean checkCredentials() {
        getStringsFromEditTexts();
        HelperDB db = new HelperDB(this, "users.db");
        return !stUsername.isEmpty() && !stPass.isEmpty() && db.isExists(stUsername);
    }


    private void showToast(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), message, duration);
        toast.show();
    }

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

        if (id == R.id.itBack) {}
            //finish();
        if (id == R.id.itExit)
            finishAffinity();
        if (id == R.id.itGuide) {}
        return true;

    }

    public void InitComp()
    {
        etUsername = findViewById(R.id.usernameEditText);
        etPass = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        regButton = findViewById(R.id.regBtn);
        context = this;
        tvCount = findViewById(R.id.tvCount);
    }

    public void InitServices()
    {
        Intent goService = new Intent(Login.this, ttsService.class);
        goService.putExtra("read", "Welcome To Login Form");
        startService(goService);
    }

    public void getStringsFromEditTexts()
    {
        stUsername = etUsername.getText().toString();
        stPass = etPass.getText().toString();
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

}
