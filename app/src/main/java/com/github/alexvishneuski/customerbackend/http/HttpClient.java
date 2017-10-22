package com.github.alexvishneuski.customerbackend.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements IHttpClient {

    @Override
    public void getAllCustomersRequest(String url, ResponseListener listener) {
        try {
            HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
            InputStream is = con.getInputStream();
            listener.onResponse(is);
            con.disconnect();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void saveCustomerRequest(String url, String body, ResponseListener listener) {



    }


    public interface ResponseListener {
        void onResponse(InputStream inputStream);
    }

}
