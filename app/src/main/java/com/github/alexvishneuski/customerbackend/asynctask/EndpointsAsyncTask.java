package com.github.alexvishneuski.customerbackend.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.example.alex.myapplication.backend.customerApi.CustomerApi;
import com.example.alex.myapplication.backend.customerApi.model.Customer;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    public static final String NO_DATA = "No data";
    private static CustomerApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
       // Log.i(TAG, "In Background");
        if (myApiService == null) {  // Only do this once
            CustomerApi.Builder builder = new CustomerApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                   // .setRootUrl("https://infra-vertex-182716.appspot.com/_ah/api/")
                    .setRootUrl( "http://localhost:8080/_ah/api/")
                    /*.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    })*/

                    ;
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;

        try {
            List<Customer> customers = myApiService.list().execute().getItems();
            if (customers == null || customers.isEmpty()) {
                return NO_DATA;
            }

            return customers.get(customers.size() - 1).getName();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

}