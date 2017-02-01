package com.yeapp.h24picasso.utils;

import android.util.Pair;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParserException;

import java.util.ArrayList;

/**
 * Created by Iacopo Peri on 30/01/17 21:42.
 */

public class GeneralUtils {

//    private static GeneralUtils instance = null;
//
//    public static GeneralUtils getInstance() throws XmlPullParserException {
//        if(instance==null){
//            return new GeneralUtils();
//        }
//        return instance;
//    }

    private GeneralUtils() throws XmlPullParserException {
    }

    public static Elements getElementsList(String response, String condition){
        Document res = Jsoup.parse(response);
        Elements list = res.select(condition);
        return list;
    }

    public static ArrayList<Pair<String, ArrayList<String>>> getDaysNames(Elements list, String subElement, String separator){
        ArrayList<Pair<String, ArrayList<String>>> coppie = new ArrayList<>();
        for(Element el : list){
            Elements listTd = el.select(subElement);
            ArrayList<String> numbers = new ArrayList<String>();
            if(!separator.isEmpty()){
                String temp = listTd.get(0).html();
                String[] separated = temp.split(separator);
                for (String s : separated) {
                    s = s.replace("+39","");
                    s.trim();
                    numbers.add(s);
                }
                for(int i=numbers.size()-1;i<3;i++){
                    numbers.add("");
                }
            }else{
                numbers.add(listTd.get(0).text());
            }
            coppie.add(new Pair<String, ArrayList<String>>(listTd.get(1).text(), numbers));
        }
        return coppie;
    }
}
