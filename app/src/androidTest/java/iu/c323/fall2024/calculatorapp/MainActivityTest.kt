package iu.c323.fall2024.calculatorapp

import androidx.lifecycle.viewmodel.viewModelFactory
import org.junit.Assert.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

import org.junit.After
import org.junit.Before
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.action.ViewActions.click
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import java.net.IDN

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test//test1 checks to make sure that display is updated when number buttons are pressed
    fun checkInput(){
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.textViewDisplay)).check(matches(withText("2")))
        onView(withId(R.id.four_button)).perform((click()))
        onView(withId(R.id.textViewDisplay)).check(matches(withText("24")))
    }

    @Test
    fun checkOperationDisplay(){//test2 checks for change in display when 2nd input is given but not operation
        onView(withId(R.id.five_button)).perform(click())
        onView(withId(R.id.textViewDisplay)).check(matches(withText("5")))//display shows 5
        onView(withId(R.id.minus_button)).perform(click())
        onView(withId(R.id.textViewDisplay)).check(matches(withText("5")))//display not supposed to change from 5
        onView(withId(R.id.three_button)).perform(click())
        onView(withId(R.id.textViewDisplay)).check(matches(withText("3")))//display changes to new input of 3
    }

    @Test
    fun checkCompleteOperation(){//test3 checks to make sure that an operation can be completed successfully to show accurate final result when equal button is pressed
        onView(withId(R.id.six_button)).perform(click())
        onView(withId(R.id.two_button)).perform(click())//62 entered
        onView(withId(R.id.plus_button)).perform(click())//addition
        onView(withId(R.id.seven_button)).perform(click())//7 pressed
        onView(withId(R.id.equals_button)).perform(click())//equals for result
        onView(withId(R.id.textViewDisplay)).check(matches(withText("69.0")))
    }

    @Test
    fun checkClearButton(){//test4 checks to make sure that display resets when clear is clicked
        onView(withId(R.id.seven_button)).perform(click())//press 7 button
        onView(withId(R.id.c_button)).perform(click())//then clear
        onView(withId(R.id.textViewDisplay)).check(matches(withText("0")))//display should now read 0
    }

    @Test
    fun checkDecimalButton(){//test5 checks to make sure that decimal is portrayed appropriately + value is actually changed
        onView(withId(R.id.nine_button)).perform(click())
        onView(withId(R.id.decimal_button)).perform(click())
        onView(withId(R.id.zero_button)).perform(click())
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.textViewDisplay)).check(matches(withText("9.02")))
    }

    @Test
    fun checkSignButton(){//test6 checks to make sure that numbers can be made neg and vice versa
        onView(withId(R.id.six_button)).perform(click())//6 button pressed
        onView(withId(R.id.textViewDisplay)).check(matches(withText("6")))//display should show 6
        onView(withId(R.id.sign_button)).perform(click())//sign button is pressed
        onView(withId(R.id.textViewDisplay)).check(matches(withText("-6")))
        onView(withId(R.id.percent_button)).perform(click())//percent button is pressed so display should show -0.06
        onView(withId(R.id.textViewDisplay)).check(matches(withText("-0.06")))//making sure that negative actually changed value
    }

    @Test
    fun checkContinuousCalculations(){//test7 will check if preliminary result is shown if operations are continued without equals button
        onView(withId(R.id.seven_button)).perform(click())//seven button is pressed
        onView(withId(R.id.minus_button)).perform(click())//minus clicked
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.zero_button)).perform(click())//10 entered
        onView(withId(R.id.x_button)).perform(click())//multiplication, second operation
        onView(withId(R.id.textViewDisplay)).check(matches(withText("-3.0")))//7-10 = -3
        onView(withId(R.id.two_button)).perform(click())//press 2
        onView(withId(R.id.equals_button)).perform(click())//press equals
        onView(withId(R.id.textViewDisplay)).check(matches(withText("-6.0")))//-3 * 2 = -6
    }

    @Test
    fun checkCalcAfterEquals(){//test8 will check if the calculation can be successfully continued from one complete prelim calculation
        onView(withId(R.id.seven_button)).perform(click())
        onView(withId(R.id.two_button)).perform(click())//72 entered
        onView(withId(R.id.div_button)).perform(click())//division operation
        onView(withId(R.id.four_button)).perform(click())//four pressed
        onView(withId(R.id.equals_button)).perform(click())//equal pressed
        onView(withId(R.id.textViewDisplay)).check(matches(withText("18.0")))// 72/14 = 18, shows the display of first operation
        onView(withId(R.id.div_button)).perform(click())//division operation AFTER equals button
        onView(withId(R.id.six_button)).perform(click())//6 pressed
        onView(withId(R.id.equals_button)).perform(click()) //equals pressed after 18/6 = 3
        onView(withId(R.id.textViewDisplay)).check(matches(withText("3.0"))) //showing final result
    }

  @Test
  fun checkDivZero(){//test9 will check to make sure that app shows appropriate response when there is division by 0
      onView(withId(R.id.five_button)).perform(click())
      onView(withId(R.id.seven_button)).perform(click())//entered 57
      onView(withId(R.id.div_button)).perform(click())//division operation
      onView(withId(R.id.zero_button)).perform(click())//dividing by zero
      onView(withId(R.id.equals_button)).perform(click())
      onView(withId(R.id.textViewDisplay)).check(matches(withText("Infinity")))//infinity on display when dividing by 0
  }

    @Test
    fun checkOrientationSwitch(){//test10 will check to make sure that the display maintains the same values despite simulating orientation change
        onView(withId(R.id.seven_button)).perform(click())
        onView(withId(R.id.x_button)).perform(click())
        onView(withId(R.id.three_button)).perform(click())
        onView(withId(R.id.equals_button)).perform(click())//7 * 3 = 21
        onView(withId(R.id.textViewDisplay)).check(matches(withText("21.0")))//21 on display in vertical
        scenario.recreate() //see if display/values can handle activity recreation, simulating orientation change
        onView(withId(R.id.textViewDisplay)).check(matches(withText("21.0")))//21 should still be in display
    }

}