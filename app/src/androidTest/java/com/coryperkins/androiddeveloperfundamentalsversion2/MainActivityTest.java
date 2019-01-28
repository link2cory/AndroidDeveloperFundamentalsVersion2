package com.coryperkins.androiddeveloperfundamentalsversion2;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.word), withText("+ Word 20"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerview),
                                        13),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("+ Word 20")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void clickItemsTest() {
        int num_elements = mActivityTestRule.
                getActivity().
                getResources().
                getInteger(R.integer.num_elements);

        String word_item_prefix = mActivityTestRule
                .getActivity()
                .getResources()
                .getString(R.string.word_item_prefix);

        String word_item_clicked = mActivityTestRule
                .getActivity()
                .getResources()
                .getString(R.string.word_item_prefix);

        ViewInteraction recyclerView = onView(withId(R.id.recyclerview));

        // click each child element of the recycler view and check that its text is correct
        for (int i = 0; i < num_elements; i++) {
            // scroll to the next position
            onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollToPosition(i));

            // click the element
            onView(atRecyclerViewAdapterPosition(withId(R.id.recyclerview), i))
                    .perform(click());


            // check that the element's text changed appropriately
            onView(withParent(atRecyclerViewAdapterPosition(withId(R.id.recyclerview), i)))
                    .check(matches(withText("Clicked! " + word_item_prefix + i)));
        }
    }

    public static Matcher<View> atRecyclerViewAdapterPosition(
            final Matcher<View> recyclerViewMatcher,
            final int position
    ) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(View item) {
                Boolean match = false;

                // verify that the recycler view is the parent of the View
                if (recyclerViewMatcher.matches(item.getParent())) {
                    // ok since we know that is true we can use item.getParent as the
                    // recycler view
                    RecyclerView parent = (RecyclerView) item.getParent();

                    // get the ViewHolder at the adapter position in question
                    RecyclerView.ViewHolder viewHolder = parent.findViewHolderForAdapterPosition(position);

                    // check if the viewHolder's itemView is the same as the item
                    if (viewHolder.itemView == item) {
                        match = true;
                    }
                }

                return match;
            }
        };
    }

}