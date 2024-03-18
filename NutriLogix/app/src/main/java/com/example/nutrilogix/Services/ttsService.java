package com.example.nutrilogix.Services;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import java.util.Locale;
import android.app.Service;

import androidx.annotation.Nullable;import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;


public class ttsService extends Service
{
    TextToSpeech tts;
    int language;
    String st;

    public ttsService() {}
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        st = intent.getStringExtra("read");
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                language = tts.setLanguage(Locale.US);
                if (i == TextToSpeech.SUCCESS)
                    tts.speak(st, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

}

