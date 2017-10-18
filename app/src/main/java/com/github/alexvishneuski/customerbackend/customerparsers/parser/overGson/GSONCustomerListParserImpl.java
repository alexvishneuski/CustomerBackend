package com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson;

import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerListParser;

import java.io.InputStream;

public class GSONCustomerListParserImpl implements ICustomerListParser {
    private final InputStream mInputStream;

    public GSONCustomerListParserImpl(InputStream pInputStream) {
        this.mInputStream = pInputStream;
    }

}
