package com.github.alexvishneuski.customerbackend.customerparsers.parser.overGson;

import com.github.alexvishneuski.customerbackend.customerparsers.parser.ICustomerListParser;
import com.github.alexvishneuski.customerbackend.model.GSONCustomerListWithObject;
import com.github.alexvishneuski.customerbackend.model.ICustomerList;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class GSONCustomerListWithObjectParcerImpl implements ICustomerListParser {

    @Override
    public ICustomerList parce() throws Exception {
        Reader reader = new InputStreamReader(mIntputStream);
        return new Gson().fromJson(reader, GSONCustomerListWithObject.class);
     }

    private final InputStream mIntputStream;

    public GSONCustomerListWithObjectParcerImpl(InputStream pIntputStream) {
        this.mIntputStream = pIntputStream;
    }

}
