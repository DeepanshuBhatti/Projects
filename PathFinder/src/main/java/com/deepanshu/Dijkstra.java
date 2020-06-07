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
            int vertexId = nodePriorityQueue.remove().getVertexId();
            settledVertices.add(vertexId);
            processNeighbours(vertexId);
        }
    }

    public Map<Integer, BigDecimal> getDestinationIdToDistanceMap() {
        return destinationIdToDistanceMap;
    }

    private void processNeighbours(int vertexId) {
        for (Node node : vertexIdToNodeListMap.get(vertexId)) {
            int nodeVertexId = node.getVertexId();
            if (!settledVertices.contains(nodeVertexId)) {
                BigDecimal edgeDistance = node.getDistance();
                BigDecimal newDistance = vertexIdToDistanceMap.get(vertexId).add(edgeDistance);
                vertexIdToDistanceMap.putIfAbsent(nodeVertexId, BigDecimal.valueOf(Integer.MAX_VALUE));
                if (newDistance.compareTo(vertexIdToDistanceMap.get(nodeVertexId)) < 0) {
                    vertexIdToDistanceMap.put(nodeVertexId, newDistance);
                }
                nodePriorityQueue.add(new Node(nodeVertexId, vertexIdToDistanceMap.get(nodeVertexId)));
                destinationIdToDistanceMap.put(nodeVertexId, vertexIdToDistanceMap.get(nodeVertexId));
            }
        }
    }
}