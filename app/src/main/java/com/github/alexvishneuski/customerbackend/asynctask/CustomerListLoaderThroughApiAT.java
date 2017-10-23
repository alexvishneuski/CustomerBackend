package com.github.alexvishneuski.customerbackend.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.alex.myapplication.backend.customerApi.CustomerApi;
import com.example.alex.myapplication.backend.customerApi.model.Customer;
import com.github.alexvishneuski.customerbackend.Api;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

public class CustomerListLoaderThroughApiAT extends AsyncTask<Context, Void, String> {
    public static final String NO_DATA = "No data";

    private static CustomerApi myApiService = null;
    private Context context;

    @Override
    public String doInBackground(Context... pParams) {
        if (myApiService == null) {  // Only do this once
            CustomerApi.Builder builder = new CustomerApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(Api.REMOTE_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = pParams[0];

        try {

            List<Customer> customers = myApiService.list().execute().getItems();
            if (customers == null || customers.isEmpty()) {
                return NO_DATA;
            }
            Customer customer = new Customer();
            return String.format("Last saved customer: id = %s, name = %s, phone = %s", customer.getId().toString(), customer.getName(), customer.getPhone());
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    public void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

}
