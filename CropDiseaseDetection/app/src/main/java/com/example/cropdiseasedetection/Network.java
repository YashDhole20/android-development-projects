package com.example.cropdiseasedetection;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Network {
	public static final String IP = "192.168.126.160/CropDisease";
	public static String id = "0";

	public static String connect(String url, HashMap<String, String> param) {
		// TODO Auto-generated method stub
		HttpURLConnection urlConnection = null;
		try {
			Iterator it = param.entrySet().iterator();
			String paramStr = "";
			String paramStr2 = "";
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				paramStr += "&" + pairs.getKey() + "=" + URLEncoder.encode(pairs.getValue() + "", "UTF-8");
			}
			paramStr = paramStr.replaceFirst("&", "");

			URL urls = new URL(url + "?" + paramStr);
			System.out.println("Url     " + url + "?" + paramStr);
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
//			HttpClient httpclient = new DefaultHttpClient();
//			HttpGet httpGet = new HttpGet(url+"?"+paramStr);
//			System.out.println("hi     "+url+"?"+paramStr);
//			HttpResponse response;
//			response = httpclient.execute(httpGet);
//			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//			StringBuffer sb = new StringBuffer("");
//			String line = "";
//			String NL = System.getProperty("line.separator");
//			while ((line = in.readLine()) != null) {
//				sb.append(line + NL);
//
//			}

			rd.close();
			System.out.println("response:" + sb.toString());
			id = sb.toString();
			return sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("err:" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}


	public static Bitmap connectForImage(String url, HashMap<String, String> param) {
		// TODO Auto-generated method stub
		HttpURLConnection urlConnection = null;
		try {
			Iterator it = param.entrySet().iterator();
			String paramStr = "";
			String paramStr2 = "";
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				paramStr += "&" + pairs.getKey() + "=" + URLEncoder.encode(pairs.getValue() + "", "UTF-8");
			}
			paramStr = paramStr.replaceFirst("&", "");
			StrictMode.ThreadPolicy policy =
					new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL urls = new URL(url + "?" + paramStr);
			System.out.println("Url     " + url + "?" + paramStr);
			urlConnection = (HttpURLConnection) urls.openConnection();

			int code = urlConnection.getResponseCode();
			if (code != 200) {
				throw new IOException("Invalid response from server: " + code);
			}
			InputStream inputStream = urlConnection.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
			return bitmap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("err:" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}