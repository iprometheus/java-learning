package com.fangxing.javalearning.jsonlearning;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.verify;

public class MockitoTest {


    /**
     * 验证行为verify
     */
    @Test
    public void testVerify1() {

        List mockList = Mockito.mock(List.class);
        mockList.add(1);
        mockList.clear();

        //验证行为
        verify(mockList).add(1);
        verify(mockList).clear();

    }

    /**
     * 验证行为verify
     */
    @Test
    public void testVerify2() {

        List mockList = Mockito.mock(List.class);
        mockList.add(1);
        mockList.clear();

        //验证行为
        verify(mockList, Mockito.times(1)).add(1);
        verify(mockList).clear();

        //Mockito . verify (mockBean ).someMethod();表示：someMethod方法调用了一次，相当于times(1)
        //Mockito . verify (mock Bean, Mockito.times(n) ).someMethod();表示：someMethod方法调用了n次
        //Mockito . verify (mock Bean, Mockito.never() ).someMethod();表示：someMethod方法未执行
        //Mockito . verify (mock Bean, Mockito. atLeastOnce() ).someMethod();表示：someMethod方法至少执行过一次,相当于atLeast(1)
        //Mockito . verify (mock Bean, Mockito.only() ).someMethod();表示： 仅有someMethod方法执行，且只有一次，不能有其他方法执行
    }


}
