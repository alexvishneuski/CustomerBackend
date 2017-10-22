package com.github.alexvishneuski.customerbackend.customerparsers.parser;

import com.github.alexvishneuski.customerbackend.model.Customer;

public interface ICustomerParser {
    Customer parse() throws Exception;
}
