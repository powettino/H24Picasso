package com.yeapp.h24picasso.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yeapp.h24picasso.R;
import com.yeapp.h24picasso.utils.ProgressDialogWithTimeout;

public class ChangeH24 extends Activity implements View.OnClickListener{


    private ProgressDialogWithTimeout pDiag;
    private Button stopSpin;
    private Button logButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_h24);
//        startSpinner("Prova");

        stopSpin = (Button)findViewById(R.id.stopSpinButton);
        stopSpin.setOnClickListener(this);

        logButton = (Button) findViewById(R.id.logButton);
        logButton.setOnClickListener(this);
    }

    private void stopSpinner() {
        if (pDiag.isShowing()) {
            pDiag.dismiss();
        }
    }

    private void startSpinner(String msg) {
        if (pDiag == null || !pDiag.isShowing()) {
            pDiag = new ProgressDialogWithTimeout(ChangeH24.this);
            pDiag.setMessage(msg);
            pDiag.setIndeterminate(false);
            pDiag.setCancelable(false);
            pDiag.show(5000);
        }
    }

//    @Override
//    public void onPause() {
//        if (pDiag != null && pDiag.isShowing()) {
//            pDiag.dismiss();
//        }
//        super.onPause();
//    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.stopSpinButton: {
                Log.d("Log prova", "sto cliccano il bottone di stop");
                pDiag.dismiss();
            }
            case R.id.logButton:
            {
                Log.d("Log prova", "sto cliccano il bottone di log");
                
            }
            default:
                break;
        }
    }
}
