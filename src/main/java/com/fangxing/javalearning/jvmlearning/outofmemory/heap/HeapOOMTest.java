package com.fangxing.javalearning.jvmlearning.outofmemory.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * VM参数： -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOMTest {

    static class OOMObject {
    }

    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }

    }

}
