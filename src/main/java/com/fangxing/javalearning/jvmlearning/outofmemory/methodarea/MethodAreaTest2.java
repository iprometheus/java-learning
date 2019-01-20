package com.fangxing.javalearning.jvmlearning.outofmemory.methodarea;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * VM参数:-XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class MethodAreaTest2 {


    public static void main(String[] args) {
        while (true){
            Enhancer enhancer=new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,args);
                }
            });
            enhancer.create();
        }
    }


    static class OOMObject{}

}
