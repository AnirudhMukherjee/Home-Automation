package com.example.anirudh.djstrike;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class LightActivity extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public Switch sw1;
    public Switch sw2;
    public Switch sw3;
    public Switch sw4;
    public Switch sw5;

    BluetoothDevice device;
    BluetoothSocket btSocket;
    BluetoothAdapter adapter;
    OutputStream outputStream = null;

    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
         sw1 = (Switch) findViewById(R.id.switch1);
         sw2 = (Switch) findViewById(R.id.switch2);
         sw3 = (Switch) findViewById(R.id.switch3);
         sw4 = (Switch) findViewById(R.id.switch4);
         sw5 = (Switch)findViewById(R.id.switch5);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        sw1.setChecked(prefs.getBoolean("switch1",false));
        sw2.setChecked(prefs.getBoolean("switch2",false));
        sw3.setChecked(prefs.getBoolean("switch3",false));
        sw4.setChecked(prefs.getBoolean("switch4",false));
        sw5.setChecked(prefs.getBoolean("switch5",false));
        searchdevices();

        try {
            btSocket = device.createRfcommSocketToServiceRecord(BTMODULEUUID);
            Toast.makeText(getApplicationContext(), "Socket Creation Successful", Toast.LENGTH_SHORT).show();

            try {
                btSocket.connect();
                Toast.makeText(getApplicationContext(), "Device connection Successful", Toast.LENGTH_SHORT).show();
                try {
                    outputStream = btSocket.getOutputStream();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Output Stream Connection failed", Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                btSocket.close();
                Toast.makeText(getApplicationContext(), "Device Connection failed", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Socket Creation failed", Toast.LENGTH_SHORT).show();
        }

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sw1.isChecked()) {
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write("1".getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Unable to send data", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write("5".getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Unable to send data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sw2.isChecked()) {
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write("2".getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Unable to send data", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write("6".getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Unable to send data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sw3.isChecked()) {
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write("3".getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Unable to send data", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write("7".getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Unable to send data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sw4.isChecked()) {
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write("4".getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Unable to send data", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write("8".getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Unable to send data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        sw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sw5.isChecked()){
                    sw1.setChecked(true);
                    sw2.setChecked(true);
                    sw3.setChecked(true);
                    sw4.setChecked(true);
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write("9".getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Unable to send data", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    sw1.setChecked(false);
                    sw2.setChecked(false);
                    sw3.setChecked(false);
                    sw4.setChecked(false);
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write("0".getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Unable to send data", Toast.LENGTH_SHORT).show();
                    }

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
        editor.putBoolean("switch3", sw3.isChecked());
        editor.putBoolean("switch4", sw4.isChecked());
        editor.putBoolean("switch5", sw5.isChecked());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean("switch1", sw1.isChecked());
        editor.putBoolean("switch2", sw2.isChecked());
        editor.putBoolean("switch3", sw3.isChecked());
        editor.putBoolean("switch4", sw4.isChecked());
        editor.putBoolean("switch5", sw5.isChecked());
        editor.apply();
    }


    private void searchdevices()
    {
        adapter=BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevice=adapter.getBondedDevices();
        ArrayList<String> s=new ArrayList<>();
        for(BluetoothDevice bt:pairedDevice)
        {
            s.add(bt.getName());
            if(bt.getName().equalsIgnoreCase("HC-05")) {
                device = adapter.getRemoteDevice(bt.getAddress());
            }
        }
    }

}
