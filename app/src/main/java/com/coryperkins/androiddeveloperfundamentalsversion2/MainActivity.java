package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_ITEM_REQUEST = 1;
    private static final String SAVED_LIST_ITEMS = "saved_list_items";
    private static final String SAVED_LIST_ITEM_COUNT = "saved_list_item_count";
    private LinearLayout mListItemContainer;
    private int mItemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // save a reference to the linear layout
        mListItemContainer = findViewById(R.id.list_item_container);

        // restore any saved list items
        if (savedInstanceState != null) {
            // first restore the item count
            mItemCount = savedInstanceState.getInt(SAVED_LIST_ITEM_COUNT);
            TextView current_list_item;
            // now loop through and restore each item
            for (int i = 0; i < mItemCount; i++) {
                current_list_item = (TextView) mListItemContainer.getChildAt(i);
                current_list_item.setText(savedInstanceState.getStringArray(SAVED_LIST_ITEMS)[i]);
            }
        }
    }

    public void activateItemSelectionActivity(View view) {
        // start the ItemSelectionActivity and expect an "Add Item" response
        Intent intent = new Intent(this, ItemSelectionActivity.class);
        startActivityForResult(intent, ADD_ITEM_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // verify that this was a successful "add item" response
        if (requestCode == ADD_ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                Log.d("MainActivity", "Got a List item: " + data.getStringExtra(ItemSelectionActivity.EXTRA_ITEM));
                // if we have room to display the item, do so
                if (mItemCount < 9) {
                    TextView current_list_item = (TextView) mListItemContainer.getChildAt(mItemCount);
                    current_list_item.setText(data.getStringExtra(ItemSelectionActivity.EXTRA_ITEM));
                    mItemCount++;
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // generate a string array containing each list item
        String[] list_items = new String[10];
        TextView current_list_item;
        for (int i = 0; i < mItemCount; i++) {
            current_list_item = (TextView) mListItemContainer.getChildAt(i);
            list_items[i] = current_list_item.getText().toString();
        }
        savedInstanceState.putStringArray(SAVED_LIST_ITEMS, list_items);

        // also save the item count
        savedInstanceState.putInt(SAVED_LIST_ITEM_COUNT, mItemCount);
    }

}
