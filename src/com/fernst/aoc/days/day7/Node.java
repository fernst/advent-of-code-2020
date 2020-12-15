package com.fernst.aoc.days.day7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Node {
  Set<Node> containers;
  String color;
  Map<Node, Integer> contents;

  public Node(String color) {
    this.color = color;
    this.containers = new HashSet<>();
    this.contents = new HashMap<>();
  }

  public void addContainer(Node container) {
    this.containers.add(container);
  }

  public void addContent(Node content, int amount) {
    this.contents.put(content, amount);
  }

  public Set<Node> getContainers() {
    return containers;
  }

  public String getColor() {
    return color;
  }

  public Map<Node, Integer> getContents() {
    return contents;
  }

  public Integer getAmount(Node content) {
    return this.contents.get(content);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Node node = (Node) o;
    return color.equals(node.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color);
  }
}
