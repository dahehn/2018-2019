package com.example.woerk.a02_gui_io_hehnm;

import android.app.DatePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class  MainActivity extends AppCompatActivity {

    EditText lastname , income, birthdate, firstname;
    private  static final String key_firstname="key_firstname";
    private  static final String key_lastname="key_lastname";
    private static final String key_income="key_income";
    private static final String key_birthdate="key_birthdate";
    String pattern;
    Format dateFormat;
    TextView first, last, inc, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = findViewById(R.id.editTextfirstname);
        lastname = findViewById(R.id.editTextlastname);
        income = findViewById(R.id.editTextincome);
        birthdate= findViewById(R.id.editTextbirthdate);
        dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        pattern = ((SimpleDateFormat) dateFormat).toLocalizedPattern();

        first = findViewById(R.id.textView);
        last = findViewById(R.id.textView2);
        inc = findViewById(R.id.textView3);
        date = findViewById(R.id.textView4);
        //onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){
            String savedFirstName = savedInstanceState.getString(key_firstname);
            first.setText(savedFirstName);
            String savedLastName = savedInstanceState.getString(key_lastname);
            last.setText(savedLastName);
            String savedIncome = savedInstanceState.getString(key_income);
            inc.setText(savedIncome);
            String savedDate = savedInstanceState.getString(key_birthdate);
            date.setText(savedDate);
        }
        else {
            Toast.makeText(this, "New entry", Toast.LENGTH_LONG).show();
        }
    }
    public void editTextDate1_Click(View v) {
        // parse the string to a calender object
        Calendar c = parseDate(birthdate.getText().toString());
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
    public Calendar parseDate(String dateStr) {
        // create a calender object
        // the date is today by default
        Calendar c = Calendar.getInstance();
        try {
            // try to parse the entered string with the given pattern and locale
            Date myDate = new SimpleDateFormat(pattern, Locale.getDefault()).parse(dateStr);
            // change the date of the calendar to the parsed date
            c.setTime(myDate);
        }
        catch (ParseException e) {
            Toast.makeText(this, "No valid date entered!", Toast.LENGTH_LONG).show();
        }
        return c;
    }
    public void Save(View view){
        try {
            if(firstname.getText().toString().isEmpty() || lastname.getText().toString().isEmpty() || income.getText().toString().isEmpty() || birthdate.getText().toString().isEmpty()) {
                throw new Exception("One or more values are empty!");
            }
            else {
                Double income1 = Double.parseDouble(income.getText().toString());
                if(income1 < 0){
                    throw new Exception("Income cannot be negative!");
                }
                else{
                    first.setText(firstname.getText().toString().trim());
                    last.setText(lastname.getText().toString().trim());
                    inc.setText(income.getText().toString());
                    date.setText(birthdate.getText().toString());
                }
            }
        }
        catch (Exception ex) {
            AlertDialog obj = new AlertDialog.Builder(this).create();
            obj.setTitle("Error!");
            obj.setMessage(ex.getMessage());
            obj.show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString(key_firstname, first.getText().toString());
        savedInstanceState.putString(key_lastname, last.getText().toString());
        savedInstanceState.putString(key_income, inc.getText().toString());
        savedInstanceState.putString(key_birthdate, date.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }
}
