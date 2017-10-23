package com.github.alexvishneuski.customerbackend.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements IHttpClient {

    @Override
    public void getAllCustomersRequest(String pUrl, ResponseListener pListener) {
        try {
            HttpURLConnection con = (HttpURLConnection) (new URL(pUrl)).openConnection();
            InputStream is = con.getInputStream();
            pListener.onResponse(is);
            con.disconnect();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void saveCustomerRequest(String pUrl, String pBody, RequestListener pRequestListeneristener, ResponseListener pResponseListenerlistener) {


        try {
            HttpURLConnection con = (HttpURLConnection) (new URL(pUrl)).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            // con.setRequestProperty("User-Agent", );
            //con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            pRequestListeneristener.onRequest(os);

            //con.setDoOutput(true);
            InputStream is = con.getInputStream();
            pResponseListenerlistener.onResponse(is);

            con.disconnect();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public interface ResponseListener {
        void onResponse(InputStream pInputStream);
    }

    public interface RequestListener {
        void onRequest(OutputStream pOutputStream) throws IOException;
    }


}
