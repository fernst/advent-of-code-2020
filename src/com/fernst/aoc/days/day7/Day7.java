package com.fernst.aoc.days.day7;

import com.fernst.aoc.days.Day;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Day7 extends Day {

  @Override
  public String getInputFileName() {
    return "./src/com/fernst/aoc/days/day7/input0.txt";
  }

  @Override
  public void part1() {
    Graph graph = new Graph();

    for (String line : getInputLines()) {
      parseLine(graph, line);
    }

    Node shinyGold = graph.get("shiny gold");

    System.out.printf("Total number of containers is %d.\n", visitContainers(shinyGold));
  }

  @Override
  public void part2() {
    Graph graph = new Graph();

    for (String line : getInputLines()) {
      parseLine(graph, line);
    }

    Node shinyGold = graph.get("shiny gold");
    Map<Node, Integer> memo = new HashMap<>();

    // We substract one because we don't need to count the shiny gold bag itself.
    System.out.printf("Total number of containers is %d.\n", visitContents(shinyGold, memo) - 1);

  }

  /**
   * BFS Visit all container bags.
   *
   * @param initial The initial node
   * @return the number of bags that contain the initial bag.
   */
  public int visitContainers(Node initial) {
    Set<Node> containers = new HashSet<>();
    Deque<Node> toVisit = new LinkedList<>();

    toVisit.addAll(initial.getContainers());
    containers.addAll(initial.getContainers());

    while (toVisit.size() > 0) {
      Node current = toVisit.removeFirst();

      for (Node container : current.getContainers()) {
        if (containers.contains(container)) continue;

        toVisit.add(container);
        containers.add(container);
      }
    }

    return containers.size();
  }

  /**
   * Top-down DP with memoization approach to count all bags within a bag.
   *
   * @param current the note that we need to count
   * @param memo    Map used to memoize previously solved subproblems
   * @return the number of bags contained within this bag.
   */
  public int visitContents(Node current, Map<Node, Integer> memo) {
    if (memo.containsKey(current)) return memo.get(current);

    int numBags = 1;

    for (Map.Entry<Node, Integer> content : current.getContents().entrySet()) {
      numBags += content.getValue() * visitContents(content.getKey(), memo);
    }

    memo.put(current, numBags);

    return numBags;
  }

  public void parseLine(Graph graph, String line) {
    String[] splitString = line.split(" bags contain ");
    String container = splitString[0].trim();
    String contentString = splitString[1];
    String[] contentParts = contentString.split(",");

    for (var contentPart : contentParts) {
      contentPart = contentPart.replaceAll("bag[s]?", "").replace(".", "").trim();
      String[] amountAndColor = contentPart.split(" ", 2);

      //Skip if there are no contents for this bag.
      if ("no".equals(amountAndColor[0])) continue;

      int amount = Integer.parseInt(amountAndColor[0]);
      String content = amountAndColor[1];

      graph.addRelationship(container, content, amount);
    }
  }
}
