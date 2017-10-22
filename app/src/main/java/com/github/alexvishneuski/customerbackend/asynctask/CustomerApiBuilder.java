package com.github.alexvishneuski.customerbackend.asynctask;

import com.example.alex.myapplication.backend.customerApi.CustomerApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Asus on 19.10.2017.
 */

public class CustomerApiBuilder {
    public static final String LOCALHOST_PATH = "http://127.0.0.1:8080/_ah/api/";

    public static CustomerApi buildApi() {
        CustomerApi.Builder builder = new CustomerApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                .setRootUrl(LOCALHOST_PATH)
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {

                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
        // end options for devappserver

        return builder.build();
    }
}
