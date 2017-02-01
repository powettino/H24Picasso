package com.yeapp.h24picasso.utils;

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

    public enum Numeri{
        IACOPO ("3475006206"),
        MASSIMO("3386960076"),
        NICOLETTA("3466643068"),
        DAVIDE("3402787706"),
        CARMELO("3386548676"),
        UMBERTO("3442383570"),
        ANDREA ("3387085389");


        Numeri(String num) {
        }
    }
}
