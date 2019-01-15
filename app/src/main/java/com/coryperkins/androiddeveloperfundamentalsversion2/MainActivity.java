package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout mToppingsCheckboxContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // save a reference to the toppings checkbox container
        mToppingsCheckboxContainer = findViewById(R.id.toppings_checkbox_container);
    }

    public void displayToppingsToast(View view) {
        String message = "Toppings Selected:";
        boolean noToppingsSelected = true;

        // make sure the toppings checkbox container exists before moving forward
        if (mToppingsCheckboxContainer != null) {
            // iterate over the toppings and append each checked topping to the toast message
            for (int i = 0; i < mToppingsCheckboxContainer.getChildCount(); i++) {
                // get the next topping checkbox
                CheckBox toppingCheckbox = (CheckBox) mToppingsCheckboxContainer.getChildAt(i);
                // check if the topping checkbox is checked
                if (toppingCheckbox.isChecked()) {
                    noToppingsSelected = false;
                    message += " " + toppingCheckbox.getText();
                }
            }
        }

        // as long as a single topping was selected, display a list of selected toppings
        // otherwise display a message saying no toppings selected
        if (noToppingsSelected) {
            message = "No Toppings Selected";
        }

        displayToast(message);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
