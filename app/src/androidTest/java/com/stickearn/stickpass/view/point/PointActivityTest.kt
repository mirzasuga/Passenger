package com.stickearn.stickpass.view.point

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.stickearn.stickpass.R
import com.stickearn.stickpass.view.register.RegisterActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.web.model.Atoms.getTitle
import android.support.test.espresso.Espresso.onView
import android.support.annotation.UiThread
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf


/**
 * Created by oohyugi on 2/13/18.
 */
@RunWith(AndroidJUnit4::class)
class PointActivityTest{

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<PointActivity> = ActivityTestRule<PointActivity>(PointActivity::class.java)

    @Test
    fun displayText(){
        activityTestRule.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.tvPoint)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvCurrency)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }
    @Test
    @UiThread
    fun checkTabSwitch() {
        Espresso.onView(allOf(withText("Buy Voucher"), isDescendantOfA(withId(R.id.tabLayoutVoucher))))
                .perform(click())
                .check(matches(isDisplayed()))

    }
    @Test
    fun viewPager(){
        Espresso.onView(ViewMatchers.withId(R.id.viewPagerVoucher))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.swipeLeft())
        Espresso.onView(allOf(withText("My Voucher"), isDescendantOfA(withId(R.id.tabLayoutVoucher))))
    }


}