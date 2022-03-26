package com.kingfishapps.fx

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest
{
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp()
    {
        scenario= ActivityScenario.launch(MainActivity::class.java);
        scenario.moveToState(Lifecycle.State.RESUMED);
    }


    @Test
    fun Test()
    {
        //Espresso.onView(withId(R.id.profile_image)).perform(ViewActions.click());
    }
    @Test
    fun Resumed(){
        scenario.moveToState(Lifecycle.State.RESUMED)

    }    }
