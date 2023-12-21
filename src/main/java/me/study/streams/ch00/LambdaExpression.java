/*****************************************************************
 * Copyright (c) 2017 EcoleTree. All Rights Reserved.
 *
 * Author : HyungSeok Kim
 * Create Date : 2023. 12. 22.
 * File Name : LambdaExpression.java
 * DESC : 
 *****************************************************************/
package me.study.streams.ch00;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaExpression {

    // Method Signature vs. Method Reference
    // Method Reference: 파라미터 리스트, 리턴 타입, 타입 파라미터, 예외 리스트
    // Method Signature: 메소드의 이름, 파라미터 리스트
    // closure, shadowing, final, effectively final

    public static void main(String[] args) {
        final Runnable runnable =
                () -> System.out.println("asdasd");

        runnable.run();


        final Calculator<Integer> addition = (o1, o2) -> {
            System.out.printf("%d + %d = %d\n", o1, o2, o1+o2);
            return o1 + o2;
        };

        System.out.println(addition.calc(2, 3));

        final Calculator<String> concat = (s1, s2) -> s1 + s2;
        System.out.println(concat.calc("a", "b"));


        final Function<String, Integer> fn = str -> Integer.parseInt(str);

        final Consumer<String> consumer = str -> str.toString();

        final Predicate<String> predicate = str -> str != null;

        final Supplier<String> supplier = () -> "a";

        fn.apply("1");
        consumer.accept("as");
        predicate.test("as");
        supplier.get();
    }
}

@java.lang.FunctionalInterface
interface Calculator<T> {
    T calc(T operand1, T operand2);
}