package com.yeapp.h24picasso.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.yeapp.h24picasso.R;
import com.yeapp.h24picasso.utils.Constants;
import com.yeapp.h24picasso.utils.GeneralUtils;

import java.util.ArrayList;
import java.util.List;

public class ModifyName extends AppCompatActivity implements View.OnClickListener{
    Spinner spinner2;
    Spinner spinner1;
    FloatingActionButton modificaOk;
    EditText third;
    CheckBox mainFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_name);

        spinner2 = (Spinner) findViewById(R.id.spinnerNome2);
        spinner1 = (Spinner) findViewById(R.id.spinnerNome1);

        third = (EditText) findViewById(R.id.numeroTerzo);

        modificaOk = (FloatingActionButton) findViewById(R.id.modificaOk);
        modificaOk.setImageBitmap(GeneralUtils.textAsBitmap("OK", 40, Color.WHITE));

        List<String> spinnerArray = new ArrayList<String>();
        for( Constants.Numero r : Constants.Numero.values()){
            spinnerArray.add(r.toString());
            Log.d("MODIFICA", r.toString());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);
        spinner1.setAdapter(adapter1);

        spinner1.setSelection(0);
        spinner2.setSelection(1);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.modificaOk: {
                if (spinner1.getSelectedItemPosition() == spinner2.getSelectedItemPosition()) {
                    new android.app.AlertDialog.Builder(ModifyName.this)
                            .setTitle("Selection error")
                            .setMessage("It's not allowed to select same values")
                            .setCancelable(true)
                            .create()
                            .show();
                } else {
                    Log.d("MODIFY", "SALVO");
                    setResult(RESULT_OK, getIntent()
                            .putExtra(Constants.firstNumber, Constants.Numero.getNumero(spinner1.getItemAtPosition(spinner1.getSelectedItemPosition()).toString()))
                            .putExtra(Constants.secondNumber, Constants.Numero.getNumero(spinner2.getItemAtPosition(spinner2.getSelectedItemPosition()).toString()))
                            .putExtra(Constants.thirdNumber, third.getText().toString()));

                    finish();
                }
            }
            default:
                break;
        }
    }
}
