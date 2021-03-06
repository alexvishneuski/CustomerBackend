package com.github.alexvishneuski.customerbackend.asynctask;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.AppVersion;
import com.example.DomainApi;
import com.github.alexvishneuski.customerbackend.BuildConfig;
import com.github.alexvishneuski.customerbackend.http.HttpClient;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

public class AppVersionChecker {

    public final String VERSION_URL = BuildConfig.APP_VERSION_URL;

    private Context mContext;

    public AppVersionChecker(Context pContext) {
        mContext = pContext;
    }

    public Boolean checkAppVersion() {

        Integer onDeviceVersion = getAppVersionOnDevice();

        Integer fromServerVersion = getAppVersionFromServer();

        return (fromServerVersion.equals(onDeviceVersion));
    }

    private Integer getAppVersionFromServer() {

        //getting url
        final String url = new DomainApi(VERSION_URL).getLastAppVersionPath();
        MyResponseListener listener = new MyResponseListener();
        new HttpClient().getCurrentAppVersion(url, listener);
        if (listener.getThrowable() != null) {
            //TODO implement error handling on UI
            throw new UnsupportedOperationException(listener.getThrowable());
        }
        return listener.getVersion().getId();
    }


    private Integer getAppVersionOnDevice() {
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
        }
        return 0;
    }

    private static class MyResponseListener implements HttpClient.ResponseListener {

        private AppVersion version;
        private Throwable mThrowable;

        @Override
        public void onResponse(final InputStream pInputStream) throws Exception {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(pInputStream);
                version = new GsonBuilder()
                        .setLenient()
                        .create().fromJson(inputStreamReader, AppVersion.class);
            } finally {
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (final Exception ignored) {
                    }
                }
            }
        }

        public Throwable getThrowable() {
            return mThrowable;
        }

        @Override
        public void onError(final Throwable pThrowable) {
            mThrowable = pThrowable;
        }

        public AppVersion getVersion() {
            return version;
        }
    }
}
