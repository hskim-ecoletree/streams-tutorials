/*****************************************************************
 * Copyright (c) 2017 EcoleTree. All Rights Reserved.
 *
 * Author : HyungSeok Kim
 * Create Date : 2023. 12. 22.
 * File Name : StreamApi.java
 * DESC : 
 *****************************************************************/
package me.study.streams.ch01;

import java.util.List;

public class StreamApi {
    public static final List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);

    private static void imperative() {
        Integer result = null;
        for (final Integer number : numbers) {
            if (number > 3 && number < 9) {
                final int newNumber = number * 2;
                if (newNumber > 10) {
                    result = newNumber;
                    break;
                }
            }
        }
        System.out.println("Imperative Result: " + result);

        // Operation: 15
    }

    private static void declarative() {
        System.out.println("Declarative Result: " +
                numbers.stream()
                        .filter(number -> number > 3)
                        .filter(number -> number < 9)
                        .map(number -> number * 2)
                        .filter(number -> number > 10)
                        .findFirst());

        // Operation: 27? 15?
    }
}
