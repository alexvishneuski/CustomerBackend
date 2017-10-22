package com.github.alexvishneuski.customerbackend.model;

import com.github.alexvishneuski.customerbackend.customerparsers.parser.CustomerGson;

import java.util.List;

public interface ICustomerList {
    List<CustomerGson> getCustomerList();
}