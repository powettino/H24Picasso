package com.yeapp.h24picasso.utils;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Iacopo Peri on 26/01/17 01:56.
 */

public class WebOperation {

    public static Bundle TryLogin(String user, String pass){
        HttpURLConnection http;
        Bundle bundle = new Bundle();
        try {
            String postData = Constants.Connection.POSTDATA.replace("@USER@", user).replace("@PASS@", pass);
            http = (HttpURLConnection) new URL(Constants.Connection.URL).openConnection();
            http.setDoOutput(true);
            http.setFixedLengthStreamingMode(postData.length());
//                    http.setChunkedStreamingMode(0);
            http.setRequestMethod("GET");

            OutputStream os = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(postData);
            writer.flush();
            writer.close();
            os.close();

            InputStream is = null;
            String startSub="";
            String endSub="";
            if (http.getResponseCode() != 200) {
                is = http.getErrorStream();
                startSub="<p>";
                endSub="<br /></p>";

            } else {
                is = http.getInputStream();
                startSub="<span class=\"nero15b\">";
                endSub="</span>";
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
            Log.d("RES", "Completo " + sb.toString());
            String res = sb.toString();
            res = res.substring(res.indexOf(startSub)+startSub.length(),res.indexOf(endSub));
            Log.d("RES", "tringa " + res);
            bundle.putBoolean(Constants.loginResult, res.contains("correttamente") ? true : false);
            bundle.putString(Constants.loginRespMessage, res);
        } catch (IOException e) {
            Log.d("Log prova", "errore quando provo ad aprire la cnnession");
            e.printStackTrace();
        }
        return bundle;
    }
}
