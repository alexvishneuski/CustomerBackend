package com.github.alexvishneuski.customerbackend.model;

import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("phone")
    private String mPhone;

    public Customer(Long pId, String pName, String pPhone) {
        mId = pId;
        mName = pName;
        mPhone = pPhone;
    }

    public Customer() {
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long pId) {
        mId = pId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String pPhone) {
        mPhone = pPhone;
    }
}
