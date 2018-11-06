package com.example.arbeit.a01_currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editDollar;
    EditText editEuro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editDollar = findViewById(R.id.dollarText);
        editEuro = findViewById(R.id.euroText);
    }

    public void onClickConDol(View v){
        try {
            Double dollar = Double.parseDouble(editEuro.getText().toString())*1.15;
            editDollar.setText(dollar.toString());
        }
        catch (Exception ex ){
            Toast.makeText(this, "Error :" + ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public void  onClickConEu(View v){
        try {
            Double euro = Double.parseDouble(editDollar.getText().toString())*0.87;
            editEuro.setText(euro.toString());
        }
        catch (Exception ex){
            Toast.makeText(this, "Error :" + ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
