package com.yeapp.h24picasso.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yeapp.h24picasso.R;
import com.yeapp.h24picasso.utils.Constants;
import com.yeapp.h24picasso.utils.ProgressDialogWithTimeout;
import com.yeapp.h24picasso.utils.WebOperation;

/**
 * Created by Iacopo Peri on 26/01/17 01:34.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";

    EditText _userText;
    EditText _passwordText;
    Button _loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _loginButton = (Button) findViewById(R.id.btn_login);
        _loginButton.setOnClickListener(this);

        _userText = (EditText) findViewById(R.id.input_user);
        _passwordText = (EditText) findViewById(R.id.input_password);
    }

    private void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            Toast.makeText(getBaseContext(), "Missing data. Validation failed", Toast.LENGTH_LONG).show();
            _loginButton.setEnabled(true);
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialogWithTimeout pDiag = new ProgressDialogWithTimeout(LoginActivity.this);
        pDiag.setIndeterminate(true);
        pDiag.show("Connecting...", "Connecting to \"Gestionenumeroverde\"", 10000);

        String user = _userText.getText().toString();
        String password = _passwordText.getText().toString();

        new AsyncTask<String, Void, Bundle>(){

            @Override
            protected Bundle doInBackground(String... strings) {
                return WebOperation.tryLogin(strings[0], strings[1]);
            }

            @Override
            protected void onPostExecute(final Bundle s) {
                pDiag.dismiss();
                if (s.getBoolean(Constants.loginResult)) {
                    Log.d("Background", "Loggato");
                    onLoginSuccess();
                } else {
                    _loginButton.setEnabled(true);
                    Log.d("Background", "Stringa " + s + ", errore");
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Login Error")
                            .setMessage(s.getString(Constants.loginRespMessage))
                            .setCancelable(true)
                            .create()
                            .show();
                }
            }
        }.execute(user, password);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

//    public void onLoginFailed() {
//        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
//
//        _loginButton.setEnabled(true);
//    }

    public boolean validate() {
        boolean valid = true;

        String user = _userText.getText().toString();
        String password = _passwordText.getText().toString();

        if (user.isEmpty()) {
            _userText.setError("Enter a valid user");
            valid = false;
        } else {
            _userText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View view) {
        login();
    }
}
