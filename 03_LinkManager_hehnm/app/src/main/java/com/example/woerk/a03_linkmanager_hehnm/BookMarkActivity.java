package com.example.woerk.a03_linkmanager_hehnm;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BookMarkActivity extends AppCompatActivity {

    EditText editTextShortName;
    EditText editTextLink;

    public void saveClick(View view) {
        String shortName = editTextShortName.getText()+"";
        String address = editTextLink.getText()+"";

        if(address.isEmpty() || address.contains(" ") || shortName.isEmpty()){
            Error("Invalid address or Short name","Please change your input");
        }
        else{
            Intent intent = new Intent();
            Bookmark bookmark = new Bookmark(shortName,address);
            intent.putExtra("bookmark",bookmark.toString());
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    public void testConnectionClick(View view) {
        String address = editTextLink.getText().toString();
        if(address.isEmpty() || address.equals(" ")){
            Error("Invalid address","Enter a valid address");
        }
        else{
            try {
                if (!address.startsWith("http://") || !address.startsWith("https://")){
                    address = "https://"+address;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(address)); startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(address)); startActivity(intent);
                }
            }
            catch (Exception ex){
                Error("Invalid address","Change your address");
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        editTextLink = findViewById(R.id.editTextLink);
        editTextShortName = findViewById(R.id.editTextShortName);
    }

    private void Error(String title, String message){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton("OK",null)
                .show();
    }
}