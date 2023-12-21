/*****************************************************************
 * Copyright (c) 2017 EcoleTree. All Rights Reserved.
 *
 * Author : HyungSeok Kim
 * Create Date : 2023. 12. 22.
 * File Name : Java8Interface.java
 * DESC : 
 *****************************************************************/
package me.study.streams.ch00;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Default method + static method
 * @FuntionalInterface
 * Multiple Inheritance
 */
public class Java8Interface {
    public static void main(String[] args) {
        new TestClass().sum(1,2,3,4);
        new TestClass().anotherSum(1,2,3,4);
    }
}

/**
 * JDK 1.7 이하의 인터페이스
 *
 * {static variable}*
 * {public abstract method}*
 */
interface TraditionalInterface {
    String STATIC_VARIABLE_STRING = "a";
    int STATIC_VARIABLE_INT = 0;

    void printInt(int i);

    String getString();
}

/**
 * @since 1.8
 * JDK 1.8 이상의 버전에서 인터페이스
 *
 * {static variable}*
 * {public abstract method}*
 * {static method (implemented)}*
 * {default method (implemented)}*
 */
interface J8Interface {
    Long STATIC_VARIABLE_LONG = 3L;

    void memberMethod1();

    void memberMethod2();

    default int sum(final int... is) {
        System.out.println("J8Interface#sum");
        return IntStream.of(is).sum();
    }

    static List<Integer> append(final List<Integer> ints, final Integer... is) {
        Collections.addAll(ints, is);
        return ints;
    }
}

/**
 * @since 1.8
 * @see java.lang.FunctionalInterface
 *
 * 단 하나의 abstract method를 가져야 한다.
 * 이 외에 클래스 변수/메서드, default methods 여러개 있어도 상관없음.
 *
 * {static variable}*
 * {public abstract method}*
 * {static method (implemented)}*
 * {default method (implemented)}*
 */
@java.lang.FunctionalInterface
interface FunctionalInterface {
    Long STATIC_VARIABLE_LONG = 3L;

    void requiredOnlyOneAbstractMethod();

    default int sum(final int... is) {
        return IntStream.of(is).sum();
    }

    static List<Integer> append(final List<Integer> ints, final Integer... is) {
        Collections.addAll(ints, is);
        return ints;
    }
}

abstract class IntUtil {

    public abstract int[] range(final int start, final int end);

    public int[] range(final int end) {
        return range(0, 1);
    }

    public int sum(final int... is) {
        System.out.println("IntUtil#sum");
        int res = 0;
        for (final int i : is) {
            res += i;
        }
        return res;
    }
}

/**
 * Occurred Shadowing J8Interface#sum
 *
 * {@link IntUtil#sum}
 * {@link J8Interface#sum}
 */
class TestClass extends IntUtil implements J8Interface {

    @Override
    public void memberMethod1() {

    }

    @Override
    public void memberMethod2() {

    }

    @Override
    public int[] range(final int start, final int end) {
        return new int[0];
    }

    /**
     * Call Shadowed method directly
     *
     * @param is
     * @return
     */
    public int anotherSum(final int... is) {
        return J8Interface.super.sum(is);
    }
}