package com.example.recorder;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.FontsContract;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class recordService extends IntentService {


    private static final String PATH = "PATH";
    private MediaRecorder mRecorder;

    private static final String EXTRA_PARAM2 = "com.example.recorder.extra.PARAM2";
    String path=Environment.getDataDirectory().getAbsolutePath()+"/Untitled.3gp";

    public recordService() {
        super("recordService");
    }




    @Override
    protected void onHandleIntent(Intent intent) {
        mRecorder = new MediaRecorder();
        boolean start = intent.getBooleanExtra("Start",false);
        if(start)
        {
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile(path);
            try {
                mRecorder.prepare();
                mRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        if(!start)
        {
            try {
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
    public String checkExistense(Integer num){
        if (new File(Environment.getDataDirectory().getAbsolutePath()+"Untitled"+num+".3gp").exists())
            checkExistense(num++);

        return "/Untitled"+num;
    }
}
