package com.example.woerk.a04_songcollection;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {


    public static final int REQUEST_CODE_ADD_SONG = 10001;
    public static final int REQUEST_CODE_MODIFY_SONG = 10002;
    private int requestCode;
    private Song oldSong;
    private Album album;


    EditText eT_artist;
    EditText eT_title;
    EditText eT_playtime;
    EditText eT_trackNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        album = new Album();
        eT_artist = (EditText) findViewById(R.id.editText_Artist);
        eT_title = (EditText) findViewById(R.id.editText_Title);
        eT_playtime = (EditText) findViewById(R.id.editText_Playtime);
        eT_trackNumber = (EditText) findViewById(R.id.editText_Tracknumber);

        requestCode = getIntent().getExtras().getInt("requestCode");

        if (requestCode == REQUEST_CODE_MODIFY_SONG) {
            oldSong = (Song) getIntent().getSerializableExtra("Song");
            eT_title.setText(oldSong.getName());
            eT_artist.setText(oldSong.getArtist());
            eT_playtime.setText(Integer.toString(oldSong.getPlaytime()));
            eT_trackNumber.setText(Integer.toString(oldSong.getTracknumber()));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    public void onClick_MenuSaveSong(MenuItem m) {
        String artist = eT_artist.getText().toString();
        String title = eT_title.getText().toString();
        int playtime = Integer.parseInt(eT_playtime.getText().toString());
        int trackNumber = Integer.parseInt(eT_trackNumber.getText().toString());
        Song newSong = new Song(trackNumber, title, artist, playtime);
        if (requestCode == REQUEST_CODE_ADD_SONG) {
            if (album.addSong(newSong) == false) {
                Error("Track number must be unique");
            } else {
                finish();
            }
        }
        else if (requestCode == REQUEST_CODE_MODIFY_SONG) {
            album.updateSong(newSong);
            finish();
        }
    }

    public void Error(String message)
    {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setTitle("Somthing went wrong");
        alertDialog.setMessage(message);
        alertDialog.setNeutralButton("Ok",null);
        alertDialog.show();

    }
}
