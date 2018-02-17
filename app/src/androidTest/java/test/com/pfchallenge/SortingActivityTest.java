package test.com.pfchallenge;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import test.com.pfchallenge.activities.SortActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SortingActivityTest {
	@Rule
	public IntentsTestRule<SortActivity> mActivityRule = new IntentsTestRule<>(SortActivity.class);

	@Test
	public void openSortActivityTest() {

		onView(withId(R.id.pd_button)).check(matches(CoreMatchers.notNullValue()));
		onView(withId(R.id.pd_button)).perform(click());

		onView(withId(R.id.pa_button)).check(matches(CoreMatchers.notNullValue()));
		onView(withId(R.id.pa_button)).perform(click());

		onView(withId(R.id.bd_button)).check(matches(CoreMatchers.notNullValue()));
		onView(withId(R.id.bd_button)).perform(click());

		mActivityRule.getActivity().getSelectedOrder().matches("bd");
	}
}
