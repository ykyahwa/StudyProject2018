package com.tistory.ykyh.test_ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by eokhyunlee on 2018. 2. 9..
 */

@RunWith(AndroidJUnit4.class)
public class MainTest {

    //Cheat-sheet - https://developer.android.com/training/testing/espresso/cheat-sheet.html
    //Sample - https://github.com/googlesamples/android-topeka/tree/13102015
    final String MESSAGE = MainActivity.DONE_MESSAGE;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void FAB문자열표시안됨() {
        onView(withText(MESSAGE)).check(doesNotExist());
    }

    @Test
    public void FAB클릭메시지표시() throws Exception {
        onView(withId(R.id.fab)).perform(click());
        onView(withText(MESSAGE)).check(matches(isDisplayed()));
    }
}
