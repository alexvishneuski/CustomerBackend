package com.github.alexvishneuski.customerbackend.customerparsers.parser.overJson;


import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerParser;

public class JSONCustomerParserImpl implements ICustomerParser {
    private final String mSource;

    public JSONCustomerParserImpl(final String pSource) {
        mSource = pSource;
    }
}
