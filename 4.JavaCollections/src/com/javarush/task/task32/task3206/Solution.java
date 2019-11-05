package com.javarush.task.task32.task3206;

import java.lang.reflect.Proxy;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.getProxy(Item.class));
        test(solution.getProxy(Item.class, Small.class));
        test(solution.getProxy(Item.class, Big.class, Small.class));
        test(solution.getProxy(Big.class, Small.class));
        test(solution.getProxy(Big.class));
    }


    private static void test(Object proxy) {
        boolean isItem = proxy instanceof Item;
        boolean isBig = proxy instanceof Big;
        boolean isSmall = proxy instanceof Small;

        System.out.format("%b %b %b\n", isItem, isBig, isSmall);
    }

    public Item getProxy(Class<?> clazz, Class<?>... classes) {
        if (classes.length == 0) {
            return (Item) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {clazz}, new ItemInvocationHandler());
        } else {
            Class<?>[] interfaces = new Class<?>[classes.length + 1];
            interfaces[0] = clazz;
            int index = 0;
            for (int i = 1; i < interfaces.length; i++) {
                interfaces[i] = classes[index];
                index++;
            }
            return (Item) Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces, new ItemInvocationHandler());
        }
    }
}