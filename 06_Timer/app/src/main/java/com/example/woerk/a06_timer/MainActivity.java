package com.example.woerk.a06_timer;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static AsyncActivity runner;
    Integer time = 0;
    TextView textViewTime;
    ProgressBar progressBar;
    TextView textView3;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTime = findViewById(R.id.textViewTime);
        progressBar = findViewById(R.id.progressBar);
        textView3 = findViewById(R.id.textView3);
        textView = findViewById(R.id.textView);

        if (runner != null) {
            runner.reconnect(progressBar, textViewTime);
        }
    }

    public void plusClick(View view){
        try {
            time++;
            if(time<10)
            {
                textViewTime.setText("0"+time.toString()+":00");
                textView3.setText("0"+time.toString()+":00");
            }

            else{
                textViewTime.setText(time.toString()+":00");
                textView3.setText(time.toString()+":00");
            }

            progressBar.setMax(time*60);
        }
        catch (Exception ex) {
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
            alertDialog.setTitle("Somthing went wrong");
            alertDialog.setMessage(ex.getMessage()+"\n");
            alertDialog.setNeutralButton("Ok",null);
            alertDialog.show();
        }
    }

    public void minusClick(View view){
        try {
            time--;
            if(time<10)
            {
                textViewTime.setText("0"+time.toString()+":00");
                textView3.setText("0"+time.toString()+":00");
            }

            else{
                textViewTime.setText(time.toString()+":00");
                textView3.setText(time.toString()+":00");
            }

            progressBar.setMax(time*60);
        }
        catch (Exception ex) {
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
            alertDialog.setTitle("Somthing went wrong");
            alertDialog.setMessage(ex.getMessage()+"\n");
            alertDialog.setNeutralButton("Ok",null);
            alertDialog.show();
        }
    }

    public void resetClick(View view){
        runner.cancel(true);
    }

    public void startClick(View view){
        if(textViewTime.getText().toString().contains("00:00")) {
            Toast.makeText(this,"Please use the buttons to enter a time",Toast.LENGTH_LONG).show();
            return;
        }
        runner = new AsyncActivity(progressBar,textViewTime);
        runner.execute(time*60);
    }
}
