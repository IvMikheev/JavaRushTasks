package com.javarush.task.task32.task3205;

import java.lang.reflect.Proxy;

public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);
    }

    public static SomeInterfaceWithMethods getProxy() {
        SomeInterfaceWithMethods original = new SomeInterfaceWithMethodsImpl();
        ClassLoader loader = original.getClass().getClassLoader();
        Class<?>[] interfaces = original.getClass().getInterfaces();
        CustomInvocationHandler custom = new CustomInvocationHandler(original);
        return (SomeInterfaceWithMethods) Proxy.newProxyInstance(loader, interfaces, custom);
    }
}