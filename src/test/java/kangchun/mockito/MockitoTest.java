package kangchun.mockito;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by kangchun on 2014-07-13.
 */
@Slf4j
public class MockitoTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        System.out.println("setUp");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }

    @Test
    public void slf4Test() throws  Exception {
        log.info("test {}", "hello");
    }

    /**
     * Mock 생성 -> 사용 -> 검증의 단계 테스트
     * @throws Exception
     */
    @Test
    public void verifyTest() throws Exception {

        // 생성
        List<Integer> testMock = mock(ArrayList.class);

        // 사용
        testMock.add(1);
        testMock.add(2);
        testMock.clear();

        // 검증
//        verify(testMock).add(1);
//        verify(testMock).add(2);
//        verify(testMock).clear();

        // 최소한 1번 호출되었는지 검증
        verify(testMock, atLeastOnce()).add(anyInt());

        // 최소한 2번 호출되었는지 검증
        verify(testMock, atLeast(2)).add(anyInt());

        // 2와 clear가 1번 호출되었는지 검증
        verify(testMock, times(1)).add(2);
        verify(testMock, times(1)).clear();

        // 호출순서 검증
        List<Integer> oneMock = mock(ArrayList.class);
        List<Integer> twoMock = mock(ArrayList.class);
        oneMock.add(1);
        twoMock.add(1);
        InOrder order = inOrder(oneMock, twoMock);
        order.verify(oneMock).add(1);
        order.verify(twoMock).add(1);
    }

    /**
     * Void Method Stub 만들기
     * @throws Exception
     */
    @Test
    public void stubVoidMethodTest() throws Exception {
        // 생성
        List<Integer> testMock = mock(ArrayList.class);

        // 사용
        testMock.add(1);
        testMock.add(2);
        testMock.clear();

        // void 메소드 Stub 만들기
        doThrow(new RuntimeException()).when(testMock).clear();

    }

    /**
     * 실제 객체를 Stub 만들기
     * @throws Exception
     */
    @Test
    public void stubObjectTest() throws Exception {
        Map<String, Object> testMock = mock(Map.class);

        testMock.put("one", "1");

        when(testMock.get("two")).thenReturn("2");
        when(testMock.get("three")).thenReturn("3");

        assertThat(testMock.get("two"), is("2"));
        assertThat(testMock.get("three"), is("3"));
    }

    /**
     * Samrt Null 테스트
     * @throws Exception
     */
    @Test
    public void smartNullTest() throws Exception {
        // 생성
        List<Integer> testMock = mock(ArrayList.class);
        List<Integer> smartTestMock = mock(ArrayList.class, RETURNS_SMART_NULLS);

        System.out.println(testMock.toArray());
        System.out.println(smartTestMock.toArray());
    }

    /**
     * Reset 기능제공
     * @since 1.8
     * @throws Exception
     */
    @Test
    public void resettingTest() throws Exception {
        List<Integer> testMock = mock(ArrayList.class);
        when(testMock.size()).thenReturn(10);
        assertThat(testMock.size(), is(10));
        // Reset
        reset(testMock);
        assertThat(testMock.size(), is(0));
    }

    /**
     * CallBack으로 Stub 만들기
     * @throws Exception
     */
    @Test
    public void callbackStubTest() throws Exception {

        UserDao userDao = mock(UserDao.class);

        when(userDao.findById("1")).thenAnswer(new Answer<User>() {
            @Override
            public User answer(InvocationOnMock invocationOnMock) throws Throwable {
                User user = new User();
                user.setId("1");
                user.setName("홍길동");
                user.setAge(100);
                return user;
            }
        });

        User user = userDao.findById("1");
        assertThat(user.getName(), is("홍길동"));
    }

    class UserDao {
        public User findById(String id) {
            return new User();
        }
    }

    @Data
    class User {
        String id;
        String name;
        Integer age;
    }

    /**
     * spy 객체를 사용하여
     * 실객체 sub 만들기
     * @since 1.8.1
     * @throws Exception
     */
    @Test
    public void spyTest() throws Exception {
        // 실객체
        List list = new LinkedList<>();

        // 실객체를 spy 함
        List spy = spy(list);

        // 실객체를 호출하여 throw IndexOutOfBoundsException을 발생함.
        // when(spy.get(0)).thenReturn("1");

        // stubbing 하기위해서는 doReturn 객체를 사용해야 함.
        doReturn("1").when(spy).get(0);

        assertThat(spy.get(0), is("1"));
    }

    @Spy LinkedList spyList2;
    @Spy List spyList = new LinkedList<>();
    /**
     * @Spy Annotation을 이용한 spy 객체로
     * 실객체 sub 만들기
     * spyList : 감시에 대한 인스턴스를 명시적으로 생성자를 호출하여 만듬.
     * spyLis2 : 감시에 대한 인스턴스를 reflection을 통해 mockito로 생성
     *           @Before 선언된 메소드에 MockitoAnnotations.initMocks(this) 선언해 줘야 한다.
     * @throws Exception
     * @since 1.8.3
     */
    @Test
    public void spyAnnotationTest() throws Exception {
        doReturn("1").when(spyList).get(0);
        assertThat(spyList.get(0), is("1"));

        doReturn("1").when(spyList2).get(0);
        assertThat(spyList2.get(0), is("1"));
    }

    /**
     * 타임아웃 검증기능
     * @see
     * <pre>
     *     프로그램이 동시에 실행되는 경우에 대해 테스트할 때 유용할 것이라고 생각됨
     *     이 기능은 거의 사용되지 않아야 하지만, 멀티 스레드 시스템을 테스트할 때에는 도움이 될것으로 보임.
     *     InOrder(순서대로 실행되는지 체크)를 이용한 검증은 아직 구현되지 않음
     * </pre>
     * @throws Exception
     */
    @Test
    public void timeoutTest() throws Exception {
        // 생성
        List<Integer> testMock = mock(ArrayList.class);

        // 사용
        testMock.add(1);
        testMock.add(2);
        testMock.clear();

        // 2와 clear가 1번 호출되었는지 검증
        verify(testMock, timeout(1000).times(1)).add(2);
        verify(testMock, timeout(1000).times(1)).clear();
    }

    /**
     * 불필요하기 실행되는 코드를 찾아보기
     * @see
     * <pre>
     *     경고 : 고전적인 방법인 expect-run-verify 방식의 테스트를 하는 사람들은 verifyNoMoreInteractions()를 너무 자주,
     *     심지어는 모든 테스트에서 사용하는 경향이 있습니다.
     *     verifyNoMoreInteractions()는 아무 테스트에서나 무분별하게 사용되지 않았으면 합니다.
     *     verifyNoMoreInteractions()는 실행 여부를 검사하는 테스트 툴에서 간편하게 사용될 수 있는
     *     assertion 방법입니다. 정말 적절한 상황에서만 사용하세요.
     *     이 method를 불필요하게 많이 사용하게 되면 유지보수가 힘든 테스트가 만들어집니다.
     *     여기를 참조하면 더 많은 정보를 얻을 수 있습니다.
     * </pre>
     * @throws Exception
     */
    @Test
    public void verifyNoMoreInteractionsTest() throws Exception {
        // 생성
        List<Integer> testMock = mock(ArrayList.class);

        testMock.add(1);
        // testMock.add(3);

        // 일반적인 검증으로 add(1) 호출하였는지
        verify(testMock).add(1);

        // 메소드가 호출되지 않았는지 검증
        verify(testMock, never()).add(2);

        // 사용되지 않은 When 이 있으면
        // testMock.add(3); (주석처리하면 성공)
        // 이구문은 실패할 것입니다.
        verifyNoMoreInteractions(ignoreStubs(testMock));
    }

    /**
     * mock 실행되지 않았는지 확인하기
     * @throws Exception
     */
    @Test
    public void verifyZeroInteractionsTest() throws Exception {
        // given
        List<Integer> testMock = mock(ArrayList.class);
        List<Integer> twoMock = mock(ArrayList.class);

        testMock.add(1);

        // 일반적인 검증으로 add(1) 호출하였는지
        verify(testMock).add(1);

        // 메소드가 호출되지 않았는지 검증
        verify(testMock, never()).add(2);

        // 다른 mock이 호출되지 않았는지 검증
        verifyZeroInteractions(ignoreStubs(twoMock));
    }


    @Test
    public void mockingDetailsTest() throws Exception {

    }

    @Test
    public void delegateTest() throws Exception {

    }

    @Test
    public void mockMakerTest() throws Exception {

    }
}
