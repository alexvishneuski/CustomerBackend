package com.github.alexvishneuski.customerbackend.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GSONCustomerListWithObject implements ICustomerList {
    @SerializedName("items")
    private List<CustomerGson> mCustomerList;

    @SerializedName("backendVersion")
    private Integer backendVersion;


    @Override
    public List<CustomerGson> getCustomerList() {
        return mCustomerList;
    }

    public Integer getBackendVersion() {
        return backendVersion;
    }
}
