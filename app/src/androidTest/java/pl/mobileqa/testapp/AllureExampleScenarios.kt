package pl.mobileqa.testapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.qameta.allure.android.allureScreenshot
import io.qameta.allure.android.rules.LogcatRule
import io.qameta.allure.android.rules.ScreenshotRule
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Allure.step
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith


@RunWith(AllureAndroidJUnit4::class)
class AllureExampleScenarios {

    @get:Rule
    var testNameRule = TestName()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val successScreenshotRule =
        ScreenshotRule(mode = ScreenshotRule.Mode.SUCCESS, screenshotName = "ss_success")

    @get:Rule
    val failureScreenshotRule =
        ScreenshotRule(mode = ScreenshotRule.Mode.FAILURE, screenshotName = "ss_failure")

    @get:Rule
    val endScreenshotRule =
        ScreenshotRule(mode = ScreenshotRule.Mode.END, screenshotName = "ss_end")

    @get:Rule
    val logcatRule = LogcatRule()

    @Test
    fun helloMessageShouldBeDisplayed() {
        step("Hello message should be displayed") {
            onView(withId(R.id.helloMessage)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun helloMessageShouldBeDisplayedWithScreenshot() {
        step("Message should be displayed") {
            onView(withId(R.id.helloMessage)).check(matches(isDisplayed()))
            allureScreenshot(name = testNameRule.methodName, quality = 100, scale = 100f)
        }
    }

    @Test
    fun helloMessageShouldBeDisplayedWithLogs() {
        step("Message should not be displayed") {
            onView(withId(R.id.helloMessage)).check(matches(not(isDisplayed())))
        }
    }
}