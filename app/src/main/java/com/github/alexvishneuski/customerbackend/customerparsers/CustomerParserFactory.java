package com.github.alexvishneuski.customerbackend.customerparsers;


import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerListParser;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerParser;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson.GSONCustomerParserImpl;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.overJson.JSONCustomerListParserImpl;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.overJson.JSONCustomerParserImpl;

import java.io.InputStream;

public class CustomerParserFactory {
    public ICustomerParser createJsonParser(String pSource) {
        return new JSONCustomerParserImpl(pSource);
    }

    public ICustomerParser createGsonParser(String pSource) {
        return new GSONCustomerParserImpl(pSource);
    }

    public ICustomerListParser createJsonListParser(InputStream pSource) {
        return new JSONCustomerListParserImpl(pSource);
    }

    public ICustomerListParser createGsonListParser(InputStream pSource) {
        return new JSONCustomerListParserImpl(pSource);
    }
}
