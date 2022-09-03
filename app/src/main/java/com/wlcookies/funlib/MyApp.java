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

        initHttp();
    }

    private void initHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        EasyConfig.with(okHttpClient)
                .setLogEnabled(BuildConfig.DEBUG)
                .setServer("https://cn.bing.com")
                .setHandler(new IRequestHandler() {
                    @NonNull
                    @Override
                    public String requestSucceed(@NonNull HttpRequest<?> httpRequest, @NonNull Response response, @NonNull Type type) throws Exception {
                        Log.d(TAG, "requestSucceed: type" + type);
                        return response.body().string();
                    }

                    @NonNull
                    @Override
                    public Exception requestFail(@NonNull HttpRequest<?> httpRequest, @NonNull Exception e) {
                        return new Exception(e.getMessage());
                    }
                })
                .into();
    }
}
