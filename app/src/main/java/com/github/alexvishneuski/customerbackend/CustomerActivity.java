package com.github.alexvishneuski.customerbackend;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.alexvishneuski.customerbackend.asynctask.CustomerListLoaderAT;
import com.github.alexvishneuski.customerbackend.asynctask.CustomerListLoaderThroughApiAT;
import com.github.alexvishneuski.customerbackend.asynctask.CustomerSaveThroughApiAT;
import com.github.alexvishneuski.customerbackend.asynctask.CustomerSaverAT;
import com.github.alexvishneuski.customerbackend.model.Customer;
import com.google.gson.Gson;

public class CustomerActivity extends AppCompatActivity {

    private EditText mInputIdEditText;
    private EditText mInputNameEditText;
    private EditText mInputPhoneEditText;
    private View mSaveCustomerButton;
    private View mLoadLastCustomerButton;
    private View mSaveCustomerThroughApiButton;
    private View mLoadLastCustomerThroughApiButton;
    private TextView mShowSavedTextView;

    @Override
    protected void onCreate(@Nullable Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_customer);
        initView();
    }

    private void initView() {

        mInputIdEditText = (EditText) findViewById(R.id.input_id_field_edit_text);
        mInputNameEditText = (EditText) findViewById(R.id.input_name_field_edit_text);
        mInputPhoneEditText = (EditText) findViewById(R.id.input_phone_field_edit_text);

        mSaveCustomerButton = findViewById(R.id.save_customer_button);
        mLoadLastCustomerButton = findViewById(R.id.load_last_customer_button);
        mSaveCustomerThroughApiButton = findViewById(R.id.save_customer_through_api_button);
        mLoadLastCustomerThroughApiButton = findViewById(R.id.load_last_customer_through_api_button);

        mShowSavedTextView = (TextView) findViewById(R.id.show_saved_text_view);

        /*
        * save item direct through http
        */
        mSaveCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Customer customer = new Customer();
                String id = mInputIdEditText.getText().toString();
                customer.setId(Long.valueOf(id));
                String name = mInputNameEditText.getText().toString();
                customer.setName(name);
                String phone = mInputPhoneEditText.getText().toString();
                customer.setPhone(phone);

                Gson gson = new Gson();
                String customerJson = gson.toJson(customer, Customer.class);

                //Performing AsyncTasc for one customer save
                new CustomerSaverAT().execute(new Pair<Context, String>(CustomerActivity.this, customerJson));

                showResult(String.format("Saved customer (http): id = %s, name = %s, phone = %s", id, name, phone));
            }
        });

        /*
        *  load item list and show last item direct through http
        */
        mLoadLastCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Performing AsyncTasc for customers list downloading
                new CustomerListLoaderAT().execute(CustomerActivity.this);

                showResult("(http)");
            }
        });

        /*
        * save item through customerApi
        */
        mSaveCustomerThroughApiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = mInputIdEditText.getText().toString();
                String name = mInputNameEditText.getText().toString();
                String phone = mInputPhoneEditText.getText().toString();
                String[] customer = {id, name, phone};

                new CustomerSaveThroughApiAT().execute(new Pair<Context, String[]>(CustomerActivity.this, customer));

                showResult(String.format("Saved customer (customerApi): id = %s, name = %s, phone = %s", id, name, phone));
            }
        });

        /*
        * load item list and show last item through customerApi
        */
        mLoadLastCustomerThroughApiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new CustomerListLoaderThroughApiAT().execute(CustomerActivity.this);

                showResult("(customerApi)");
            }
        });
    }

    private void showResult(String result) {
        mShowSavedTextView.setText(result);
    }
}

