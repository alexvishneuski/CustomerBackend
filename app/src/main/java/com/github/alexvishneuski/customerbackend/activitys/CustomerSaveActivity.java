package com.github.alexvishneuski.customerbackend.activitys;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alex.myapplication.backend.customerApi.CustomerApi;
import com.github.alexvishneuski.customerbackend.R;
import com.github.alexvishneuski.customerbackend.asynctask.CustomerApiBuilder;

import java.io.IOException;


public class CustomerSaveActivity extends AppCompatActivity {

    private EditText mInputIdEditText;
    private EditText mInputNameEditText;
    private EditText mInputPhoneEditText;
    private View mSaveCustomerButton;
    private TextView mShowSavedTextView;

    @Override
    protected void onCreate(@Nullable Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_customer_save);
        initView();
    }

    private void initView() {

        mInputIdEditText = (EditText) findViewById(R.id.input_id_field_edit_text);
        mInputNameEditText = (EditText) findViewById(R.id.input_name_field_edit_text);
        mInputPhoneEditText = (EditText) findViewById(R.id.input_phone_field_edit_text);
        mSaveCustomerButton = findViewById(R.id.save_button);


        mSaveCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = mInputIdEditText.getText().toString();
                String name = mInputNameEditText.getText().toString();
                String phone = mInputPhoneEditText.getText().toString();

                new EndpointsAsyncTask().execute(id, name, phone);


            }
        });
        mShowSavedTextView = (TextView) findViewById(R.id.show_saved_text_view);
    }

    private void showResult(String result) {
        mShowSavedTextView.setText(result);
    }


    class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
        private CustomerApi myApiService = null;

        @Override
        protected String doInBackground(String... customerFields) {
            if (myApiService == null) {  // Only do this once
                myApiService = CustomerApiBuilder.buildApi();
            }

            String id = customerFields[0];
            String name = customerFields[1];
            String phone = customerFields[2];

            try {
                myApiService.insert(id, name, phone).execute();
                //  myApiService.get(Long.valueOf(id));
                return "Inserted";
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            finish();
        }
    }
}

