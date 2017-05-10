package com.yeapp.h24picasso.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.yeapp.h24picasso.activity.ChangeH24;
import com.yeapp.h24picasso.utils.Constants;
import com.yeapp.h24picasso.utils.GeneralUtils;
import com.yeapp.h24picasso.utils.WebOperation;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Iacopo Peri on 03/02/17 02:56.
 */
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AsyncTask<Void, Void, Bundle>(){

            @Override
            protected Bundle doInBackground(Void... strings) {
                Bundle b = new Bundle();

                try {
                    b = WebOperation.tryLogin(Constants.Connection.USER, Constants.Connection.PWD);
                } catch (IOException e) {
                    b=null;
                }
                return b;
            }

            @Override
            protected void onPostExecute(Bundle s) {
                if(s==null){
                    new AlertDialog.Builder(Splash.this)
                            .setTitle("Connection problem")
                            .setMessage("Check your connection and try to execute again")
                            .setCancelable(false)
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    finish();
                                }
                            })
                            .create()
                            .show();
                }else {
                    Intent intent = new Intent(getBaseContext(), ChangeH24.class);
                    if (!s.isEmpty()) {
                        intent.putExtra(Constants.loginResult, s.getBoolean(Constants.loginResult));
                        intent.putExtra(Constants.loginRespCode, s.getInt(Constants.loginRespCode));
                        intent.putExtra(Constants.loginRespMessage, s.getString(Constants.loginRespMessage));
                    }
                    startActivity(intent);
                    finish();
                }
            }
        }.execute();
    }
}
