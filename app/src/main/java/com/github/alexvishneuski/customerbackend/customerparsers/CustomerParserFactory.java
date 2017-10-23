package com.github.alexvishneuski.customerbackend.customerparsers;


import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerListParser;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerParser;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson.GSONCustomerFromStringParserImpl;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson.GSONCustomerListParserImpl;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson.GSONCustomerListWithObjectParcerImpl;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson.GSONCustomerFromInputstreamParserImpl;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.overJson.JSONCustomerListParserImpl;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.overJson.JSONCustomerParserImpl;

import java.io.InputStream;

public class CustomerParserFactory {


    public ICustomerParser createJsonParser(String pSource) {
        return new JSONCustomerParserImpl(pSource);
    }

    public ICustomerParser createGsonParserFromInputstream(InputStream pSource) {
        return new GSONCustomerFromInputstreamParserImpl(pSource);
    }

    public ICustomerParser createGsonParserFromString(String pSource) {
        return new GSONCustomerFromStringParserImpl(pSource);
    }

    public ICustomerListParser createJsonListParser(InputStream pSource) {
        return new JSONCustomerListParserImpl(pSource);
    }

    public ICustomerListParser createGsonListParser(InputStream pSource) {
        return new GSONCustomerListParserImpl(pSource);
    }

    public ICustomerListParser createParserForResponceCustomerListWithObject(InputStream pSource) {
        return new GSONCustomerListWithObjectParcerImpl(pSource);
    }

}
