package com.example.anirudh.djstrike;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private TextView welc;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        welc = findViewById(R.id.welcome);
        Typeface custom = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-Regular.ttf");
        welc.setTypeface(custom);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
            }
        },SPLASH_TIME_OUT);
    }
}
