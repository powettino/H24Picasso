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

    public static final String firstNumber = "firstResult";
    public static final String secondNumber = "secondResult";
    public static final String mainNumber = "mainNumber";

    public static class Connection{
        public static final String GET_METHOD="GET";
        public static final String POST_METHOD="POST";
        public static final String USER ="800858446";
        public static final String PWD ="m4j3i9r9";
        public static final String CONNECTION_URL ="http://www.gestionenumeroverde.it/np_pub/login.php";
        public static final String SAVE_DAYS_URL="http://www.gestionenumeroverde.it/np_pub/mod_fasce2.php";
        public static final String SAVE_BASE_URL="http://www.gestionenumeroverde.it/np_pub/mod_base2.php";
        public static final String PANEL_URL="http://www.gestionenumeroverde.it/np_pub/home.php";
        public static String POST_LOGIN = "login=@USER@&pass=@PASS@&tipo=0&button=Entra";
        public static String POST_SAVE_DAYS="pref_internaz=0039&terminazione1=$numero1&pref_internaz=0039&terminazione2=$numero2" +
                "&pref_internaz=0039&terminazione3=$numero3&pref_internaz=0039&terminazione4=&pref_internaz=0039" +
                "&terminazione5=&pref_internaz=0039&terminazione6=&pref_internaz=0039&terminazione7=&pref_internaz=0039" +
                "&terminazione8=&pref_internaz=0039&terminazione9=&pref_internaz=0039&terminazione10=&pref_internaz=0039" +
                "&terminazione11=&pref_internaz=0039&terminazione12=&pref_internaz=0039&terminazione13=&pref_internaz=0039" +
                "&terminazione14=&pref_internaz=0039&terminazione15=&pref_internaz=0039&terminazione16=&pref_internaz=0039" +
                "&terminazione17=&pref_internaz=0039&terminazione18=&pref_internaz=0039&terminazione19=&pref_internaz=0039" +
                "&terminazione20=&ore_inizio_fascia=09&minuti_inizio_fascia=30&ore_fine_fascia=18&minuti_fine_fascia=30" +
                "&giorno=$num_giorno&squilli=0&segreteria=$email&attesa=0&id_f=$id_fascia&button=Memorizza";
        public static String POST_SAVE_BASE="pref_internaz=0039&terminazione1=$numero&pref_internaz=0039&terminazione2=$num_aggiuntivo" +
                "&pref_internaz=0039&terminazione3=&pref_internaz=0039&terminazione4=&pref_internaz=0039" +
                "&terminazione5=&pref_internaz=0039&terminazione6=&pref_internaz=0039&terminazione7=&pref_internaz=0039" +
                "&terminazione8=&pref_internaz=0039&terminazione9=&pref_internaz=0039&terminazione10=&pref_internaz=0039" +
                "&terminazione11=&pref_internaz=0039&terminazione12=&pref_internaz=0039&terminazione13=&pref_internaz=0039" +
                "&terminazione14=&pref_internaz=0039&terminazione15=&pref_internaz=0039&terminazione16=&pref_internaz=0039" +
                "&terminazione17=&pref_internaz=0039&terminazione18=&pref_internaz=0039&terminazione19=&pref_internaz=0039" +
                "&terminazione20=&squilli=0&segreteria=$email&attesa=0&button=Memorizza";
        public static final String[] ID_DAYS = new String[]{"2409", "2410", "2411", "2412", "2413"};
    }

    public enum Numero{
        CODICES ("0503160136"),
        ANDREA ("3387085389"),
        CARMELO("3386548676"),
        DAVIDE("3402787706"),
        IACOPO ("3475006206"),
        MASSIMO("3386960076"),
        NICOLETTA("3466643068"),
        UMBERTO("3442383570"),
        SUPPORTO ("supporto.picasso@codices.com");

        private String numero = "";

        Numero(String num) {
            this.numero = num;
        }

        public String getValue(){
            return this.numero;
        }

        public static String getNumero(String nome){
            if(nome.equalsIgnoreCase(IACOPO.toString())){
                return IACOPO.numero;
            }
            if(nome.equalsIgnoreCase(MASSIMO.toString())){
                return MASSIMO.numero;
            }
            if(nome.equalsIgnoreCase(NICOLETTA.toString())){
                return NICOLETTA.numero;
            }
            if(nome.equalsIgnoreCase(ANDREA.toString())){
                return ANDREA.numero;
            }
            if(nome.equalsIgnoreCase(DAVIDE.toString())){
                return DAVIDE.numero;
            }
            if(nome.equalsIgnoreCase(CARMELO.toString())){
                return CARMELO.numero;
            }
            if(nome.equalsIgnoreCase(UMBERTO.toString())){
                return UMBERTO.numero;
            }
            if(nome.equalsIgnoreCase(CODICES.toString())){
                return CODICES.numero;
            }
            if(nome.equalsIgnoreCase(SUPPORTO.toString())){
                return SUPPORTO.numero;
            }

            return "-";
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
            if(numero!=null && numero.length()!=0){
                return numero;
            }
            return "-";
        }
    }
}
