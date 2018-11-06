package com.example.arbeit.a00_helloworld;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextHeight;
    EditText editTextWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWidth = findViewById(R.id.editTextWidth);
    }

    //button calculate click
    public void onClickButtonCalculate(View v) {


        Integer area = Integer.parseInt(editTextHeight.getText().toString()) * Integer.parseInt(editTextWidth.getText().toString());

        long time = 500;
        while (area > 0) {
            Toast.makeText(this, "Daaaaab" + String.valueOf(area), Toast.LENGTH_LONG).show();
            
            area--;
        }
    }
}