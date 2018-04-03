package com.fangxing.javalearning.apachecommonslearning;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

/**
 * 集合的测试方法
 */
public class CollectionTest {

    @Test
    public void arrayTest(){
        int[] data={1,3,5,7,9};
        System.out.println(ArrayUtils.toString(data));
    }

}