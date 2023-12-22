/*****************************************************************
 * Copyright (c) 2017 EcoleTree. All Rights Reserved.
 *
 * Author : HyungSeok Kim
 * Create Date : 2023. 12. 22.
 * File Name : LookAndSay.java
 * DESC :
 *****************************************************************/
package me.study.streams.ch01;

import java.text.NumberFormat;

public class LookAndSay {

    public static void main(String[] args) {
        showLookAndSay(10);
    }

    public static void showLookAndSay(final int round) {
        String s = "1";
        System.out.println(s);
        for (int i = 0; i < round; i++) {
            s = lookAndSay(s);
            System.out.println(s);
//            final String curr = lookAndSay(s);
//            final NumberFormat nf = NumberFormat.getInstance();
//            System.out.printf("[#%d] > length: [%s]: %s%n", i, nf.format(curr.length()), curr.length() > 100 ? curr.substring(0, Math.min(100, curr.length())) + "..." : curr);
//            s = curr;
        }
    }

    public static String findOne(final int round, final int nth) {
        String s = "1";
        for (int i = 0; i < round; i++) {
            s = lookAndSay(s);
        }
        return s.substring(nth - 1, nth);
    }

    public static String lookAndSay(String s) {
        StringBuilder result = new StringBuilder();
        char c = s.charAt(0);
        int count = 1;
        for (int i = 1, len = s.length();
             i < len; i++) {
            if (s.charAt(i) == c) {
                count++;
            } else {
                result.append(count);
                result.append(c);
                c = s.charAt(i);
                count = 1;
            }
        }
        result.append(count);
        result.append(c);
        return result.toString();
    }

    public static String lookAndSayWithStream(final String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .reduce(new StringBuilder(),
                        (result, c) -> {
                            if (result.length() == 0) {
                                result.append(c);
                            } else {
                                char prev = result.charAt(result.length() - 1);
                                if (prev == c) {
                                    int count = Integer.parseInt(result.substring(result.length() - 2, result.length() - 1));
                                    result.replace(result.length() - 2, result.length(), String.valueOf(count + 1));
                                } else {
                                    result.append("1");
                                    result.append(c);
                                }
                            }
                            return result;
                        },
                        (result1, result2) -> result1.append(result2.toString()))
                .toString();
    }
}
