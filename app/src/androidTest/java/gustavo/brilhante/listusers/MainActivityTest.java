package gustavo.brilhante.listusers;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gustavo.brilhante.listusers.activity.MainActivity;
import gustavo.brilhante.listusers.activity.MainActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Gustavo on 23/05/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity_.class, true, true);

    @Test
    public void testInterface() throws Exception {
        Thread.sleep(10000);

        onView(withId(R.id.recyclerView)).perform(swipeUp());
        Thread.sleep(2000);

        onView(withId(R.id.recyclerView)).perform(click());
        Thread.sleep(2000);

        MainActivity_ activity = (MainActivity_) mActivityRule.getActivity();
        if(activity.isTablet){
            onView(withId(R.id.recyclerView)).perform(swipeUp());
            Thread.sleep(500);

            onView(withId(R.id.recyclerView)).perform(click());
            Thread.sleep(500);


            onView(withId(R.id.recyclerView)).perform(swipeDown());
            Thread.sleep(500);
            onView(withId(R.id.recyclerView)).perform(click());
            Thread.sleep(5000);

        }

    }
}
