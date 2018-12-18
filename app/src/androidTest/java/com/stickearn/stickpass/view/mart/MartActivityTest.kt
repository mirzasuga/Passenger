package com.stickearn.stickpass.view.mart

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.stickearn.stickpass.R
import com.stickearn.stickpass.view.scan.ScanActivity
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Created by oohyugi on 2/13/18.
 */
@RunWith(AndroidJUnit4::class)
class MartActivityTest {

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<MartActivity> = ActivityTestRule<MartActivity>(MartActivity::class.java,false,false)


    @Rule
    @JvmField
    var activityTestScan: ActivityTestRule<ScanActivity> = ActivityTestRule<ScanActivity>(ScanActivity::class.java,false,false)

    lateinit var intent: Intent

    @Test
    fun testScan(){

        activityTestScan.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.et_code)).check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.typeText("123"))
        Espresso.onView(ViewMatchers.withId(R.id.fab_scan)).check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())
    }


    @Test
    fun display(){

        activityTestRule.launchActivity(Intent())
        activityTestRule.activity.intent.getStringExtra("martID")
//        Espresso.onView(ViewMatchers.withId(R.id.rv_mart)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.view_touch)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.bottom_sheet)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }


}