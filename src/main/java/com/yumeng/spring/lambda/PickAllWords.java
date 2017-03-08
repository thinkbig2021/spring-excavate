package com.yumeng.spring.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PickAllWords {
    public static void main(String[] args) {
        Path path = Paths.get(System.getProperty("user.dir")
                + "/src/main/java/com/yumeng/spring/lambda/PickAllWords.java");

        // 1. Java 8 Read File + Stream
        try (Stream<String> stream = Files.lines(path)) {
            List<String> output = stream.flatMap(line -> Stream.of(line.split(" ")))
                    .filter(word -> word.length() > 0).collect(Collectors.toList());
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. BufferedReader + Stream
        try (BufferedReader br = Files.newBufferedReader(path)) {
            List<String> output = br.lines().flatMap(line -> Stream.of(line.split(" ")))
                    .filter(word -> word.length() > 0).collect(Collectors.toList());
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}