package com.github.alexvishneuski.customerbackend.http;

public interface IHttpClient {

    void request(String url, HttpClient.ResponseListener listener);
}
