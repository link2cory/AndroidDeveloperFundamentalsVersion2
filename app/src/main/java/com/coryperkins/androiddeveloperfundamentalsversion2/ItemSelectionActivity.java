package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ItemSelectionActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM = "com.coryperkins.androidDeveloperFundamentalsVersion2.extra.item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selection);
    }

    public void addItem(View view) {
        // need to case the view as a button so we can extract its text
        Button b = (Button) view;

        // pass the clicked button's text back to the MainActivity
        Intent return_intent = new Intent();
        return_intent.putExtra(EXTRA_ITEM, b.getText().toString());
        setResult(RESULT_OK, return_intent);

        // terminate this activity
        finish();
    }
}
