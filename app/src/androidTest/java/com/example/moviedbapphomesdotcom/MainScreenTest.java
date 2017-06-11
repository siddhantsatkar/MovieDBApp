package com.example.moviedbapphomesdotcom;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.moviedbapphomesdotcom.activities.MainActivity;
import com.example.moviedbapphomesdotcom.activities.TopMoviesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by sid on 11-06-2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void shouldBeAbleToLaunchMainScreen(){
        onView(withText("Welcome!")).check(ViewAssertions.matches(isDisplayed()));
    }
    @Test
    public void clickSearchButton() throws Exception{
        onView(withId(R.id.editText_main_search_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.button_main_search)).perform(click());

    }

    @Test
    public void validateIntentSentToPackage() {
        Intents.init();
        onView(withId(R.id.button_main_top_movies)).perform(click());
        intended(hasComponent(TopMoviesActivity.class.getName()));
    }
}
