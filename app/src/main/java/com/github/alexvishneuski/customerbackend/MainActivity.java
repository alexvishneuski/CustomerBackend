package com.github.alexvishneuski.customerbackend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startCheckAppVersionActivity();

//        startCustomerActivity();

    }

    private void startCheckAppVersionActivity() {
        startActivity(new Intent(this, CheckAppVersionActivity.class));
    }


    private void startCustomerActivity() {
        startActivity(new Intent(this, CustomerActivity.class));
    }
}
