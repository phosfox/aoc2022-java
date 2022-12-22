package org.example;

import org.example.puzzles.Day02;
import org.example.puzzles.Day05;
import org.example.puzzles.Day06;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var d = new Day06("src/main/resources/day06.txt");
        d.part1();
        d.part2();
    }
}