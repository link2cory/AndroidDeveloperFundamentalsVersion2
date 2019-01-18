package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final int MAX_BATTERY_LEVEL = 6;
    private static final int MIN_BATTERY_LEVEL = 0;

    private static final String STATE_BATTERY_LEVEL = "STATE_BATTERY_LEVEL";

    private ImageView mBatteryImage;

    private int mBatteryLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBatteryImage = findViewById(R.id.batteryImage);

        // restore state if necessary
        if (savedInstanceState != null) {
            mBatteryLevel = savedInstanceState.getInt(STATE_BATTERY_LEVEL);
            updateBatteryImage();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_BATTERY_LEVEL, mBatteryLevel);
        super.onSaveInstanceState(outState);
    }

    public void increaseBatteryLevel(View view) {
        if (mBatteryLevel < MAX_BATTERY_LEVEL) {
            mBatteryLevel++;
            updateBatteryImage();
        }
    }

    public void decreaseBatteryLevel(View view) {
        if (mBatteryLevel > MIN_BATTERY_LEVEL) {
            mBatteryLevel--;
            updateBatteryImage();
        }
    }

    private void updateBatteryImage() {
        mBatteryImage.setImageLevel(mBatteryLevel);
    }
}
