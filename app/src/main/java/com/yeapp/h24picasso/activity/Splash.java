package com.yeapp.h24picasso.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yeapp.h24picasso.activity.ChangeH24;

/**
 * Created by Iacopo Peri on 03/02/17 02:56.
 */
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, ChangeH24.class);
        startActivity(intent);
        finish();
    }
}
