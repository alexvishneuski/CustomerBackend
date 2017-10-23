package com.github.alexvishneuski.customerbackend.http;

public interface IHttpClient {

    void getAllCustomersRequest(String pUrl, HttpClient.ResponseListener pListener);

    void saveCustomerRequest(String pUrl, String pBody, HttpClient.RequestListener pRequestListener, HttpClient.ResponseListener pResponseListener);
}
