package com.github.alexvishneuski.customerbackend.customerparsers.parser;

import com.github.alexvishneuski.customerbackend.model.Customer;
import com.github.alexvishneuski.customerbackend.model.ICustomer;

public interface ICustomerParser {
    ICustomer parse() throws Exception;
}
