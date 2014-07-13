package kangchun.hamcrest;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static kangchun.hamcrest.AreEvenNumbers.evenNumbers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by kangchun on 2014-07-13.
 */
@Slf4j
public class HamcrestTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }

    @Test
    public void shouldHaveOnlyEvenNumbers() throws Exception {
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);
        assertThat( numbers, is(evenNumbers()) );
    }

    @Test
    public void shouldNotHaveOddNumbers() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 4, 6, 8, 10);
        assertThat( numbers, not(evenNumbers()) );
    }
}
