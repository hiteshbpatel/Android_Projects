package com.dealkrack;
import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Talash Hub on 13-06-2016.
 */
public class Parser_SearchLoadList {

    Activity mActivity;
    String mSourceState;
    String mDestinationState;
    String mDate;
    public static String response;
    public Parser_SearchLoadList(Activity activity) {
        super();
        this.mActivity = activity;


        POSTForFetchingDATA("http://tools.vcommission.com/api/coupons.php?apikey=23b96d55da4372eabd8f2f1831d37f29f0434e4397ae0658f26fa813e8b9e381");

        // TODO Auto-generated constructor stub
    }


    private String POSTForFetchingDATA(String urlValue) {
        try {

            URL url = new URL(urlValue);
            Map<String, Object> params = new LinkedHashMap<>();


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // conn.setRequestProperty("Content-Type", "application/multipart/form-data");

            //conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.d("ERROR::::CODE", "http response code is " + conn.getResponseCode());
                return null;
            }
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0; )
                sb.append((char) c);
            response = sb.toString();
            System.out.println("Response SEARCH Load LIST DETAILS::::" + response);

        } catch (UnknownHostException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
        // 11. return result
        return "";
    }

}

