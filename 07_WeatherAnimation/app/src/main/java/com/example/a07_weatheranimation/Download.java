package com.example.a07_weatheranimation;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.content.Context;
import android.content.UriMatcher;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class Download extends IntentService {

    private static final String RESULT= "RESULT";
    private static final String NOTIFICATION = "com.example.a07_weatheranimation";
    //url to the images
    public static final String URL = "https://www.zamg.ac.at/aladin_anim/aladin_gifs/";


    //ctor
    public Download() {
        super("Download");
    }

    //magic
    @Override
    protected void onHandleIntent(Intent intent) {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.GERMAN).format(Calendar.getInstance(Locale.GERMAN).getTime());
        String time = new SimpleDateFormat("HH", Locale.GERMAN).format(Calendar.getInstance(Locale.GERMAN).getTime());
        //get type
        String type = intent.getStringExtra("type");
        //number string
        String nr="";
        //the more static part of the url
        String urlpart = urlBuilder(type,date,time);

        InputStream stream = null;
        FileOutputStream fos = null;
        //loop to download all files
        for(int i=4;i<=72;i++){
            //completing the numeration
            nr=i+"";
            //if index is <10 add a 0 for correct format
            if(i<10)
                nr="0"+i;
            //how the downloaded files will be named
            String filename =type+nr +".gif";
            //where they will be stored with the previously declared name
            File output;

            try {
                //complete the url
                URL url = new URL(urlpart+nr+".gif");
                //start the downloading
                stream = url.openConnection().getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                output = new File(Environment.getExternalStorageDirectory(), filename);
                fos = new FileOutputStream(output.getPath());
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                //publish the results
                publishResult(Activity.RESULT_OK);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                publishResult(Activity.RESULT_CANCELED);
            }
            finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        publishResult(Activity.RESULT_CANCELED);
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public String urlBuilder(String type,String date,String time) {
        //build part of the url according to specification
        Integer myTime = Integer.valueOf(time);
        if (myTime > 4 && myTime <= 10)
            return URL + "ALADIN_" + type + "_" + date + "_00s";

        if (myTime > 10 && myTime <= 16)
            return URL + "ALADIN_" + type + "_" + date + "_06s";

        if(myTime>16 && myTime <= 22)
            return URL + "ALADIN_" + type + "_" + date + "_12s";

        return URL + "ALADIN_" + type + "_" + date +"_18s";
    }

    private void publishResult(int result){
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(RESULT,result);
        sendBroadcast(intent);
    }
}
