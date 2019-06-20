package com.example.a07_weatheranimation;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Magnifier;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private String type = "precip";
    private static Async animator;
    private ImageView imageView;
    private final static int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        if (animator != null) {
            animator.reconnect(imageView, this);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            performDownload(type);
        } else {
            Toast.makeText(this, "External Storage Error! (Permission denied!)", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
        }
    }

    //menu inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void tempClick(MenuItem menuItem) {
        type = "temp";
        performDownload(type);
        startAnimation(type);
    }

    public void cloudClick(MenuItem menuItem) {
        type = "cloud";
        performDownload(type);
        startAnimation(type);
    }

    public void precipClick(MenuItem menuItem) {
        type = "precip";
        performDownload(type);
        startAnimation(type);

    }

    //start button
    public void start(View view) {
        try {
            performDownload(type);
            startAnimation(type);
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void startAnimation(String type) {
        if(animator!=null)
            animator.cancel(true);

        animator = new Async(imageView, this);
        animator.execute(type);
    }

    //stop button
    public void stop(View view) {
        animator.cancel(true);
    }

    //refresh button
    public void refresh(View view) {
        performDownload(type);
        startAnimation(type);
    }

    //check if permission has been granted
    public void onRequestPermissionsResult(int requestCode, @NonNull String permission[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_WRITE_EXTERNAL_STORAGE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "External Storage Permission granted", Toast.LENGTH_LONG).show();
                    performDownload(type);
                } else {
                    Toast.makeText(this, "External Storage Permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //download pictures
    private void performDownload(String type) {
        //start a intent
        Intent intent = new Intent(this, Download.class);
        //add the type
        intent.putExtra("type", type);
        startService(intent);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            startAnimation(type);
        }
    };
}
