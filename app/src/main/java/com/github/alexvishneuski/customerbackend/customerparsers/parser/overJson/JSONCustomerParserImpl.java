package com.github.alexvishneuski.customerbackend.customerparsers.parser.overJson;


import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerParser;
import com.github.alexvishneuski.customerbackend.model.Customer;

public class JSONCustomerParserImpl implements ICustomerParser {
    private final String mSource;

    public JSONCustomerParserImpl(final String pSource) {
        mSource = pSource;
    }

    @Override
    public Customer parse() throws Exception {
        return null;
    }
}
