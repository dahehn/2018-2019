package com.example.a07_weatheranimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.File;
import java.util.ArrayList;

public class Async extends AsyncTask<String,String,String> {

   private ImageView imageView;
   MainActivity main;

    public Async(ImageView imageView, MainActivity main) {
        this.imageView = imageView;
        this.main = main;

    }

    public void reconnect(ImageView imageV, MainActivity main) {
        this.imageView = imageV;
        this.main=main;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Bitmap bitmap = BitmapFactory.decodeFile(values[0]);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected String doInBackground(String... values) {
        try {

            for (Integer i = 4; i<72; i++){
                String type = values[0];

                if(isCancelled()) {
                    break;
                }

                if(i<10){
                    type += "0"+i+".gif";

                }
                else{
                    type += i+".gif";
                }

                String path="/sdcard/"+type;
                publishProgress(path);
                Thread.sleep(500);
            }
        }
        catch (Exception ex){
            return "animation failed";
        }
        return "animation complete";
    }
}
