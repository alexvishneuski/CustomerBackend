package com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson;


import com.github.alexvishneuski.customerbackend.customerparsers.parser.CustomerGson;
import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerListParser;
import com.github.alexvishneuski.customerbackend.model.CustomerListGson;
import com.github.alexvishneuski.customerbackend.model.ICustomerList;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class GSONCustomerListParserImpl implements ICustomerListParser {
    private final InputStream mInputStream;

    public GSONCustomerListParserImpl(InputStream pInputStream) {
        this.mInputStream = pInputStream;
    }


    @Override
    public ICustomerList parce() throws Exception {
        Reader reader = new InputStreamReader(mInputStream);
        CustomerGson[] result = new Gson().fromJson(reader, CustomerGson[].class);
        return new CustomerListGson(Arrays.asList(result));

    }
}