package com.stickearn.stickpass.view.survey_question

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import com.stickearn.stickpass.R
import org.junit.Rule
import org.junit.Test

/**
 * Created by oohyugi on 2/21/18.
 */
class SurveyQuestionActivityTest{
    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<SurveyQuestionActivity> = ActivityTestRule<SurveyQuestionActivity>(SurveyQuestionActivity::class.java)

    @Test
    fun checkViewDisplayed() {
        activityTestRule.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.rvChat)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}