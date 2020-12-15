package com.fernst.aoc.days.day7;

import java.util.HashMap;
import java.util.Map;

public class Graph {
  Map<String, Node> nodesByColor;

  public Graph() {
    this.nodesByColor = new HashMap<>();
  }

  public Node getOrCreate(String color) {
    return nodesByColor.computeIfAbsent(color, Node::new);
  }

  public Node get(String color) {
    return nodesByColor.get(color);
  }

  public void addRelationship(String container, String content, int amount) {
    Node containerNode = getOrCreate(container);
    Node contentNode = getOrCreate(content);
    containerNode.addContent(contentNode, amount);
    contentNode.addContainer(containerNode);
  }
}
