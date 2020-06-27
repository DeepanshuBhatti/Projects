package com.deepanshu.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.deepanshu.Dijkstra;
import com.deepanshu.dao.PathFinderDao;
import com.deepanshu.model.Node;
import com.deepanshu.model.ReferenceData;
import com.deepanshu.model.SourceDestinationDistance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author Deepanshu Bhatti
 */
@RestController
public class PathFinderService {

    private final PathFinderDao pathFinderDao;

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public PathFinderService() {
        pathFinderDao = new PathFinderDao();
    }

    @GetMapping("/PathFinderService/getAllCityInfo")
    public List<ReferenceData> getAllCityInfo() {
        Map<Integer, String> cityIdToNameMap = pathFinderDao.getCityIdToNameMap();
        List<ReferenceData> referenceDataList = new ArrayList<>();
        for (Map.Entry<Integer, String> city : cityIdToNameMap.entrySet()) {
            referenceDataList.add(
                    new ReferenceData(city.getKey(), city.getValue(), null)
            );
        }
        return referenceDataList;
    }

    @GetMapping("/PathFinderService/getShortestDistance")
    public BigDecimal getShortestDistance(
            @RequestParam(value = "sourceName") String sourceName,
            @RequestParam(value = "destinationName") String destinationName
    ) {

        LOGGER.info("Got input parameters " + sourceName +" and " +  destinationName);
        Map<Integer, String> cityIdToNameMap = pathFinderDao.getCityIdToNameMap();
        int sourceId = -1;
        int destinationId = -1;

        for (Map.Entry<Integer, String> city : cityIdToNameMap.entrySet()) {
            if (sourceName.equalsIgnoreCase(city.getValue())) {
                sourceId = city.getKey();
            }
            if (destinationName.equalsIgnoreCase(city.getValue())) {
                destinationId = city.getKey();
            }
        }
        if (sourceId == -1) {
            throw new IllegalArgumentException("Invalid Source Name");
        }
        if (destinationId == -1) {
            throw new IllegalArgumentException("Invalid Destination Name");
        }

        List<SourceDestinationDistance> sourceDestinationDistanceList =
                pathFinderDao.getSourceDestinationDistanceList();

        Map<Integer, List<Node>> distanceNodes = new HashMap<>();
        for (SourceDestinationDistance sourceDestinationDistance : sourceDestinationDistanceList) {
            int currentSourceId = sourceDestinationDistance.getSource().getId();
            int currentDestinationId = sourceDestinationDistance.getDestination().getId();
            BigDecimal distance = sourceDestinationDistance.getDistance();
            distanceNodes.putIfAbsent(currentSourceId, new ArrayList<>());
            distanceNodes.putIfAbsent(currentDestinationId, new ArrayList<>());
            distanceNodes.get(currentSourceId).add(new Node(currentDestinationId, distance));
            distanceNodes.get(currentDestinationId).add(new Node(currentSourceId, distance));
        }

        // Calculate the single source shortest path
        Dijkstra dpq = new Dijkstra();
        dpq.calculateFromSource(distanceNodes, sourceId);

        return dpq.getDestinationIdToDistanceMap().get(destinationId);

    }
}
