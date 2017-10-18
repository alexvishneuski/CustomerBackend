package com.example.alex.myapplication.backend;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Customer {

    @Id
    private Integer mId;

    private String mName;

    private String mPhone;

    public Customer() {
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer pId) {
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