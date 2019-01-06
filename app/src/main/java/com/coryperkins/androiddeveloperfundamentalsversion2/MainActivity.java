package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.coryperkins.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;

    private EditText mMessageEditText;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // save a reference to the EditText
        mMessageEditText = findViewById(R.id.editText_main);

        // save a reference to the reply header TextView
        mReplyHeadTextView = findViewById(R.id.text_header_reply);

        // save a reference to the reply TextView
        mReplyTextView = findViewById(R.id.text_message_reply);
    }

    public void launchSecondActivity(View view) {
        // create the intent to start the SecondActivity
        Intent intent = new Intent(this, SecondActivity.class);

        // add the message from the EditText to the intent
        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        // start the SecondActivity and expect a message in response
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // call the parent version of this method.  I don't know what all it does, but I suspect it
        // is always required
        super.onActivityResult(requestCode, resultCode, data);

        // verify that this data is a TEXT_REQUEST reply
        if (requestCode == TEXT_REQUEST) {
            // verify that the TEXT_REQUEST was successful
            if (resultCode == RESULT_OK) {
                // display the reply in the text_message_reply view
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyTextView.setText(reply);

                // set the reply header and reply text views to be visible
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }


}
