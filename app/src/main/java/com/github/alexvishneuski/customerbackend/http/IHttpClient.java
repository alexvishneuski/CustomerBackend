package com.github.alexvishneuski.customerbackend.http;

public interface IHttpClient {

    void getAllCustomersRequest(String url, HttpClient.ResponseListener listener);
    void saveCustomerRequest(String url, String body, HttpClient.ResponseListener listener);
}
