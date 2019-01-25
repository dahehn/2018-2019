package com.example.woerk.a03_linkmanager_hehnm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class  MainActivity extends AppCompatActivity {
    EditText editTextBookmarks;

    public static final int REQUEST_CODE_BOOKMARK=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextBookmarks = findViewById(R.id.editTextBookmarks);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_second,menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("bookmarks",editTextBookmarks.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_BOOKMARK) {
            if (resultCode == RESULT_OK) {
                String bookmark = data.getExtras().getString("bookmark");
                if (bookmark != null) {
                    editTextBookmarks.setText(editTextBookmarks.getText().toString() + bookmark + "\n");
                }
            }
        }
    }

    public void addBookmarkClick(MenuItem menuItem) {
        try {
            Intent intent = new Intent(this, BookMarkActivity.class);
            startActivityForResult(intent, REQUEST_CODE_BOOKMARK);
        }
       catch (Exception ex){
           new AlertDialog.Builder(this)
                   .setTitle("Error opening")
                   .setMessage(ex.getMessage())
                   .setNeutralButton("OK",null)
                   .show();
       }

    }

    public void loadLinkClick(MenuItem menuItem){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        editTextBookmarks.setText(sharedPref.getString("bookmarks", ""));
    }

    public void saveLinkClick(MenuItem menuItem){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("bookmarks",editTextBookmarks.getText().toString());

        editor.commit();
    }

    public void clearListClick(MenuItem menuItem) {
        editTextBookmarks.setText("");
    }

}
