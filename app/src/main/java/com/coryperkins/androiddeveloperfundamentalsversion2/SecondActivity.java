package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_REPLY = "com.coryperkins.twoactivities.extra.REPLY";
    private EditText mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // save a reference to the EditText
        mReply = findViewById(R.id.editText_second);

        // get the message passed from the MainActivity
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // display the message in the text_message view
        TextView textView = findViewById(R.id.text_message);
        textView.setText(message);
    }

    public void returnReply(View view) {
        // create an intent to return to the MainActivity
        Intent replyIntent = new Intent();

        // add the message from the EditText to the intent
        String reply = mReply.getText().toString();
        replyIntent.putExtra(EXTRA_REPLY, reply);

        // send the data to the MainActivity
        setResult(RESULT_OK, replyIntent);


        // Display a Debug Message so we know that this method Happened
        Log.d(LOG_TAG, "End SecondActivity");

        // return to the MainActivity
        finish();
    }
}
