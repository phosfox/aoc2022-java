package org.example.puzzles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Day05 {
    private final String[] input;

    private static class Instruction {

        private final int to;
        private final int iterations;
        private final int from;

        public Instruction(final int iterations, final int from, final int to) {
            this.iterations = iterations;
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "Instruction{" +
                    "to=" + to +
                    ", iterations=" + iterations +
                    ", from=" + from +
                    '}';
        }
    }


    private Stack<String> toStack(String s) {
        var stack = new Stack<String>();
        stack.addAll(List.of(s.split("")));
        return stack;
    }

    private ArrayList<Stack<String>> getCrates() {
        return  new ArrayList<>(List.of(
                toStack(""),
                toStack("GTRW"),
                toStack("GCHPMSVW"),
                toStack("CLTSGM"),
                toStack("JHDMWRF"),
                toStack("PQLHSWFJ"),
                toStack("PJDNFMS"),
                toStack("ZBDFGCSJ"),
                toStack("RTB"),
                toStack("HNWLC")
        ));
    }

    private List<Instruction> getInstructions() {
        return (Arrays.stream(input[1].split("\n")).map(line -> {
            var pattern = Pattern.compile("\\d+");
            var matcher = pattern.matcher(line);
            var ints = matcher.results().map(MatchResult::group).map(Integer::parseInt).collect(Collectors.toList());
            return new Instruction(ints.get(0), ints.get(1), ints.get(2));
        }).collect(Collectors.toList()));
    }

    public Day05(String path) throws IOException {
        this.input = Files.readString(Path.of(path)).split("\n\n");
    }

    private String topCrates(ArrayList<Stack<String>> crates) {
       return crates.stream().map(s -> {
            if(!s.empty()) {
                return s.peek();
            }
            return "";
        }).reduce("", String::concat);
    }
    public void part1() {
        var crates = getCrates();
        getInstructions().forEach(instruction -> {
            var from = instruction.from;
            var to = instruction.to;
            for (int i = 0; i < instruction.iterations; i++) {
                var crate = crates.get(from).pop();
                crates.get(to).push(crate);
            }
        });
        System.out.println(topCrates(crates));
    }

    public void part2() {
        var crates = getCrates();
        getInstructions().forEach(instruction -> {
            var from = instruction.from;
            var to = instruction.to;
            var tempStack = new Stack<String>();
            for (int i = 0; i < instruction.iterations; i++) {
                var crate = crates.get(from).pop();
                tempStack.push(crate);
            }
            for (int i = 0; i < instruction.iterations; i++) {
                crates.get(to).push(tempStack.pop());
            }
        });
        System.out.println(topCrates(crates));
    }
}
