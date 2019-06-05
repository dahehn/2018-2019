package com.example.recorder;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaExtractor;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = "AudioRecording";
    private static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/AudioRecording.3gp";
    }
    public void start(View view){
        if(CheckPermissions()) {
            Toast.makeText(getApplicationContext(), "Recording Started", Toast.LENGTH_LONG).show();
            startRecording(true);
        }
        else
        {
            RequestPermissions();
        }
    }

    public void stop(View view){
       startRecording(false);

        Toast.makeText(getApplicationContext(), "Recording Stopped", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length> 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] ==  PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                    } else { Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
    public boolean CheckPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }
    private void RequestPermissions() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }
    private void startRecording(boolean start){
        Toast.makeText(this, "down", Toast.LENGTH_LONG).show();
        //start a intent
        Intent intent = new Intent(this, recordService.class);
        //append the type
        intent.putExtra("Start",start);
        startService(intent);
    }
}
