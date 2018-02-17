package test.com.pfchallenge;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import test.com.pfchallenge.activities.MainActivity;
import test.com.pfchallenge.activities.SortActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SortButtonTest {
	@Rule
	public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(MainActivity.class);

	@Test
	public void openSortActivityTest() {

		onView(withId(R.id.action_sort)).check(matches(CoreMatchers.notNullValue()));
		onView(withId(R.id.action_sort)).perform(click());
		intended(hasComponent(SortActivity.class.getName()));
	}
}
