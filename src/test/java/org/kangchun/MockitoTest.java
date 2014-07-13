package org.kangchun;

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
        verify(testMock).add(1);
        verify(testMock).add(2);
        verify(testMock).clear();

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
     */
    @Test
    public void spyAnnotationTest() throws Exception {
        doReturn("1").when(spyList).get(0);
        assertThat(spyList.get(0), is("1"));

        doReturn("1").when(spyList2).get(0);
        assertThat(spyList2.get(0), is("1"));
    }
}
