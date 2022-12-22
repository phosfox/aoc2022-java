package org.example.puzzles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day06 {
    private final String input;

    public Day06(String path) throws IOException {
        this.input = Files.readString(Path.of(path));
    }


    public void part1() {
        var sequence = List.of(input.split(""));
        var windowSize = 4;
        for (int i = 0; i <= sequence.size() - windowSize; i++) {
            var window = sequence.subList(i, i + windowSize);
            if ((int) window.stream().distinct().count() == windowSize) {
                System.out.println("Index " + (i + windowSize));
                System.out.println(window);
                break;
            }
        }
    }

    public void part2() {
        var sequence = List.of(input.split(""));
        var windowSize = 14;
        for (int i = 0; i <= sequence.size() - windowSize; i++) {
            var window = sequence.subList(i, i + windowSize);
            if ((int) window.stream().distinct().count() == windowSize) {
                System.out.println("Index " + (i + windowSize));
                System.out.println(window);
                break;
            }
        }
    }
}
