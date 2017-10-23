package com.github.alexvishneuski.customerbackend.customerparsers.parser.overJson;


import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerParser;
import com.github.alexvishneuski.customerbackend.model.Customer;
import com.github.alexvishneuski.customerbackend.model.ICustomer;

public class JSONCustomerParserImpl implements ICustomerParser {
    private final String mSource;

    public JSONCustomerParserImpl(final String pSource) {
        mSource = pSource;
    }

    @Override
    public ICustomer parse() throws Exception {
        return null;
    }
}
