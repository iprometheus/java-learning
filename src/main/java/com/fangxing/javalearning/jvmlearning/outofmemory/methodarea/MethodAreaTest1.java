package com.fangxing.javalearning.jvmlearning.outofmemory.methodarea;

import java.util.ArrayList;
import java.util.List;

/**
 * VM参数：-XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class MethodAreaTest1 {

    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        int i=0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }

}
