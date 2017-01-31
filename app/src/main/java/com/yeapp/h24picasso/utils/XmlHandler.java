package com.yeapp.h24picasso.utils;

import android.text.Html;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import static java.lang.System.in;

/**
 * Created by Iacopo Peri on 30/01/17 21:42.
 */

public class XmlHandler {

    XmlPullParser xmlPP = XmlPullParserFactory.newInstance().newPullParser();
    private static XmlHandler instance = null;

    public static XmlHandler getInstance() throws XmlPullParserException {
        if(instance==null){
            return new XmlHandler();
        }
        return instance;
    }

    private XmlHandler() throws XmlPullParserException {
    }

    public void pp(String response){
        Log.d("RES",Html.fromHtml(response).toString());
    }

    public HashMap<String, String> parsePanelResponse(String response) throws XmlPullParserException, IOException {
        HashMap<String, String> val = new HashMap<String, String>();
        List entries = new ArrayList();
        xmlPP.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        xmlPP.setInput(new ByteArrayInputStream(response.getBytes()), null);
        xmlPP.nextTag();
//        xmlPP.require(XmlPullParser.START_TAG, null, "table");
        while (xmlPP.next() != XmlPullParser.END_TAG) {
            if (xmlPP.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = xmlPP.getName();
            // Starts by looking for the entry tag
            if (name.equals("table")) {
                entries.add(readTR(xmlPP));
            } else {
                skip(xmlPP);
            }
        }
        return val;
    }

    private String readTR(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "entry");
        String title = null;
        String summary = null;
        String link = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
//                title = readTitle(parser);
            } else if (name.equals("summary")) {
//                summary = readSummary(parser);
            } else if (name.equals("link")) {
//                link = readLink(parser);
            } else {
                skip(parser);
            }
        }
        return null;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
