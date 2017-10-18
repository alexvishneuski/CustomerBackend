package com.github.alexvishneuski.customerbackend.customerparsers.parser.overJson;



import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerListParser;

import java.io.InputStream;


public class JSONCustomerListParserImpl implements ICustomerListParser {

    private final InputStream mInputStream;

    public JSONCustomerListParserImpl(InputStream pInputStream) {
        this.mInputStream = pInputStream;
    }

}
