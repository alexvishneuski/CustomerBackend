package com.github.alexvishneuski.customerbackend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.github.alexvishneuski.customerbackend.asynctask.AppVersionChecker;
import com.github.alexvishneuski.customerbackend.asynctask.PseudoDownloadAT;


public class CheckAppVersionActivity extends AppCompatActivity {

    public static final String APP_SOURCE = "https://google.com";
    private View mUpdateAppversionNowButton;
    private View mUpdateAppversionLaterButton;
    private Boolean isVersionOk = null;
    private AppVersionChecker mChecker;
    private PseudoDownloadAT mPseudoDownload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_app_version);
        initView();
    }

    private void initView() {

        mUpdateAppversionNowButton = findViewById(R.id.update_appversion_now_button);
        mUpdateAppversionLaterButton = findViewById(R.id.update_appversion_later_button);

        //this is in separate thtead
        Toast.makeText(CheckAppVersionActivity.this, "Start VersionChecking", Toast.LENGTH_SHORT).show();
        final Thread checkVersion = new Thread(new Runnable() {
            @Override
            public void run() {

                mChecker = new AppVersionChecker(CheckAppVersionActivity.this);
                isVersionOk = mChecker.checkAppVersion();
            }
        });
        checkVersion.start();

        //waiting response from server
        try {
            checkVersion.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast.makeText(CheckAppVersionActivity.this, (isVersionOk == true) ? "AppVersion is Ok" : "You need update AppVersion", Toast.LENGTH_SHORT).show();


        //if client version is lower -> update later button inaktiv
        mUpdateAppversionLaterButton.setEnabled(isVersionOk);

        //pseudo download
        mUpdateAppversionNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPseudoDownload = new PseudoDownloadAT();
                mPseudoDownload.execute(new Pair<Context, String>(CheckAppVersionActivity.this, APP_SOURCE));
                startActivity(new Intent(CheckAppVersionActivity.this, CustomerActivity.class));
            }
        });

        //go to CustomerActivity
        mUpdateAppversionLaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CheckAppVersionActivity.this, CustomerActivity.class));
            }
        });
    }
}
