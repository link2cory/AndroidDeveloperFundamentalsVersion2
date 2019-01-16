package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowAlert(View view) {
        // create the Alert dialog builder
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);

        // set the dialog title and message
        myAlertBuilder.setTitle(R.string.alert_title);
        myAlertBuilder.setMessage(R.string.alert_message);

        // add the dialog buttons
        myAlertBuilder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked OK button
                Toast.makeText(getApplicationContext(), getString(R.string.ok_message), Toast.LENGTH_SHORT).show();
            }
        });
        myAlertBuilder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User cancelled the dialog
                Toast.makeText(getApplicationContext(), getString(R.string.cancelled_message), Toast.LENGTH_SHORT).show();
            }
        });

        myAlertBuilder.show();
    }
}
