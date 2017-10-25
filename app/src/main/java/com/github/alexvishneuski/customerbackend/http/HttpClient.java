package com.github.alexvishneuski.customerbackend.http;

import android.support.annotation.VisibleForTesting;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements IHttpClient {


    private HttpURLConnection con;

    @Override
    public void getCurrentAppVersion(String pUrl, ResponseListener pListener) {
        try {
            final InputStream is = openStream(pUrl);
            pListener.onResponse(is);
            con.disconnect();
        } catch (final Throwable t) {
     //       pListener.onError(t);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

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


    @VisibleForTesting
    InputStream openStream(final String url) throws IOException {
        con = (HttpURLConnection) (new URL(url)).openConnection();
        return con.getInputStream();
    }

    public interface ResponseListener {
        void onResponse(InputStream pInputStream) throws Exception;

        void onError(Throwable pThrowable);
    }

    public interface RequestListener {
        void onRequest(OutputStream pOutputStream) throws IOException;

    }


}
