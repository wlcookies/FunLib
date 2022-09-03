package com.wlcookies.funlib;

import android.content.Context;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wlcookies.funlib.databinding.ActivityMainBinding;
import com.wlcookies.funlib.day_image.BingDayImageActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());

        // 必应每日一图
        mActivityMainBinding.dayImageBt.setOnClickListener(v -> {
            startActivity(BingDayImageActivity.newInstance(this));
        });


    }
}