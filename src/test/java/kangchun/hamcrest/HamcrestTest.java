package kangchun.hamcrest;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;

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

}
