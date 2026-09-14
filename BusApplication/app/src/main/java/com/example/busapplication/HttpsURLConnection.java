package com.example.busapplication;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpsURLConnection{
public static void main(String[]arg){
    try{
        URL url = null;
        try {
            url = new URL("http://localhost/api.php");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // Set request method to POST or GET as needed
        try {
            urlConnection.setRequestMethod("POST");
        } catch (ProtocolException ex) {
            throw new RuntimeException(ex);
        }

        // Read the response
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        // Parse the input stream and handle the data

        // Disconnect
        urlConnection.disconnect();
    }
    catch(IOException e){

    }
}
}
