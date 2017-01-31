package com.yeapp.h24picasso.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static HashMap<String, String> getDaysNames(Elements list, String subElement){
        HashMap<String, String> hash = new HashMap<String, String>();
        for(Element el : list){
            Elements listTd = el.select(subElement);
            hash.put(listTd.get(1).text(), listTd.get(0).text());
        }
        return hash;
    }
}
