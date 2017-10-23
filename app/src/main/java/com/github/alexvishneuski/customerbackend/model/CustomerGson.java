package com.github.alexvishneuski.customerbackend.model;

import com.github.alexvishneuski.customerbackend.model.ICustomer;
import com.google.gson.annotations.SerializedName;

public class CustomerGson implements ICustomer {

    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("phone")
    private String mPhone;


    @Override
    public long getId() {
        return mId;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getPhone() {
        return mPhone;
    }


}
