package com.fernst.aoc.days.day5;

import com.fernst.aoc.days.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day5 extends Day {
  public enum Direction {
    BOTTOM_LEFT,
    TOP_RIGHT;
  }

  public Day5() {
    inputFile = "./src/com/fernst/aoc/days/day5/input0.txt";
  }

  @Override
  public void part1() {
    int maxSeatId = -1;
    String input = getInput();

    for (String line : input.split("\n")) {
      int seatId = findSeatId(line);
      maxSeatId = Math.max(maxSeatId, seatId);
    }

    System.out.println(String.format("Max seat ID is %d", maxSeatId));
  }

  @Override
  public void part2() {
    List<Integer> seenSeatIds = new ArrayList<>();
    String input = getInput();

    for (String line : input.split("\n")) {
      int seatId = findSeatId(line);
      seenSeatIds.add(seatId);
    }

    //Sort list, O(N log N) runtime
    seenSeatIds.sort(Comparator.naturalOrder());

    int seatId = -1;

    for (int i = 0; i < seenSeatIds.size() - 1; i++) {
      if (seenSeatIds.get(i) + 1 != seenSeatIds.get(i + 1)) {
        seatId = seenSeatIds.get(i) + 1;
        break;
      }
    }

    System.out.println("My seat ID was " + seatId);
  }

  public int findSeatId(String line) {
    String rowString = line.substring(0, 7);
    String colString = line.substring(7, 10);
    int row = findRow(rowString);
    int col = findCol(colString);
    int seatId = (row * 8) + col;

    return seatId;
  }

  public int findRow(String row) {
    int[] range = new int[]{0, 127};

    for (int i = 0; i < row.length(); i++) {
      char c = row.charAt(i);
      range = binarySearch(c == 'F' ? Direction.BOTTOM_LEFT : Direction.TOP_RIGHT, range);
    }

    return range[0];
  }

  public int findCol(String col) {
    int[] range = new int[]{0, 7};

    for (int i = 0; i < col.length(); i++) {
      char c = col.charAt(i);
      range = binarySearch(c == 'L' ? Direction.BOTTOM_LEFT : Direction.TOP_RIGHT, range);
    }

    return range[0];
  }

  public int[] binarySearch(Direction dir, int[] range) {
    int min = range[0];
    int max = range[1];

    if (dir == Direction.BOTTOM_LEFT) {
      return new int[]{min, ((max + min) / 2)};
    } else {
      return new int[]{((max + min) / 2) + 1, max};
    }
  }
}
