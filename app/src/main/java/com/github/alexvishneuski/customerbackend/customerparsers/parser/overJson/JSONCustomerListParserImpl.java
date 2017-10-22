package com.github.alexvishneuski.customerbackend.customerparsers.parser.overJson;



import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerListParser;
import com.github.alexvishneuski.customerbackend.model.Customer;
import com.github.alexvishneuski.customerbackend.model.ICustomerList;

import java.io.InputStream;
import java.util.List;


public class JSONCustomerListParserImpl implements ICustomerListParser {

    private final InputStream mInputStream;

    public JSONCustomerListParserImpl(InputStream pInputStream) {
        this.mInputStream = pInputStream;
    }

    @Override
    public ICustomerList parce() throws Exception {
        return null;
    }
}
