package org.example.puzzles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A = X = Rock
 * B = Y = Paper
 * C = Z = Scissors
 */
public class Day02 {

    private static final class Pair<T> {

        final T left;
        final T right;

        private Pair(T left, T right) {
            this.left = left;
            this.right = right;
        }

        public static <T> Pair<T> of(T left, T right) {
            return new Pair<T>(left, right);
        }
    }

    private final List<String[]> rounds;

    public Day02(String path) {
        try {
            String input = Files.readString(Paths.get(path));
            this.rounds = Arrays.stream(input.split("\n")).map(line -> line.split(" ")).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int outcomeScore(String left, String right) {
        var mappings = Map.of(
                List.of("A", "X"), 3,
                List.of("A", "Y"), 6,
                List.of("A", "Z"), 0,
                List.of("B", "X"), 0,
                List.of("B", "Y"), 3,
                List.of("B", "Z"), 6,
                List.of("C", "X"), 6,
                List.of("C", "Y"), 0,
                List.of("C", "Z"), 3
        );

        return mappings.getOrDefault(List.of(left, right), 0);
    }

    private int scoreOf(String s) {
        var mappings = Map.of("X", 1, "Y", 2, "Z", 3);
        return mappings.getOrDefault(s, 0);
    }

    public void part1() {
        int score = this.rounds.stream().map(round -> {
            var elv = round[0];
            var me = round[1];
            return scoreOf(me) + outcomeScore(elv, me);
        }).reduce(0, Integer::sum);

        System.out.println(score);
    }
}
