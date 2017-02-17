package com.yeapp.h24picasso.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.yeapp.h24picasso.R;
import com.yeapp.h24picasso.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ModifyName extends AppCompatActivity {
    Spinner spinner;
    Button modificaOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_name);

        spinner = (Spinner) findViewById(R.id.spinnerNome);

        modificaOk = (Button) findViewById(R.id.modificaOk);

        List<String> spinnerArray = new ArrayList<String>();
        for( Constants.Numero r : Constants.Numero.values()){
            spinnerArray.add(r.toString());
            Log.d("MODIFICA", r.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

    }

}
