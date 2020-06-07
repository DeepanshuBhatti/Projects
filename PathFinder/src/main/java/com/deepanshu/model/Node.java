package com.deepanshu.model;

import java.math.BigDecimal;
import java.util.Comparator;

public class Node implements Comparator<Node> {
    public int vertexId;
    public BigDecimal cost;

    public Node() {
    }

    public Node(int vertexId, BigDecimal cost) {
        this.vertexId = vertexId;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return node1.cost.compareTo(node2.cost);
    }
}