package com.github.alexvishneuski.customerbackend.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.alex.myapplication.backend.customerApi.CustomerApi;
import com.example.alex.myapplication.backend.customerApi.model.Customer;
import com.github.alexvishneuski.customerbackend.Api;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by evgen on 12.10.2017.
 */

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String[]>, Void, String> {
    public static final String NO_DATA = "No data";


    private static CustomerApi myApiService = null;
    private Context context;

    @Override
    public String doInBackground(Pair<Context, String[]>... params) {
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

        context = params[0].first;

        String id = params[0].second[0];
        String name = params[0].second[1];
        String phone = params[0].second[2];

        Customer user = new Customer();
        user.setId(Long.valueOf(id));
        user.setName(name);
        user.setPhone(phone);

        Customer resUser = new Customer();


        try {
            myApiService.insert(user).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resUser != null) {
            System.out.println(resUser.getId() + resUser.getName() + resUser.getPhone());
        }

        /*try {
            List<User> users = myApiService.list().execute().getItems();
            if (users == null || users.isEmpty()){
                return NO_DATA;
            }

            return users.get(users.size()).getName();
        } catch (IOException e) {
            return e.getMessage();
        }*/
        return "Inserted";
    }

    @Override
    public void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

}
