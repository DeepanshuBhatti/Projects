package com.deepanshu;

import java.math.BigDecimal;
import java.util.*;

import com.deepanshu.model.Node;

/*
 * @author Deepanshu Bhatti
 */
public class Dijkstra {
    private final Map<Integer, BigDecimal> vertexIdToDistanceMap;
    private final Set<Integer> settledVertices;
    private final PriorityQueue<Node> nodePriorityQueue;
    private final int numberOfVertices;
    private Map<Integer, List<Node>> vertexIdToNodeListMap;
    private final Map<Integer, BigDecimal> destinationIdToDistanceMap;

    public Dijkstra(int numberOfVertices) {
        this.destinationIdToDistanceMap = new HashMap<>();
        this.numberOfVertices = numberOfVertices;
        vertexIdToDistanceMap = new HashMap<>();
        settledVertices = new HashSet<>();
        nodePriorityQueue = new PriorityQueue<>(numberOfVertices, new Node());
    }

    public void calculateFromSource(Map<Integer, List<Node>> adj, int src) {
        this.vertexIdToNodeListMap = adj;
        nodePriorityQueue.add(new Node(src, BigDecimal.ZERO));
        vertexIdToDistanceMap.putIfAbsent(src, BigDecimal.ZERO);
        while (settledVertices.size() != numberOfVertices) {
            int vertexId = nodePriorityQueue.remove().vertexId;
            settledVertices.add(vertexId);
            processNeighbours(vertexId);
        }
    }

    public Map<Integer, BigDecimal> getDestinationIdToDistanceMap() {
        return destinationIdToDistanceMap;
    }

    private void processNeighbours(int vertexId) {
        for (Node node : vertexIdToNodeListMap.get(vertexId)) {
            if (!settledVertices.contains(node.vertexId)) {
                BigDecimal edgeDistance = node.cost;
                BigDecimal newDistance = vertexIdToDistanceMap.get(vertexId).add(edgeDistance);
                vertexIdToDistanceMap.putIfAbsent(node.vertexId, BigDecimal.valueOf(Integer.MAX_VALUE));
                if (newDistance.compareTo(vertexIdToDistanceMap.get(node.vertexId)) < 0) {
                    vertexIdToDistanceMap.put(node.vertexId, newDistance);
                }
                nodePriorityQueue.add(new Node(node.vertexId, vertexIdToDistanceMap.get(node.vertexId)));
                destinationIdToDistanceMap.put(node.vertexId, vertexIdToDistanceMap.get(node.vertexId));
            }
        }
    }
}