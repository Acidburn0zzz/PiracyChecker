package com.github.javiersantos.piracychecker.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.github.javiersantos.piracychecker.R;

public class LicenseActivity extends AppCompatActivity {
    private String description;
    private int colorPrimary;
    private int colorPrimaryDark;
    private boolean withLightStatusBar;
    @LayoutRes
    private int layoutXML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        setContentView(layoutXML != -1 ? layoutXML : R.layout.activity_license);
        setActivityStyle();
        setActivityData();
    }

    private void getIntentData() {
        if (getIntent() != null) {
            description = getIntent().getStringExtra("content");
            colorPrimary = getIntent().getIntExtra("colorPrimary",
                    ContextCompat.getColor(this, R.color.colorPrimary));
            colorPrimaryDark = getIntent().getIntExtra("colorPrimaryDark",
                    ContextCompat.getColor(this, R.color.colorPrimaryDark));
            withLightStatusBar = getIntent().getBooleanExtra("withLightStatusBar", false);
            layoutXML = getIntent().getIntExtra("layoutXML", -1);
        }
    }

    private void setActivityStyle() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setBackgroundColor(colorPrimary);
            setSupportActionBar(toolbar);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(ActivityUtils.getAppName(this));
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(colorPrimaryDark);
        }

        ActivityUtils.setupLightStatusBar(getWindow().getDecorView(), withLightStatusBar);
    }

    private void setActivityData() {
        TextView activityDescription = (TextView) findViewById(R.id.piracy_checker_description);
        activityDescription.setText(description);
    }

}