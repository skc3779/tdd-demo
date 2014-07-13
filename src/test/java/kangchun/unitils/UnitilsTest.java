package kangchun.unitils;

import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Created by kangchun on 2014-07-13.
 */
public class UnitilsTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }

    @Data
    class User {
        String id;
        String name;
        Integer age;
        public User(String id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }

    /**
     * 리플렉션 단정문 비교
     * @see
     * <pre>
     *     메소드 단위의 비교가 아니라 클랙스 단위의 비교가 가능
     *     비교가 깔끔하게 이루어 진다.
     * </pre>
     */
    @Test
    public void reflectionEqualTest() {
        User user = new User("1", "홍길동", 15);
        User otherUser = new User("1", "홍길동", 15);
        assertReflectionEquals("User 객체가 동일한지 비교", user, otherUser);
    }

    /**
     * 리플렉션 단정문의 너그러운 비교
     * @see
     * <pre>
     *     LENIENT_ORDER   : Collection 이나 Array를 비교할 때 순서는 무시한다.
     *     IGNORE_DEFAULTS : 예상 객체의 필드 중 타입 기본값을 갖는 필드에 대해서는 비교하지 않는다.
     *     LENIENT_DATES   : 시간이나 날짜 타입은 서로 비교하지 않는다.
     * </pre>
     *
     */
    @Test
    public void reflectionEqualLenientTest() {
        List<Integer> list = Arrays.asList(3, 2, 1);
        assertReflectionEquals(Arrays.asList(1,2,3), list, ReflectionComparatorMode.LENIENT_ORDER);
    }
}