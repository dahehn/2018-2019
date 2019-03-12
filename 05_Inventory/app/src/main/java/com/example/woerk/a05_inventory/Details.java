package com.example.woerk.a05_inventory;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Details extends AppCompatActivity {

    String pattern;
    Format dateFormat;
    EditText editTextDeviceName;
    EditText editTextSerialnumber;
    EditText editTextLocation;
    EditText editTextDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    public void dateClicker(View v){

        // parse the string to a calender object
        Calendar c = parseDate(.getText().toString());
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        // create a DatePickerDialog with the adjusted date and implemented event
        DatePickerDialog dialog = new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            // create a calendar and set it to the selected date
            Calendar c1 = Calendar.getInstance();
            c1.set(year, monthOfYear, dayOfMonth);
            // get a Date object of the calendar and format a string with the given pattern and locale
            Date myDate = c1.getTime();
            String dateStr = new SimpleDateFormat(pattern, Locale.getDefault()).format(myDate);
            birthdate.setText(dateStr);
        }, mYear, mMonth, mDay);
        dialog.show();
    }
}
