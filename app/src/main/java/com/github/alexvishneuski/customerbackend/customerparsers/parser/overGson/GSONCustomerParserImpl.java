package com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson;


import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerParser;
import com.github.alexvishneuski.customerbackend.model.Customer;

public class GSONCustomerParserImpl implements ICustomerParser {
    private final String mSource;

    public GSONCustomerParserImpl(final String pSource) {
        mSource = pSource;
    }

    @Override
    public Customer parse() throws Exception {
        return null;
    }
}
