package com.github.alexvishneuski.customerbackend.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.github.alexvishneuski.customerbackend.Api;
import com.github.alexvishneuski.customerbackend.customerparsers.CustomerParserFactory;
import com.github.alexvishneuski.customerbackend.http.HttpClient;
import com.github.alexvishneuski.customerbackend.http.IHttpClient;
import com.github.alexvishneuski.customerbackend.model.Customer;
import com.github.alexvishneuski.customerbackend.model.ICustomer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class CustomerSaverAT extends AsyncTask<Pair<Context, String>, Void, String> {

    public static final String NO_DATA = "No data";
    public static final String GET_ALL_CUSTOMERS_URN = "customerApi/v1/customer";
    public static final String GET_ALL_CUSTOMERS_REMOTE_URI = Api.REMOTE_URL + GET_ALL_CUSTOMERS_URN;
    private ICustomer mCustomer;
    private Context mContext;

    @Override
    protected String doInBackground(Pair<Context, String>... pParams) {


        final CustomerParserFactory customerListParserFactory = new CustomerParserFactory();
        IHttpClient httpClient = new HttpClient();
        mCustomer = null;

        mContext = pParams[0].first;
        final String body = pParams[0].second;

        //https://customerbackend-183423.appspot.com/_ah/api/customerApi/v1/customer
        httpClient.saveCustomerRequest(GET_ALL_CUSTOMERS_REMOTE_URI, body, new HttpClient.RequestListener() {
            @Override
            public void onRequest(OutputStream pOutputStream) throws IOException {

                OutputStreamWriter wr = new OutputStreamWriter(pOutputStream);
                wr.write(body);
                wr.flush();
                wr.close();
            }

        }, new HttpClient.ResponseListener() {
            @Override
            public void onResponse(InputStream pInputStream) {
                try {
                    mCustomer = customerListParserFactory.createGsonParserFromInputstream(pInputStream).parse();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable pThrowable) {
                //TODO implement error handling on UI
                throw new UnsupportedOperationException();
            }
        });

        if (mCustomer == null) {
            return NO_DATA;
        }

        Customer customer = new Customer(mCustomer.getId(), mCustomer.getName(), mCustomer.getPhone());

        String id = Long.toString(customer.getId());
        String name = customer.getName();
        String phone = customer.getPhone();

        return String.format("Saved customer: id = %s, name = %s, phone = %s", id, name, phone);
    }

    @Override
    protected void onPostExecute(String pCustomer) {
        Toast.makeText(mContext, pCustomer, Toast.LENGTH_LONG).show();
    }
}
