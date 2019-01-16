package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        String message = "Order: " + ((Intent) intent).getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);

        // Create the spinner
        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this,
                R.array.labels_array,
                android.R.layout.simple_spinner_item
        );

        // specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // apply the adapter to the spinner
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    public void onRadioButtonClicked(View view) {
        // check if the button is now checked
        if (((RadioButton) view).isChecked()) {
            // send the appropriate message based on which button was clicked
            switch (view.getId()) {
                case R.id.sameday:
                    displayToast(getString(R.string.same_day_messenger_service));
                    break;
                case R.id.nextday:
                    displayToast(getString(R.string.next_day_ground_delivery));
                    break;
                case R.id.pickup:
                    displayToast(getString(R.string.pick_up));
                    break;
                default:
                    // should never get here
                    // do nothing
                    break;
            }
        }

    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerLabel = parent.getItemAtPosition(position).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void processDatePickerResult(int year, int month, int dayOfMonth) {
        String message =
                getString(R.string.date_message_prefix)
                + " "
                + Integer.toString(month)
                + "/"
                + Integer.toString(dayOfMonth)
                + "/"
                + Integer.toString(year);

        displayToast(message);
    }

    public void showDatePicker(View view) {
        new DatePickerFragment().show(getSupportFragmentManager(), getString(R.string.datepicker));
    }
}
