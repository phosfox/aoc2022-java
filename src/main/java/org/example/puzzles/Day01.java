package org.example.puzzles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day01 {
    private final List<Integer> elvesCalories;

    public Day01(String path) throws IOException {
        String input = Files.readString(Paths.get(path));
        this.elvesCalories = Arrays.stream(input.split("\n\n"))
                .map(ss -> Stream.of(ss.split("\n"))
                        .map(Integer::parseInt)
                )
                .map(nn -> nn
                        .reduce(0, Integer::sum)).collect(Collectors.toList());
    }

    public void part1() {
        var solution = this.elvesCalories.stream()
                .max(Integer::compare)
                .orElseThrow();

        System.out.println(solution);
    }

    public void part2() {
        var solution = this.elvesCalories.stream()
                .sorted((o1, o2) -> o2 - o1)
                .limit(3)
                .reduce(0, Integer::sum);

        System.out.println(solution);
    }
}
