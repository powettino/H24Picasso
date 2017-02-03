package com.yeapp.h24picasso.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

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
        if(pDiag!=null && !pDiag.isShowing()) {
            if (!title.isEmpty()) {
                pDiag.setTitle(title);
            }
            pDiag.setMessage(msg);
            pDiag.setIndeterminate(false);
            pDiag.setCancelable(false);
            pDiag.show();

            handler.postDelayed(new ExitTask(), millis);
        }
    }

    public void show(long millis){
        if(pDiag!=null && !pDiag.isShowing()) {
            pDiag.show();
            handler.postDelayed(new ExitTask(), millis);
        }
    }

    @Override
    public void dismiss() {
        if(pDiag!=null && pDiag.isShowing()) {
            handler.removeCallbacksAndMessages(null);
            pDiag.dismiss();
        }
    }

    private class ExitTask implements Runnable {

        @Override
        public void run() {

            if(pDiag!=null){
                pDiag.dismiss();
            }
            Log.d("DIAG", "Scattato il timeout");
            new AlertDialog.Builder(currentContext)
                    .setTitle("Generic Error")
                    .setMessage("Timeout on request")
                    .setCancelable(true)
                    .create()
                    .show();
        }
    }
}
