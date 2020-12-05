package com.fernst.aoc.days.day3;

import com.fernst.aoc.days.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day3 extends Day {

  public Day3() {
    inputFile = "/Users/fevelasquez/Development/advent-of-code-2020/src/com/fernst/aoc/days/day3/input0.txt";
  }

  @Override
  public void part1() {
    long numTrees = countForDelta(readInput(), 3, 1);

    System.out.println("I've encountered " + numTrees + " trees.");
  }

  @Override
  public void part2() {
    List<String> slope = readInput();

    long result = 1;

    result *= countForDelta(slope, 1, 1);
    result *= countForDelta(slope, 3, 1);
    result *= countForDelta(slope, 5, 1);
    result *= countForDelta(slope, 7, 1);
    result *= countForDelta(slope, 1, 2);

    System.out.println("Result is " + result);

  }

  public long countForDelta(List<String> input, int xDelta, int yDelta) {
    int xPos = 0;
    int yPos = 0;
    long numTrees = 0;

    while (yPos < input.size()) {
      String level = input.get(yPos);
      if (level.charAt(xPos) == '#') numTrees++;

      xPos += xDelta;
      xPos %= level.length();
      yPos += yDelta;
    }

    return numTrees;
  }

  public List<String> readInput() {
    String input = this.getInput();

    return new ArrayList<>(Arrays.asList(input.split("\n")));
  }


}
