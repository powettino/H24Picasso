package com.yeapp.h24picasso.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
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
                for (String sep : separated) {
                    sep = sep.replace("+39","");
                    sep=sep.replaceAll(" ","");
                    numbers.add(sep);
                    Log.d("GENERAL", "aggiunto numero: "+sep);
                }
                for(int i=numbers.size()-1;i<2;i++){
                    numbers.add("");
                }
            }else{
                numbers.add(listTd.get(0).text());
            }
            coppie.add(new Pair<String, ArrayList<String>>(listTd.get(1).text(), numbers));
        }
        return coppie;
    }
    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }
}
