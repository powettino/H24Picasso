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
        public static final String PWD ="m4j3i9r";
        public static final String URL ="http://www.gestionenumeroverde.it/np_pub/login.php";
        public static String POSTDATA = "login=@USER@&pass=@PASS@&tipo=0&button=Entra";
    }
}
