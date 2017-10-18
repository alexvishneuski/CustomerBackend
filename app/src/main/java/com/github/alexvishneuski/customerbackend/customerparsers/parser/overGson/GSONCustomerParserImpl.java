package com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson;


import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerParser;

public class GSONCustomerParserImpl implements ICustomerParser {
    private final String mSource;

    public GSONCustomerParserImpl(final String pSource) {
        mSource = pSource;
    }

}
