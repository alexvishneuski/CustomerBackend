package com.github.alexvishneuski.customerbackend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class CheckVersionActivity extends AppCompatActivity {
    private View mUpdateAppversionNowButton;
    private View mUpdateAppversionLaterButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_version);
        initView();
    }

    private void initView() {

        mUpdateAppversionNowButton = findViewById(R.id.update_appversion_now_button);
        mUpdateAppversionLaterButton = findViewById(R.id.update_appversion_later_button);

        //TODO perform version cheking throuth AsuncTask, during it process show some indicator

        //TODO if client version is lower -> make cancel button inaktiv
//        mUpdateAppversionLaterButton.setEnabled(false);

        mUpdateAppversionNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO perform updating imitation, during it process show some indicator
            }
        });

        mUpdateAppversionLaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO go to BackendActivity
            }
        });


    }
}
