package com.coryperkins.ImplicitIntents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // extract the incoming URI and convert it to a string
        Intent intent = getIntent();
        Uri uri = intent.getData();

        // convert the URI to a string
        String uri_string = null;
        if (uri != null) {
            uri_string = "URI: " + uri.toString();
        }

        // display the URI string in the uri_string text view
        TextView textView = findViewById(R.id.text_uri_message);
        textView.setText(uri_string);


    }
}
