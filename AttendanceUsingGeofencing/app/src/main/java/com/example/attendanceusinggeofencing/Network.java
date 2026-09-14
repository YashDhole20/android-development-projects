package com.example.attendanceusinggeofencing;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Network {
	public static final  String IP ="192.168.1.108:80/AttendanceSys";
	public static String id = "0";

	public static String connect(String url, HashMap<String, String> param) {
		// TODO Auto-generated method stub
		try {
			Iterator it = param.entrySet().iterator();
			String paramStr="";
			String paramStr2="";
			while (it.hasNext()){
				Map.Entry pairs = (Map.Entry)it.next();
				paramStr+= "&"+pairs.getKey()+"="+URLEncoder.encode(pairs.getValue()+"","UTF-8");
			}
			paramStr=paramStr.replaceFirst("&","");
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url+"?"+paramStr);
			System.out.println("hi     "+url+"?"+paramStr);
			HttpResponse response;
			response = httpclient.execute(httpGet);
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {                    
				sb.append(line + NL);

			}
			in.close();
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
