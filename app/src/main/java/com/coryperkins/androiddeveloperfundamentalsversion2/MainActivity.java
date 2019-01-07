package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // save a reference to the website edit text view
        mWebsiteEditText = findViewById(R.id.website_edittext);
    }

    public void openWebsite(View view) {
        // setup an intent to view the website in the website edit text
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // verify that there is an activity that can support this action and data combination
        if (intent.resolveActivity(getPackageManager()) != null) {
            // send the request to view the website
            startActivity(intent);
        } else {
            // print an error message for debugging
            Log.d("ImplicitIntents", "Can't handle this!");
        }

    }

    public void openLocation(View view) {
    }

    public void shareText(View view) {
    }
}
