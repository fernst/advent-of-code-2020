package com.fernst.aoc.days.day1;

import com.fernst.aoc.days.Day;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Day1 extends Day {
  public Day1() {
    inputFile = "./src/com/fernst/aoc/days/day1/input0.txt";
  }

  final int globalTarget = 2020;

  public void part1() {
    String input = this.getInput();
    List<Integer> values = new LinkedList<>();
    for (String line : input.split("\n")) {
      int current = Integer.parseInt(line);
      values.add(current);
    }

    int[] result = findTarget(globalTarget, values);

    if (result != null) {
      System.out.println(String.format("Values are %d and %d and the multiplication is %d",
                                       result[0], result[1], result[0] * result[1]));
    } else {
      System.out.println("None found");
    }
  }

  public void part2() {
    String input = this.getInput();
    List<Integer> values = new LinkedList<>();
    for (String line : input.split("\n")) {
      int current = Integer.parseInt(line);
      values.add(current);
    }

    Iterator<Integer> iter = values.iterator();

    while (iter.hasNext()) {
      Integer currPivot = iter.next();
      iter.remove();

      int[] result = findTarget(globalTarget - currPivot, values);

      if (result == null) continue;

      System.out.println(String.format("Values are %d , %d and %d and the multiplication is %d",
              currPivot, result[0], result[1], currPivot * result[0] * result[1]));

    }
  }

  //O(n) runtime, O(n) memory
  public int[] findTarget(int target, List<Integer> values) {
    Set<Integer> seen = new HashSet<>();

    for (Integer current : values) {
      if (seen.contains(target - current)) {
        return new int[]{current, target - current};
      }

      seen.add(current);
    }

    return null;
  }
}