package com.fernst.aoc.days.day4;

import com.fernst.aoc.days.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day4 extends Day {

  public Day4() {
    inputFile = "./src/com/fernst/aoc/days/day4/input0.txt";
  }

  @Override
  public void part1() {
    String input = this.getInput();

    Map<String, String> passport = initPassport();

    int validPassports = 0;

    for (String line : input.split("\n")) {
      if ("".equals(line.trim())) {
        if (isValid1(passport)) validPassports++;
        passport = initPassport();
      } else {
        processLine(passport, line);
      }
    }

    System.out.println("Found " + validPassports + " valid passports");
  }

  @Override
  public void part2() {

    String input = this.getInput();

    Map<String, String> passport = initPassport();

    int validPassports = 0;

    for (String line : input.split("\n")) {
      if ("".equals(line.trim())) {
        if (isValid2(passport)) validPassports++;
        passport = initPassport();
      } else {
        processLine(passport, line);
      }
    }

    System.out.println("Found " + validPassports + " valid passports");
  }

  public Map<String, String> initPassport() {
    return new HashMap<>();
  }

  public Map<String, String> processLine(Map<String, String> passport, String line) {
    String[] parts = line.split(" ");

    for (String part : parts) {
      String[] keyval = part.split(":");
      passport.put(keyval[0], keyval[1]);
    }

    return passport;
  }

  public boolean isValid1(Map<String, String> passport) {
    return passport.containsKey("byr")
      && passport.containsKey("iyr")
      && passport.containsKey("eyr")
      && passport.containsKey("hgt")
      && passport.containsKey("hcl")
      && passport.containsKey("ecl")
      && passport.containsKey("pid");
  }

  public boolean isValid2(Map<String, String> passport) {
    return passport.containsKey("byr") && isNumber(passport.get("byr"), 4) && isNumberInRange(passport.get("byr"), 1920, 2002)
      && passport.containsKey("iyr") && isNumber(passport.get("iyr"), 4) && isNumberInRange(passport.get("iyr"), 2010, 2020)
      && passport.containsKey("eyr") && isNumber(passport.get("eyr"), 4) && isNumberInRange(passport.get("eyr"), 2020, 2030)
      && passport.containsKey("hgt") && isHeight(passport.get("hgt"))
      && passport.containsKey("hcl") && isHairColor(passport.get("hcl"))
      && passport.containsKey("ecl") && isEyeColor(passport.get("ecl"))
      && passport.containsKey("pid") && isNumber(passport.get("pid"), 9);
  }

  public boolean isNumber(String val, int length) {
    String expression = String.format("^[0-9]{%d}$", length);
    return val.matches(expression);
  }

  public boolean isNumberInRange(String val, int min, int max) {
    int num = Integer.parseInt(val);
    return num >= min && num <= max;
  }

  public boolean isHeight(String val) {
    if (val.matches("^[0-9]+cm$")) return isValidHeightCentimeters(val);
    if (val.matches("^[0-9]+in$")) return isValidHeightInches(val);

    return false;
  }

  public boolean isValidHeightCentimeters(String val) {
    return isNumberInRange(val.replace("cm", ""), 150, 193);
  }

  public boolean isValidHeightInches(String val) {
    return isNumberInRange(val.replace("in", ""), 59, 76);
  }

  public boolean isHairColor(String val) {
    return val.matches("^#[0-9a-f]{6}$");
  }

  static final Set<String> colors = new HashSet(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));

  public boolean isEyeColor(String val) {
    return colors.contains(val);
  }
}
