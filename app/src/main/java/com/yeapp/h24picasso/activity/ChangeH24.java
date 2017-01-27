package com.yeapp.h24picasso.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yeapp.h24picasso.R;
import com.yeapp.h24picasso.utils.WebOperation;
import com.yeapp.h24picasso.utils.Constants;
import com.yeapp.h24picasso.utils.ProgressDialogWithTimeout;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChangeH24 extends Activity implements View.OnClickListener{

    private static final int CODE_FOR_LOGIN = 0;

    private ProgressDialogWithTimeout pDiag;
    private Button stopSpin;
    private Button logButton;

    HttpURLConnection http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_h24);

        stopSpin = (Button)findViewById(R.id.stopSpinButton);
        stopSpin.setOnClickListener(this);

        logButton = (Button) findViewById(R.id.logButton);
        logButton.setOnClickListener(this);

        pDiag = new ProgressDialogWithTimeout(ChangeH24.this);
        pDiag.show("Connecting", "Connecting to \"Gestionenumeroverde\"", 10000);
        new AsyncTask<Void, Void, Bundle>(){

            @Override
            protected Bundle doInBackground(Void... strings) {
                return WebOperation.TryLogin(Constants.Connection.USER, Constants.Connection.PWD);
            }

            @Override
            protected void onPostExecute(Bundle s) {
                pDiag.dismiss();
                if (s.getBoolean(Constants.loginResult)) {
                    Log.d("Background", "Loggato");
                } else {
                    Log.d("Background", "Stringa " + s + ", errore");
                    new AlertDialog.Builder(ChangeH24.this)
                            .setTitle("Login Error")
                            .setMessage(s.getString(Constants.loginRespMessage))
                            .setCancelable(true)
                            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    Intent login = new Intent(getBaseContext(), LoginActivity.class);
                                 startActivityForResult(login,CODE_FOR_LOGIN);
                                }
                            })
                            .create()
                            .show();
                }
            }
        }.execute();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.stopSpinButton: {
                Log.d("Log prova", "sto cliccano il bottone di stop");
                pDiag.dismiss();
            }
            case R.id.logButton:
            {

            }
            default:
                break;
        }
    }
}
