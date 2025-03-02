package com.tmdb.sampleapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tmdb.sampleapp.presentation.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchForMovieTest {
    @get:Rule
    val homeActivity = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun check_if_edit_text_is_displayed(){
        onView(ViewMatchers.withId(R.id.searchMovie)).check(matches(isDisplayed()))
    }
}