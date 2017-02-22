package com.yeapp.h24picasso.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yeapp.h24picasso.R;
import com.yeapp.h24picasso.adapter.DayAdapter;
import com.yeapp.h24picasso.utils.WebOperation;
import com.yeapp.h24picasso.utils.Constants;
import com.yeapp.h24picasso.utils.ProgressDialogWithTimeout;
import com.yeapp.h24picasso.utils.GeneralUtils;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ChangeH24 extends AppCompatActivity implements View.OnClickListener{

    private static final int CODE_FOR_LOGIN = 0;
    private static final int CODE_FOR_SAVE = 1;

    private ProgressDialogWithTimeout pDiag;
    GetPanelTask gpt;
    SaveNumberTask snt;
    DayAdapter da;

    TextView baseNum1;
    TextView baseNum2;
    TextView baseNum3;
    LinearLayout infoBase;

    FloatingActionButton fab;

    SwipeRefreshLayout srl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_h24);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        pDiag = new ProgressDialogWithTimeout(ChangeH24.this);
        pDiag.show("Connecting...", "Connecting to \"Gestionenumeroverde\"", 40000);

        baseNum1 = (TextView) findViewById(R.id.baseNum1);
        baseNum2 = (TextView) findViewById(R.id.baseNum2);
        baseNum3 = (TextView) findViewById(R.id.baseNum3);

        infoBase = (LinearLayout) findViewById(R.id.infoBase);
        infoBase.setVisibility(View.INVISIBLE);

        fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        srl = (SwipeRefreshLayout) findViewById(R.id.swipe);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                gpt= new GetPanelTask();
                gpt.execute();
            }
        });

        srl.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        new AsyncTask<Void, Void, Bundle>(){

            @Override
            protected Bundle doInBackground(Void... strings) {
                Bundle b = new Bundle();
                try {
                    b = WebOperation.tryLogin(Constants.Connection.USER, Constants.Connection.PWD);
                } catch (Exception e) {
                    pDiag.dismiss();
                }
                return b;
            }

            @Override
            protected void onPostExecute(final Bundle s) {
                if (s.getBoolean(Constants.loginResult)) {
                    Log.d("Background", "Loggato");
                    gpt= new GetPanelTask();
                    gpt.execute();
                } else {
                    Log.d("Background", "Stringa " + s + ", errore");
                    new AlertDialog.Builder(ChangeH24.this)
                            .setTitle("Cannot auto-login")
                            .setMessage(s.getString(Constants.loginRespMessage))
                            .setCancelable(true)
                            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    if(s.isEmpty() || s.getInt(Constants.loginRespCode)==200) {
                                        Intent login = new Intent(getBaseContext(), Login.class);
                                        startActivityForResult(login, CODE_FOR_LOGIN);
                                    }
                                }
                            })
                            .create()
                            .show();
                    pDiag.dismiss();
                }
            }
        }.execute();

        ListView listA = (ListView) findViewById(R.id.listDays);
        da = new DayAdapter(this);
        listA.setAdapter(da);
//        listA.setEmptyView(view.findViewById(R.id.empty));
//        listA.setOnItemClickListener(this);

//        addButton = (FloatingActionButton) view.findViewById(R.id.addHand);
//        addButton.setOnClickListener(this);



//        if(restoring) {

//            addButton.setEnabled(currentGame.getWinner()==0);
//            setUIStatus();
//            if (currentGame.getNumeroMani() != 0) {
//                resultB.setBackgroundResource(R.color.SfondoMedio);
//                resultA.setBackgroundResource(R.color.SfondoMedio);
//            }
//            restoring = false;
//        }
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fab: {
                Log.d("PANEL", "Modifico qualcosa");
                Intent intent = new Intent(getBaseContext(), ModifyName.class);
                startActivityForResult(intent, CODE_FOR_SAVE);
//                Log.d("Log prova", "sto cliccano il bottone di stop");
//                pDiag.dismiss();
            }
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CODE_FOR_LOGIN:{
                gpt= new GetPanelTask();
                gpt.execute();
            }
            case CODE_FOR_SAVE:{
                pDiag = new ProgressDialogWithTimeout(ChangeH24.this);
                pDiag.show("Saving data...", "Connecting to \"Gestionenumeroverde\"", 40000);
                String first = data.getStringExtra(Constants.firstNumber);
                String second = data.getStringExtra(Constants.secondNumber);
                for(int i =0; i<Constants.Connection.ID_DAYS.length;i++){
                    snt = new SaveNumberTask();
                    snt.execute(first, second, String.valueOf(i));
                }
                snt =new SaveNumberTask();
                snt.execute(first, second);
            }
            default:
                break;
        }
    }


    private class SaveNumberTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                if(params.length==3) {
                    Log.d("PANEL", "Lancio il salvataggio dei campi per giorno");
                    WebOperation.SaveDaysNumbers(Constants.Numero.CODICES.getValue(), Constants.Numero.SUPPORTO.getValue(), params[0], params[1], Constants.Connection.ID_DAYS[Integer.parseInt(params[2])], params[2] + 1);
                }else{
                    Log.d("PANEL", "Lancio il salvataggio dei campi per base");
                    WebOperation.SaveBaseNumbers(Constants.Numero.SUPPORTO.getValue(), params[0], params[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("PANEL", "salvtaggio terminato");
            gpt = new GetPanelTask();
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
            Elements listCoppie = GeneralUtils.getElementsList(s, "tr[class='nero12']");
            ArrayList<Pair<String, ArrayList<String>>> listGiorni = GeneralUtils.getDaysNames(listCoppie, "td", "<br>");
            baseNum1.setText(Constants.Numero.getName(listGiorni.get(listGiorni.size()-1).second.get(0)));
            Log.d("BOH:", "RISULTATO> "+listGiorni.get(listGiorni.size()-1).second.get(1)+"--");
            baseNum2.setText(Constants.Numero.getName(listGiorni.get(listGiorni.size()-1).second.get(1)));
            baseNum3.setText(Constants.Numero.getName(listGiorni.get(listGiorni.size()-1).first));
            da.clear();
            da.addDays(listGiorni, false);
            da.notifyDataSetChanged();
            infoBase.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
            Log.d("PANEL", "Risultato ottenuto");
            srl.setRefreshing(false);
        }
    }
}
