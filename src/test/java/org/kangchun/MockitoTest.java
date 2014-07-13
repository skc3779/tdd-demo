package org.kangchun;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by kangchun on 2014-07-13.
 */
@Slf4j
public class MockitoTest {

    @Before
    public void setUp() throws Exception {
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

    }

}
