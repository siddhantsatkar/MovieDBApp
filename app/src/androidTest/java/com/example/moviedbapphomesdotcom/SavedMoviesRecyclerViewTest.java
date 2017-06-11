package com.example.moviedbapphomesdotcom;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.moviedbapphomesdotcom.activities.SavedMoviesActivity;
import com.example.moviedbapphomesdotcom.activities.TopMoviesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import android.support.test.espresso.contrib.RecyclerViewActions;

/**
 * Created by sid on 11-06-2017.
 */
@RunWith(AndroidJUnit4.class)
public class SavedMoviesRecyclerViewTest {
    @Rule
    public ActivityTestRule<SavedMoviesActivity> act = new ActivityTestRule<>(SavedMoviesActivity.class);
    @Test
    public void clickItem() {
        onView(withId(R.id.rv_saved_movies))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }
}
