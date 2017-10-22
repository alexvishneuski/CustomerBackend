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
import com.github.alexvishneuski.customerbackend.asynctask.EndpointsAsyncTask;


public class CustomerActivity extends AppCompatActivity {

    private EditText mInputIdEditText;
    private EditText mInputNameEditText;
    private EditText mInputPhoneEditText;
    private View mSaveCustomerButton;
    private View mLoadLastCustomerButton;
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


        mSaveCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = mInputIdEditText.getText().toString();
                String name = mInputNameEditText.getText().toString();
                String phone = mInputPhoneEditText.getText().toString();
                String[] customer = {id, name, phone};

               // new EndpointsAsyncTask().execute(new Pair<Context, String[]>(CustomerActivity.this, customer));
                new CustomerListLoaderAT().execute(CustomerActivity.this);

                showResult(String.format("Saved customer: id = %s, name = %s, phone = %s",id,name,phone));
                mShowSavedTextView = (TextView) findViewById(R.id.show_saved_text_view);
            }
        });

        mLoadLastCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  String id = mInputIdEditText.getText().toString();
                String name = mInputNameEditText.getText().toString();
                String phone = mInputPhoneEditText.getText().toString();
                String[] customer = {id, name, phone};*/

//                new EndpointsAsyncTask().execute(new Pair<Context, String[]>(CustomerActivity.this, customer));
                new CustomerListLoaderAT().execute(CustomerActivity.this);

//                showResult(String.format("Saved customer: id = %s, name = %s, phone = %s",id,name,phone));
//                mShowSavedTextView = (TextView) findViewById(R.id.show_saved_text_view);
            }
        });

    }

    private void showResult(String result) {
        mShowSavedTextView.setText(result);
    }

}

