package kangchun.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Collection;

/**
 * Created by kangchun on 2014-07-13.
 */
public class AreEvenNumbers extends TypeSafeMatcher<Collection<Integer>> {

    @Override
    public boolean matchesSafely(Collection<Integer> numbers) {
        for (Integer number : numbers) {
            if (number % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("even numbers");
    }

    @Factory
    public static <T> Matcher<Collection<Integer>> evenNumbers() {
        return new AreEvenNumbers();
    }
}