package com.fernst.aoc.days.day6;

import com.fernst.aoc.days.Day;
import com.sun.istack.internal.Nullable;

import java.util.HashSet;
import java.util.Set;

public class Day6 extends Day {

    public Day6() {
        inputFile = "./src/com/fernst/aoc/days/day6/input0.txt";
    }

    @Override
    public void part1() {

        String input = this.getInput();

        Set<Character> seen = new HashSet<>();
        int totalCount = 0;

        for (String line : input.split("\n")) {
            if ("".equals(line.trim())) {
                totalCount += countAndReset(seen);
            } else {
                processLine1(line, seen);
            }
        }

        // Run one more time for the final group.
        totalCount += countAndReset(seen);

        System.out.printf("Total count is %d.\n", totalCount);
    }

    @Override
    public void part2() {
        String input = this.getInput();

        Set<Character> seen = null;
        int totalCount = 0;

        for (String line : input.split("\n")) {
            if ("".equals(line.trim())) {
                assert seen != null;
                totalCount += countAndReset(seen);
                seen = null; // Need to reset to null.
            } else {
                seen = processLine2(line, seen);
            }
        }

        // Run one more time for the final group.
        totalCount += countAndReset(seen);

        System.out.printf("Total count is %d.\n", totalCount);
    }

    public void processLine1(String line, Set<Character> charsInGroup) {
        for (int i = 0; i < line.length(); i++) {
            charsInGroup.add(line.charAt(i));
        }
    }

    public Set<Character> processLine2(String line, Set<Character> charsInGroup) {
        Set<Character> charsInLine = new HashSet<>();

        for (int i = 0; i < line.length(); i++) {
            charsInLine.add(line.charAt(i));
        }

        //This will only happen on the first line for a given group.
        if (charsInGroup == null) return charsInLine;

        charsInGroup.retainAll(charsInLine);

        return charsInGroup;
    }

    public int countAndReset(Set<Character> charsInGroup) {
        int size = charsInGroup.size();
        charsInGroup.clear();
        return size;
    }
}
