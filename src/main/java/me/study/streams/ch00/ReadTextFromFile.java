/*****************************************************************
 * Copyright (c) 2017 EcoleTree. All Rights Reserved.
 *
 * Author : HyungSeok Kim
 * Create Date : 2023. 12. 22.
 * File Name : ReadTextFromFile.java
 * DESC : 파일로부터 텍스트 읽기
 *****************************************************************/
package me.study.streams.ch00;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadTextFromFile {
    public static final String FILE_NAME = "sample.txt";

    private static void readTextFromFile(final String... stopWords) {
        // TODO: step 1. 파일로부터 텍스트를 한 줄씩 읽기

        // TODO: step 2. 한 줄의 텍스트를 단어로 분리 (공백문자를 기준으로 분리)

        // TODO: step 3. 한 줄의 텍스트 단어 중 stopWords 와 일치하는 단어가 나올 때 까지 단어를 출력 (반복)


        try (
                final FileReader fileReader = new FileReader(FILE_NAME);
                final BufferedReader reader = new BufferedReader(fileReader)
        ) {
            String line;
            lineLoop: while ((line = reader.readLine()) != null) {
                System.out.print("\n");
                final String[] words = line.split("\\s+");
                for (final String word : words) {
                    if (Arrays.asList(stopWords).contains(word)) {
                        continue lineLoop;
                    }
                    System.out.print(word + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            Files.readAllLines(Paths.get(FILE_NAME))
//                .stream()
//                .map(line -> Stream.of(line.split("\\s+")).takeWhile(word -> !Arrays.asList(stopWords).contains(word)).collect(Collectors.joining(" ")))
//                .forEach(System.out::println);
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }

    }

    public static void main(final String... args) {
        readTextFromFile("four", "time");
        /*
        > The fox: "If you come at four in the afternoon, I'll begin to be happy at three. By
        > The fox: "It means that if you come at
        > The Little Prince: "I don't understand."
        > The fox: "It's the
         */
    }
}
