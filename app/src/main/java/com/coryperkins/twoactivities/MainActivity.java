package com.coryperkins.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_BUTTON_ID = "com.coryperkins.twoactivities.extra.button_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void launchSecondActivity(View view) {
        // create an intent to start the SecondActivity
        Intent intent = new Intent(this, Main2Activity.class);

        // send the button id to the SecondActivity
        intent.putExtra(EXTRA_BUTTON_ID, view.getId());

        // start the SecondActivity
        startActivity(intent);
    }
}
