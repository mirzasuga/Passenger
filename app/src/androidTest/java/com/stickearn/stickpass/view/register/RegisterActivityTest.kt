package com.stickearn.stickpass.view.register




import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.stickearn.stickpass.R

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.DecimalFormat
import org.junit.BeforeClass





/**
 * Created by oohyugi on 2/9/18.
 */
@RunWith(AndroidJUnit4::class)
class RegisterActivityTest {

    @Rule
    @JvmField
    var activityTestRule:ActivityTestRule<RegisterActivity> = ActivityTestRule<RegisterActivity>(RegisterActivity::class.java)


    @Test
    fun checkViewDisplayed() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.rgetName)).check(matches(isDisplayed())).perform(typeText("yogi putra"))
        onView(withId(R.id.rgetEmail)).check(matches(isDisplayed())).perform(typeText("testingandroid2@gmail.com"))
        onView(withId(R.id.rgetPhoneNumber)).check(matches(isDisplayed())).perform(typeText("1234565775"))
        onView(withId(R.id.rgPass)).check(matches(isDisplayed())).perform(typeText("1234567"))
        onView(withId(R.id.rgConfirmPass)).check(matches(isDisplayed())).perform(typeText("1234567"))
//        onView(withId(R.id.rgbtnRegister)).check(matches(isDisplayed()))
//        onView(withId(R.id.rgbtnLogin)).check(matches(isDisplayed()))
        onView(withId(R.id.rgbtnRegister)).check(matches(isDisplayed()))
        onView(withId(R.id.rgbtnRegister)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.progress_dialog)).check(matches(isDisplayed()))
//        onView(withText("success")).check(matches(isDisplayed()))


}
}