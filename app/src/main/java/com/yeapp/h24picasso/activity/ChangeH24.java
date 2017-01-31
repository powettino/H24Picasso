package com.yeapp.h24picasso.activity;

import android.app.Activity;
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
import com.yeapp.h24picasso.utils.GeneralUtils;

import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParserException;

import java.net.HttpURLConnection;
import java.util.HashMap;

public class ChangeH24 extends Activity implements View.OnClickListener{

    private static final int CODE_FOR_LOGIN = 0;

    private ProgressDialogWithTimeout pDiag;
    private Button stopSpin;
    private Button logButton;

    HttpURLConnection http;

    GetPanelTask gpt = new GetPanelTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_h24);

        stopSpin = (Button)findViewById(R.id.stopSpinButton);
        stopSpin.setOnClickListener(this);

        logButton = (Button) findViewById(R.id.logButton);
        logButton.setOnClickListener(this);

        pDiag = new ProgressDialogWithTimeout(ChangeH24.this);
        pDiag.show("Connecting...", "Connecting to \"Gestionenumeroverde\"", 10000);
        new AsyncTask<Void, Void, Bundle>(){

            @Override
            protected Bundle doInBackground(Void... strings) {
                return WebOperation.tryLogin(Constants.Connection.USER, Constants.Connection.PWD);
            }

            @Override
            protected void onPostExecute(final Bundle s) {
                if (s.getBoolean(Constants.loginResult)) {
                    Log.d("Background", "Loggato");
                    gpt.execute();
                } else {
                    Log.d("Background", "Stringa " + s + ", errore");
                    pDiag.dismiss();
                    new AlertDialog.Builder(ChangeH24.this)
                            .setTitle("Login Error")
                            .setMessage(s.getString(Constants.loginRespMessage))
                            .setCancelable(true)
                            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    if(s.getInt(Constants.loginRespCode)==200) {
                                        Intent login = new Intent(getBaseContext(), LoginActivity.class);
                                        startActivityForResult(login, CODE_FOR_LOGIN);
                                    }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == CODE_FOR_LOGIN) {
            gpt.execute();
        }
    }

    private class GetPanelTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... strings) {
            Log.d("PANEL", "Lancio la ricerca dei campi");
            return WebOperation.getPanel();
        }

        @Override
        protected void onPostExecute(String s) {
            pDiag.dismiss();
            Log.d("PANEL", "ok");
            Elements listCoppie = GeneralUtils.getElementsList(s, "tr[class='nero12']");
            HashMap<String, String> listGiorni = GeneralUtils.getDaysNames(listCoppie, "td");
            Log.d("PANEL", "Risultato ottenuto");
        }
    }
}
