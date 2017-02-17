package com.yeapp.h24picasso.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.yeapp.h24picasso.R;

public class ModifyName extends AppCompatActivity {
Spinner spinner;
    Button modificaOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_name);

        spinner = (Spinner) findViewById(R.id.spinnerNome);

        modificaOk = (Button) findViewById(R.id.modificaOk);

        spinner.add

    }

}
