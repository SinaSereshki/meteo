package io.github.aimsio.meteo

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.Description
import org.junit.runner.RunWith
import java.util.EnumSet.allOf
import java.util.regex.Matcher


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SearchInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("io.github.aimsio.meteo", appContext.packageName)
    }

    @Test
    fun testCurrentWeather(){
        onView(withId(R.id.et_search_city)).perform(typeText("Vancouver"), closeSoftKeyboard())
        onView(withId(R.id.ib_search_city)).perform(ViewActions.click())
        onView(withId(R.id.tv_city_name)).check(matches(withText("Vancouver")))
    }

    @Test
    fun testFutureWeather(){
        onView(withId(R.id.et_search_city)).perform(typeText("Vancouver"), closeSoftKeyboard())
        onView(withId(R.id.ib_search_city)).perform(ViewActions.click())
        onView(withId(R.id.rv_future_weather)).check(matches(hasChildCount(3)));
    }

    @Test
    fun testNews(){
        onView(withId(R.id.et_search_city)).perform(typeText("Vancouver"), closeSoftKeyboard())
        onView(withId(R.id.ib_search_city)).perform(ViewActions.click())
        onView(withId(R.id.rv_news)).check(matches(hasMinimumChildCount(1)));
    }
}