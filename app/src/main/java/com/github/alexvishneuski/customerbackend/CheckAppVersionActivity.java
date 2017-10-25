package com.github.alexvishneuski.customerbackend;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ThemedSpinnerAdapter;

import com.github.alexvishneuski.customerbackend.asynctask.AppVersionChecker;


public class CheckAppVersionActivity extends AppCompatActivity {
    private View mUpdateAppversionNowButton;
    private View mUpdateAppversionLaterButton;
    private Context mContext;
    private Boolean versionOk = null;


    private AppVersionChecker mChecker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_app_version);
        initView();
    }

    private void initView() {

        mUpdateAppversionNowButton = findViewById(R.id.update_appversion_now_button);
        mUpdateAppversionLaterButton = findViewById(R.id.update_appversion_later_button);

        //TODO perform version cheking throuth AsuncTask, during it process show some indicator
        //this is in separate thtead
        new Thread(new Runnable() {
            @Override
            public void run() {
                mChecker = new AppVersionChecker(CheckAppVersionActivity.this);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        versionOk = mChecker.checkAppVersion();
                    }
                });
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // mContext = mChecker.getContext();
        mUpdateAppversionLaterButton.setEnabled(versionOk ? true : false);

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
