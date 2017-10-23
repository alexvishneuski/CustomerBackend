package com.github.alexvishneuski.customerbackend.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.github.alexvishneuski.customerbackend.Api;
import com.github.alexvishneuski.customerbackend.customerparsers.CustomerParserFactory;
import com.github.alexvishneuski.customerbackend.model.CustomerGson;
import com.github.alexvishneuski.customerbackend.http.HttpClient;
import com.github.alexvishneuski.customerbackend.http.IHttpClient;
import com.github.alexvishneuski.customerbackend.model.ICustomerList;

import java.io.InputStream;
import java.util.List;

public class CustomerListLoaderAT extends AsyncTask<Context, Void, String> {

    public static final String NO_DATA = "No data";
    public static final String GET_ALL_CUSTOMERS_URN = "customerApi/v1/customer";
    public static final String GET_ALL_CUSTOMERS_REMOTE_URI = Api.REMOTE_URL + GET_ALL_CUSTOMERS_URN;
    private ICustomerList mCustomerListWithObject;
    private Context mContext;

    @Override
    protected String doInBackground(Context... pParams) {

        final CustomerParserFactory customerListParserFactory = new CustomerParserFactory();
        IHttpClient httpClient = new HttpClient();

        mCustomerListWithObject = null;
        //https://customerbackend-183423.appspot.com/_ah/api/customerApi/v1/customer
        httpClient.getAllCustomersRequest(GET_ALL_CUSTOMERS_REMOTE_URI, new HttpClient.ResponseListener() {
            @Override
            public void onResponse(InputStream inputStream) {
                try {
                    mCustomerListWithObject = customerListParserFactory.createParserForResponceCustomerListWithObject(inputStream).parce();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mContext = pParams[0];

        if (mCustomerListWithObject == null) {
            return NO_DATA;
        }
        List<CustomerGson> usersList = mCustomerListWithObject.getCustomerList();

        if (usersList == null || usersList.isEmpty()) {
            return NO_DATA;
        }
        CustomerGson customerGson = usersList.get(usersList.size() - 2);
        String id = Long.toString(customerGson.getId());
        String name = customerGson.getName();
        String phone = customerGson.getPhone();

        return String.format("Last saved customer: id = %s, name = %s, phone = %s", id, name, phone);
    }

    @Override
    protected void onPostExecute(String pCustomer) {
        Toast.makeText(mContext, pCustomer, Toast.LENGTH_LONG).show();
    }
}
