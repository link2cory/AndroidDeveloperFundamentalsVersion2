package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // save a reference to the website edit text view
        mWebsiteEditText = findViewById(R.id.website_edittext);

        // save a reference to the location edit text view
        mLocationEditText = findViewById(R.id.location_edittext);

        // save a reference to the share text edit text view
        mShareTextEditText = findViewById(R.id.share_edittext);
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
        // setup an intent to view the location in the location edit text
        String loc = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        // verify that there is an activity that can support this action and data combination
        if (intent.resolveActivity(getPackageManager()) != null) {
            // send the request to view the location
            startActivity(intent);
        } else {
            // print an error message for debugging
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.chooser_title_share_text)
                .setText(txt)
                .startChooser();
    }
}
