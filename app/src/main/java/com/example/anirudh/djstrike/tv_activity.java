package com.example.anirudh.djstrike;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class tv_activity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile3";
    public Switch sw1;
    public Switch sw2;
    public Switch sw3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_activity);
        sw1 = (Switch) findViewById(R.id.switch1);
        sw2 = (Switch) findViewById(R.id.switch2);
        sw3 = (Switch)findViewById(R.id.switch3);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        sw1.setChecked(prefs.getBoolean("switch1",false));
        sw2.setChecked(prefs.getBoolean("switch2",false));

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sw3.isChecked()){
                    sw1.setChecked(true);
                    sw2.setChecked(true);
                }
                else{
                    sw1.setChecked(false);
                    sw2.setChecked(false);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean("switch1", sw1.isChecked());
        editor.putBoolean("switch2", sw2.isChecked());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean("switch1", sw1.isChecked());
        editor.putBoolean("switch2", sw2.isChecked());
        editor.apply();
    }
}
