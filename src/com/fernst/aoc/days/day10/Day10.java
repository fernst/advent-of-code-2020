package com.fernst.aoc.days.day10;

import com.fernst.aoc.days.Day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 extends Day {

  @Override
  public String getInputFileName() {
    return "./src/com/fernst/aoc/days/day10/input0.txt";
  }

  public List<Integer> getJoltages() {
    List<Integer> joltages = new ArrayList<>();

    //Add base case
    joltages.add(0);

    for (String line : getInputLines()) {
      joltages.add(Integer.valueOf(line));
    }

    //Sort joltages
    Collections.sort(joltages);

    //Add a final element with the desired joltage of my device, which is the last element of my list + 3.
    int desiredJoltage = joltages.get(joltages.size() - 1) + 3;
    joltages.add(desiredJoltage);

    return joltages;
  }

  @Override
  public void part1() {
    List<Integer> joltages = getJoltages();

    int diff1 = 0;
    int diff3 = 0;

    for (int i = 1; i < joltages.size(); i++) {
      int prev = joltages.get(i - 1);
      int curr = joltages.get(i);

      if (prev == curr - 1) diff1++;
      if (prev == curr - 3) diff3++;
    }

    System.out.println("Result is " + (diff1 * diff3));
  }

  @Override
  public void part2() {
    List<Integer> joltages = getJoltages();

    //Set up Memo map.
    Map<Integer, Long> memo = new HashMap<>();
    // Add base case to memoization: From the last element in the array (my phone's joltage),
    // the number of paths is always 1
    memo.put(joltages.get(joltages.size() - 1), 1L);

    long result = bt(joltages, memo, 0);

    System.out.println("Number of paths is is " + result);
  }

  public long bt(List<Integer> elems, Map<Integer, Long> memo, int startPos) {
    int curr = elems.get(startPos);

    //Return from memo if available.
    if (memo.containsKey(curr)) {
      return memo.get(curr);
    }

    long numPaths = 0;

    for (int i = startPos + 1; i < elems.size(); i++) {
      if (elems.get(i) <= curr + 3) {
        numPaths += bt(elems, memo, i);
      }

      if (elems.get(i) > curr + 3) break; // Nothing to do here
    }

    memo.put(curr, numPaths);

    return numPaths;
  }
}
