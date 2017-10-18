package com.github.alexvishneuski.customerbackend.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.alexvishneuski.customerbackend.R;

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
                String result = "Customer is saved: id = " + mInputIdEditText.getText()
                        + "name = " + mInputNameEditText.getText()
                        + " phone = " + mInputPhoneEditText.getText();
                showResult(result);
            }
        });
        mShowSavedTextView = (TextView) findViewById(R.id.show_saved_text_view);
    }

    private void showResult(String result) {
        mShowSavedTextView.setText(result);
    }


}
