package com.example.woerk.a06_timer;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncActivity extends AsyncTask<Integer,Integer,String> {
    private TextView textViewTime;
    private ProgressBar progressBar;

    public AsyncActivity(ProgressBar progressBar, TextView textViewResult) {
        reconnect(progressBar,textViewResult);
    }

    public void reconnect(ProgressBar progressBar, TextView textViewResult) {
        this.progressBar = progressBar;
        this.textViewTime = textViewResult;
    }

    @Override
    protected String doInBackground(Integer... integers) {
       Integer time = integers[0];
       progressBar.setMax(time);
       while(time>0)
       {
           doFakeWork(1000);

           publishProgress(time);
           if(isCancelled())break;
           time--;
       }
       return "fin";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
        Integer mins = values[0]/60;
        Integer secs = values[0] - mins *60;
        if(mins<10)
            textViewTime.setText("0"+mins + ":" + secs);
        else
            textViewTime.setText(mins + ":" + secs);
    }

    private void doFakeWork(int millis) {
        try {
            // the fake work consists of some sleeping ;-)
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace(); // show the stack trace if the thread was interrupted during sleeping
        }
    }
}
