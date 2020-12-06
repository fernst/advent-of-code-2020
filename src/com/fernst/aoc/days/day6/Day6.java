package com.fernst.aoc.days.day6;

import com.fernst.aoc.days.Day;

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
                totalCount += seen.size();
                seen.clear();
            } else {
                processLinePart1(line, seen);
            }
        }

        // Run one more time for the final group.
        totalCount += seen.size();

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
                totalCount += seen.size();
                seen = null; // Need to reset to null.
            } else {
                seen = processLinePart2(line, seen);
            }
        }

        // Run one more time for the final group.
        totalCount += seen.size();

        System.out.printf("Total count is %d.\n", totalCount);
    }

    /**
     * Add all seen characters in this line to a set.
     *
     * @param line         the input line representing all options a passenger has selected.
     * @param charsInGroup Set contaling all seen characters for this passenger. It can be null.
     */
    public void processLinePart1(String line, Set<Character> charsInGroup) {
        for (int i = 0; i < line.length(); i++) {
            charsInGroup.add(line.charAt(i));
        }
    }

    /**
     * Returns a set containing the intersection of all the existing characters in a group with the characters present
     * in this line.
     * <p>
     * It the supplies set is null, we assume this is the first line in a group, so we retain all characters for
     * successive intersections as more lines are read
     *
     * @param line         the input line representing all options a passenger has selected.
     * @param charsInGroup Set contaling all seen characters for this passenger. It can be null.
     * @return Set containing all the comon characters seen in this group.
     */
    public Set<Character> processLinePart2(String line, Set<Character> charsInGroup) {
        //Don't do any work if there are already no characters in the intersection set.
        if (charsInGroup != null && charsInGroup.isEmpty()) return charsInGroup;

        Set<Character> charsInLine = new HashSet<>();

        //Reuse existing code.
        processLinePart1(line, charsInLine);

        //This will only happen on the first line for a given group.
        if (charsInGroup == null) return charsInLine;

        charsInGroup.retainAll(charsInLine);

        return charsInGroup;
    }
}
