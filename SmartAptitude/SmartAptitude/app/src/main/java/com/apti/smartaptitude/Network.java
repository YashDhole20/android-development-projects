 package com.apti.smartaptitude;

 import android.os.StrictMode;
 import android.util.Log;

 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Map;

 public class Network {
//     public static final String IP ="192.168.1.2/Color_Creation";
     public static final String IP ="192.168.1.4/SmartAptitude";
//     https://sunandainfotech.com/color_project
     public static String id = "0";

     public static String connect(String url, HashMap<String, String> param) {
         // TODO Auto-generated method stub
         HttpURLConnection urlConnection = null;
         try {
             Iterator it = param.entrySet().iterator();
             String paramStr="";
             String paramStr2="";
             while (it.hasNext()){
                 Map.Entry pairs = (Map.Entry)it.next();
                 paramStr+= "&"+pairs.getKey()+"="+ URLEncoder.encode(pairs.getValue()+"","UTF-8");
             }
             paramStr=paramStr.replaceFirst("&","");
             StrictMode.ThreadPolicy policy =
                     new StrictMode.ThreadPolicy.Builder().permitAll().build();
             StrictMode.setThreadPolicy(policy);
             URL urls = new URL(url+"?"+paramStr);
             System.out.println("Url     "+url+"?"+paramStr);
             urlConnection = (HttpURLConnection) urls.openConnection();

             int code = urlConnection.getResponseCode();
             if (code != 200) {
                 throw new IOException("Invalid response from server: " + code);
             }

             BufferedReader rd = new BufferedReader(new InputStreamReader(
                     urlConnection.getInputStream()));
             String line;
             StringBuffer sb = new StringBuffer("");
             String NL = System.getProperty("line.separator");
             while ((line = rd.readLine()) != null) {
                 Log.i("data", line);
                 sb.append(line + NL);
             }
             rd.close();
             System.out.println("response:"+sb.toString());
             id=sb.toString();
             return sb.toString();
         }
         catch (Exception e) {
             // TODO Auto-generated catch block
             System.out.println("err:"+e.getMessage());
             e.printStackTrace();
         }
         return null;
     }

 }
