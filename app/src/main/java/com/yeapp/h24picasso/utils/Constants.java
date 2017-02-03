package com.yeapp.h24picasso.utils;

import android.util.Log;

import java.util.zip.CheckedOutputStream;

/**
 * Created by Iacopo Peri on 25/01/17 21:28.
 */

public class Constants {

    public static final String loginRespMessage = "login_response_message";
    public static final String loginResult = "login_result";
    public static final String loginRespCode = "login_response_code";

    public static class Connection{
        public static final String USER ="800858446";
        public static final String PWD ="m4j3i9r9";
        public static final String CONNECTION_URL ="http://www.gestionenumeroverde.it/np_pub/login.php";
        public static final String PANEL_URL="http://www.gestionenumeroverde.it/np_pub/home.php";
        public static String POSTDATA = "login=@USER@&pass=@PASS@&tipo=0&button=Entra";
    }

    public enum Numero{
        IACOPO ("3475006206"),
        MASSIMO("3386960076"),
        NICOLETTA("3466643068"),
        DAVIDE("3402787706"),
        CARMELO("3386548676"),
        UMBERTO("3442383570"),
        ANDREA ("3387085389"),
        CODICES ("0503160136"),
        SUPPORTO ("supporto.picasso@codices.com");

        private String numero = "";

        Numero(String num) {
            this.numero = num;
        }

        public static String getName(String numero){
            if(numero.equalsIgnoreCase(IACOPO.numero)){
                return IACOPO.toString();
            }
            if(numero.equalsIgnoreCase(MASSIMO.numero)){
                return MASSIMO.toString();
            }
            if(numero.equalsIgnoreCase(NICOLETTA.numero)){
                return NICOLETTA.toString();
            }
            if(numero.equalsIgnoreCase(ANDREA.numero)){
                return ANDREA.toString();
            }
            if(numero.equalsIgnoreCase(CARMELO.numero)){
                return CARMELO.toString();
            }
            if(numero.equalsIgnoreCase(DAVIDE.numero)){
                return DAVIDE.toString();
            }
            if(numero.equalsIgnoreCase(UMBERTO.numero)){
                return UMBERTO.toString();
            }
            if(numero.equalsIgnoreCase(CODICES.numero)) {
                return CODICES.toString();
            }
            if(numero.equalsIgnoreCase(SUPPORTO.numero)) {
                return SUPPORTO.toString();
            }
            return "-";
        }
    }
}
