package com.yeapp.h24picasso.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Iacopo Peri on 19/01/17 18:04.
 */

public class ProgressDialogWithTimeout extends ProgressDialog {

    private ProgressDialog pDiag;
    private Context currentContext;
    Handler handler;

    public ProgressDialogWithTimeout(Context context) {
        super(context);
        init(context);
    }

    public ProgressDialogWithTimeout(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    private void init(Context context){
        pDiag = new ProgressDialog(context);
        currentContext = context;
        handler = new Handler();
    }

    public void show(String title, String msg, long millis){
        if (!title.isEmpty()) {
            pDiag.setTitle(title);
        }
        pDiag.setMessage(msg);
        pDiag.setIndeterminate(false);
        pDiag.setCancelable(false);
        pDiag.show();

        handler.postDelayed(new ExitTask(), millis);
    }

    public void show(long millis){
        pDiag.show();
        handler.postDelayed(new ExitTask(), millis);
    }

    @Override
    public void dismiss() {
        handler.removeCallbacksAndMessages(null);
        pDiag.dismiss();
    }

    private class ExitTask implements Runnable {

        @Override
        public void run() {

            if(pDiag!=null){
                pDiag.dismiss();
            }
            new AlertDialog.Builder(currentContext)
                    .setTitle("Error")
                    .setCancelable(true)
                    .create()
                    .show();
        }
    }
}
