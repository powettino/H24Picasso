package com.yeapp.h24picasso.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.yeapp.h24picasso.R;
import com.yeapp.h24picasso.utils.Constants;
import com.yeapp.h24picasso.utils.GeneralUtils;

import java.util.ArrayList;
import java.util.List;

public class ModifyName extends AppCompatActivity implements View.OnClickListener{
    Spinner spinner;
    FloatingActionButton modificaOk;
    EditText second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_name);

        spinner = (Spinner) findViewById(R.id.spinnerNome);

        second = (EditText) findViewById(R.id.numeroSecondario);

        modificaOk = (FloatingActionButton) findViewById(R.id.modificaOk);
        modificaOk.setImageBitmap(GeneralUtils.textAsBitmap("OK", 40, Color.WHITE));

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

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.modificaOk: {
                Log.d("MODIFY", "SALVO");
                setResult(RESULT_OK, getIntent()
                        .putExtra(Constants.firstNumber, spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString())
                        .putExtra(Constants.secondNumber, second.getText().toString()));

                finish();
            }
            default:
                break;
        }
    }
}
