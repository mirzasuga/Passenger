package com.stickearn.stickpass.view.survey

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import com.stickearn.stickpass.R
import org.junit.Rule
import org.junit.Test

/**
 * Created by oohyugi on 2/13/18.
 */
class SurveyActivityTest {
    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<SurveyActivity> = ActivityTestRule<SurveyActivity>(SurveyActivity::class.java)


    @Test
    fun displayText(){
        activityTestRule.launchActivity(Intent())
//        Espresso.onView(ViewMatchers.withId(R.id.rv_s)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        Espresso.onView(ViewMatchers.withId(R.id.tvCurrency)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }
}