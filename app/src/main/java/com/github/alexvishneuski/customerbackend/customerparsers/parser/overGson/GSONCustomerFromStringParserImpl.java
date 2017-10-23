package com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson;


import com.github.alexvishneuski.customerbackend.model.CustomerGson;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerParser;
import com.github.alexvishneuski.customerbackend.model.ICustomer;
import com.google.gson.Gson;

public class GSONCustomerFromStringParserImpl implements ICustomerParser {
    private final String mSource;

    public GSONCustomerFromStringParserImpl(final String pSource) {
        mSource = pSource;
    }


    @Override
    public ICustomer parse() throws Exception {
        return new Gson().fromJson(mSource, CustomerGson.class);
    }
}

