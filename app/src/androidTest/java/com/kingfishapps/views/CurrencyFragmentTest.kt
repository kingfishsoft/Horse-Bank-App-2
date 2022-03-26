package com.kingfishapps.views

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kingfishapps.fx.MainActivity
import com.kingfishapps.fx.R
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrencyFragmentTest
{
    private lateinit  var scenario: FragmentScenario<CurrencyFragment>
    private lateinit var activity:ActivityScenario<MainActivity>;

    @Before
    fun setUp()
    {
        activity= ActivityScenario.launch(MainActivity::class.java);
        activity.moveToState(Lifecycle.State.CREATED);

        scenario=launchFragmentInContainer(themeResId = R.style.Theme_Fx)
        scenario.moveToState(Lifecycle.State.CREATED);
    }

    @Test
    fun SpinnerIsDisplayed()
    {
        onView(withId(R.id.fromSpinner)).check(
            ViewAssertions.matches(isDisplayed())
        )
    }

    @Test
    fun graphIsDisplayed()
    {
            onView(withId(R.id.getTheGraph)).check(
                ViewAssertions.matches(isDisplayed())
            )



    }




    @Test
    fun TestSpinner()
    {

        onView(withId(R.id.fromSpinner)).perform(
            click(),


            )
        Espresso.onData(
            Matchers.allOf(
                Matchers.`is`(Matchers.instanceOf(String::class.java)),
                Matchers.`is`("AUD")
            )
        ).perform(click())

    }
    @Test
    fun LowMemory(){
        scenario.recreate()

    }    }
