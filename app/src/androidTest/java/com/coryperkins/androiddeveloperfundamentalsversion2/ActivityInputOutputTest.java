package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.coryperkins.androiddeveloperfundamentalsversion2", appContext.getPackageName());
    }

    @Test
    public void activityLaunch() {
        // click the button that sends us to the second activity
        onView(withId(R.id.button_main)).perform(click());

        // check if an element from the second activity is displayed
        onView(withId(R.id.text_header)).check(matches(isDisplayed()));

        // click the button that sends us back to the first activity
        onView(withId(R.id.button_second)).perform(click());

        // check if an element from the first activity is displayed
        onView(withId(R.id.text_header_reply)).check(matches(isDisplayed()));
    }

    @Test
    public void textInputOutput() {
        // enter some text
        onView(withId(R.id.editText_main)).perform(typeText("This is a test."));

        // click the button
        onView(withId(R.id.button_main)).perform(click());

        // check if the text shows up on the next activity
        onView(withId(R.id.text_message)).check(matches(withText("This is a test.")));
    }
}
