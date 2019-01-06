package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.coryperkins.androiddeveloperfundamentalsversion2.MainActivity;

public class Main2Activity extends AppCompatActivity {

    private TextView mDisplayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // save a reference to the DisplayText View
        mDisplayText = findViewById(R.id.text_display);

        // display a different message depending on which button id we received
        String message;
        switch(getIntent().getIntExtra(MainActivity.EXTRA_BUTTON_ID, 0)) {
            case R.id.hello_button:
                message = "Hello!";
                break;
            case R.id.world_button:
                message = "World!";
                break;
            case R.id.goodbye_button:
                message = "Goodbye!";
                break;
            default:
                message = "Something Went Wrong!";
                break;
        }
        mDisplayText.setText(message);
    }
}
