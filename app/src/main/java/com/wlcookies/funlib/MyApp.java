package com.wlcookies.funlib;

import android.app.Application;
import android.util.Log;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.hjq.http.EasyConfig;
import com.hjq.http.config.IRequestHandler;
import com.hjq.http.request.HttpRequest;

import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MyApp extends Application {
    private static final String TAG = "MyApp";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
