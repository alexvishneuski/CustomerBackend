package com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson;


import com.github.alexvishneuski.customerbackend.model.CustomerGson;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerParser;
import com.github.alexvishneuski.customerbackend.model.ICustomer;
import com.github.alexvishneuski.customerbackend.utils.IOUtils;
import com.google.gson.Gson;

import java.io.InputStream;

public class GSONCustomerFromInputstreamParserImpl implements ICustomerParser {
    private final InputStream mIntputStream;


    public GSONCustomerFromInputstreamParserImpl(final InputStream pIntputStream) {
        mIntputStream = pIntputStream;

    }

    @Override
    public ICustomer parse() throws Exception {
        return new Gson().fromJson(IOUtils.toString(mIntputStream), CustomerGson.class);
    }


}

