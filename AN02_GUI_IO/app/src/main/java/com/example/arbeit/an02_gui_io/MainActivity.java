




package com.example.arbeit.an02_gui_io;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextBirthDate;
    EditText editTextIncome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goOnClick(){
        String firstname = editTextFirstName.getText().toString();
        String lastname = editTextLastName.getText().toString();
        
    }
}
