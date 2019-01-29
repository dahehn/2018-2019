package com.example.woerk.a04_sangcollection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Album album;
    ArrayAdapter adapter;
    ListView listViewOverview;
    public static final int REQUEST_CODE_ADD_SONG = 10001;
    public static final int REQUEST_CODE_MODIFY_SONG = 10002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        album = new Album();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, album.allSongs())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(album.allSongs().get(position).getName());
                text2.setText(album.allSongs().get(position).getArtist());
                return view;
            }
        };
        listViewOverview = findViewById(R.id.listViewOverview);
        listViewOverview.setAdapter(adapter);

        listViewOverview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Song song = (Song) adapter.getItem(position);
                editSong(song);
            }
        });

        listViewOverview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Song song = (Song) adapter.getItem(position);
                deleteSong(position);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_meu, menu);
        return true;
    }

    public void onClick_MenuAdd(MenuItem m) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("requestCode", REQUEST_CODE_ADD_SONG);
        startActivityForResult(intent, REQUEST_CODE_ADD_SONG);


    }
    public void onClick_GetTotalPlaytime(MenuItem m){
        int totalplaytime = album.totalPlaytime();
        Toast.makeText(this, "TotalplayTime: " + Integer.toString(totalplaytime) + " min", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //adapter = new ArrayAdapter<Song>(this, android.R.layout.simple_expandable_list_item_2, album.allSongs());
        //listViewOverview.invalidateViews();
        adapter.notifyDataSetChanged();

    }
    private void editSong(Song song){
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("requestCode", REQUEST_CODE_MODIFY_SONG);
        intent.putExtra("Song", song);
        startActivityForResult(intent, REQUEST_CODE_MODIFY_SONG);

    }
    private void deleteSong(final int index){
        AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        alert.setTitle("Dialog for removal");
        alert.setMessage("Do you really want to delete this song?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                album.deleteSong(index);
                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
