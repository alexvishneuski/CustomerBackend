package com.github.alexvishneuski.customerbackend.model;

import java.util.List;

public class CustomerListGson implements ICustomerList {
    private List<CustomerGson> mCustomerList;

    public CustomerListGson(List<CustomerGson> pUsersLists) {
        this.mCustomerList = pUsersLists;
    }


    @Override
    public List<CustomerGson> getCustomerList() {
        return mCustomerList;
    }
}
