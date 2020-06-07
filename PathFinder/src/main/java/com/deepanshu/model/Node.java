package com.deepanshu.model;

import java.math.BigDecimal;
import java.util.Comparator;

/*
 * @author Deepanshu Bhatti
 */
public class Node implements Comparator<Node> {
    private int vertexId;
    private BigDecimal distance;

    public Node() {
    }

    public Node(int vertexId, BigDecimal distance) {
        this.vertexId = vertexId;
        this.distance = distance;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return node1.distance.compareTo(node2.distance);
    }

    public int getVertexId() {
        return vertexId;
    }

    public BigDecimal getDistance() {
        return distance;
    }

}