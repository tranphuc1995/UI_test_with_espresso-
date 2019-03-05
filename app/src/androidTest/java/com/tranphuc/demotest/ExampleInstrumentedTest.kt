package com.tranphuc.demotest

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    public val rule = IntentsTestRule<LoginActivity>(LoginActivity::class.java)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.tranphuc.demotest", appContext.packageName)
    }

    @Test
    fun checkEdtUserNameCanEnterText() {
        Espresso.onView(withId(R.id.edtUserName)).perform(ViewActions.typeText("phuc"))
    }

    @Test
    fun checkEdtPasswordCanEnterText() {
        Espresso.onView(withId(R.id.edtPassword)).perform(ViewActions.typeText("12345678"))
    }

    @Test
    fun checkLoginSuccess() {
        Espresso.onView(withId(R.id.edtUserName)).perform(ViewActions.typeText("p"))
        Espresso.onView(withId(R.id.edtPassword)).perform(ViewActions.typeText("1"))
        Espresso.onView(withId(R.id.btnLogin)).perform(ViewActions.click())
        intended(hasComponent(MainActivity::class.java.name))
    }

    @Test
    fun checkLoginFailed() {
        Espresso.onView(withId(R.id.edtUserName)).perform(ViewActions.typeText("p2"))
        Espresso.onView(withId(R.id.edtPassword)).perform(ViewActions.typeText("1"))
        Espresso.onView(withId(R.id.btnLogin)).perform(ViewActions.click())
        Espresso.onView(withText("sai")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }
}
