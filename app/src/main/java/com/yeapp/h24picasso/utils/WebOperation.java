package com.yeapp.h24picasso.utils;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by Iacopo Peri on 26/01/17 01:56.
 */

public class WebOperation {

    static CookieManager msCookieManager = new CookieManager();
    static final String COOKIES_HEADER = "Set-Cookie";
    static final String COOKIE = "Cookie";

    public static String getPanel() {
        HttpURLConnection http;
        try {
//            http = (HttpURLConnection) new URL(Constants.Connection.CONNECTION_URL).openConnection();
//            http.setDoOutput(true);
//            http.setFixedLengthStreamingMode(0);
//            http.setRequestMethod("GET");
//            addCookies(http);

            http = connect(null, Constants.Connection.CONNECTION_URL, Constants.Connection.GET_METHOD);
            InputStream is = null;
            if (http.getResponseCode() != HttpURLConnection.HTTP_OK) {
                is = http.getErrorStream();
            } else {
                is = http.getInputStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
            Log.d("WEB", "Completo " + sb.toString());
            String res = sb.toString();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bundle tryLogin(String user, String pass) throws Exception {
        HttpURLConnection http;
        Bundle bundle = new Bundle();
        try {
            String postData = Constants.Connection.POST_LOGIN.replace("@USER@", user).replace("@PASS@", pass);
            http = connect(postData, Constants.Connection.CONNECTION_URL, Constants.Connection.GET_METHOD);
//            http = (HttpURLConnection) new URL(Constants.Connection.CONNECTION_URL).openConnection();
//            http.setDoOutput(true);
//            http.setFixedLengthStreamingMode(postData.length());
////                    http.setChunkedStreamingMode(0);
//            http.setRequestMethod("GET");
//
//            addCookies(http);
//
//            OutputStream os = http.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//            writer.write(postData);
//            writer.flush();
//            writer.close();
//            os.close();

            InputStream is = null;
            String startSub="";
            String endSub="";
            if (http.getResponseCode() != HttpURLConnection.HTTP_OK) {
                is = http.getErrorStream();
                startSub="<p>";
                endSub="<br /></p>";

            } else {
                is = http.getInputStream();
                startSub="<span class=\"nero15b\">";
                endSub="</span>";
                extractCookies(http);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
            Log.d("WEB", "Completo " + sb.toString());
            String res = sb.toString();
            res = res.substring(res.indexOf(startSub)+startSub.length(),res.indexOf(endSub));
            Log.d("WEB", "tringa " + res);
            bundle.putBoolean(Constants.loginResult, res.contains("correttamente") ? true : false);
            bundle.putInt(Constants.loginRespCode, http.getResponseCode());
            bundle.putString(Constants.loginRespMessage, res);
        } catch (Exception e){
            Log.d("WEB", "Errore quando provo ad aprire la connessione");
            e.printStackTrace();
            throw e;
        }
        return bundle;
    }

    public static void SaveDaysNumbers(String mainNumber, String email, String firstNumber, String secondNumber, String id_day, String prog_day) throws Exception{
        String postData = Constants.Connection.POST_SAVE_DAYS.replaceAll("$numero1", mainNumber)
                .replaceAll("$email", email)
                .replaceAll("$numero2", firstNumber)
                .replaceAll("$numero3", secondNumber)
                .replaceAll("$id_fascia", id_day)
                .replaceAll("$num_giorno", prog_day);
        HttpURLConnection http = connect(postData, Constants.Connection.SAVE_URL, Constants.Connection.GET_METHOD);

    }

    private static HttpURLConnection connect(String postData, String url, String requestMethod) throws IOException {
        HttpURLConnection http = (HttpURLConnection) new URL(Constants.Connection.SAVE_URL).openConnection();
        http.setDoOutput(true);
        http.setFixedLengthStreamingMode(postData==null ? 0 : postData.length());
//                    http.setChunkedStreamingMode(0);
        http.setRequestMethod(requestMethod);

        addCookies(http);
        if(postData!=null) {
            OutputStream os = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(postData);
            writer.flush();
            writer.close();
            os.close();
        }
        return http;
    }

    private static void extractCookies(HttpURLConnection http) {
        Map<String, List<String>> headerFields = http.getHeaderFields();
        List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);
        if (cookiesHeader != null) {
            for (String cookie : cookiesHeader) {
                msCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
                Log.d("WEB","Saving cookie: "+HttpCookie.parse(cookie).get(0).toString());
            }
        }
    }

    private static void addCookies(HttpURLConnection http){
        if (msCookieManager.getCookieStore().getCookies().size() > 0) {
            http.setRequestProperty(COOKIE ,TextUtils.join(";", msCookieManager.getCookieStore().getCookies()));
            Log.d("WEB", "Sending cookie: "+msCookieManager.getCookieStore().getCookies().toString());
        }
    }

    private static CookieManager getMsCookieManager() {
        return msCookieManager;
    }
}
