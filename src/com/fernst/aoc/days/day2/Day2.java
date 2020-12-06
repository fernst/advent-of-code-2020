package com.fernst.aoc.days.day2;

import com.fernst.aoc.days.Day;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day2 extends Day {

  @Override
  public String getInputFileName() {
    return "./src/com/fernst/aoc/days/day2/input0.txt";
  }

  @Override
  public void part1() {
    String input = this.getInput();
    List<Integer> values = new LinkedList<>();

    int valid = 0;

    for (String line : input.split("\n")) {
      line = line.replace("-", " ");
      line = line.replace(":", "");
      Scanner scanner = new Scanner(line);
      int min = scanner.nextInt();
      int max = scanner.nextInt();
      String desired = scanner.next();
      String word = scanner.next();

      if (validate1(min, max, desired, word)) valid++;
    }

    System.out.println(String.format("There are %d valid records",
                                     valid));
  }

  @Override
  public void part2() {
    String input = this.getInput();
    List<Integer> values = new LinkedList<>();

    int valid = 0;

    for (String line : input.split("\n")) {
      //Cleaning up input
      line = line.replace("-", " ");
      line = line.replace(":", "");

      //Find desired elements
      Scanner scanner = new Scanner(line);
      int min = scanner.nextInt();
      int max = scanner.nextInt();
      String desired = scanner.next();
      String word = scanner.next();

      if (validate2(min, max, desired, word)) valid++;
    }

    System.out.printf("There are %d valid records%n",
            valid);
  }

  public boolean validate1(int min, int max, String needle, String haystack) {
    char c = needle.charAt(0);

    int count = 0;

    for (char h : haystack.toCharArray()) {
      if (c == h) count++;
    }

    return count >= min && count <= max;
  }

  public boolean validate2(int lo, int hi, String needle, String haystack) {
    lo--;
    hi--;

    char c = needle.charAt(0);

    int count = 0;

    char[] chars = haystack.toCharArray();

    if (lo >= chars.length || hi >= chars.length) return false;

    int matches = 0;
    if (lo < chars.length && chars[lo] == c) matches++;
    if (hi < chars.length && chars[hi] == c) matches++;

    return matches == 1;
  }
}