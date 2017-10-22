package com.github.alexvishneuski.customerbackend.customerparsers.parser;

import com.github.alexvishneuski.customerbackend.model.ICustomerList;

public interface ICustomerListParser {

    ICustomerList parce() throws Exception;
}
