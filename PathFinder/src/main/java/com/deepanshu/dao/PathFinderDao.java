package com.deepanshu.dao;

import com.deepanshu.model.ReferenceData;
import com.deepanshu.model.SourceDestinationDistance;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Deepanshu Bhatti
 */
public class PathFinderDao {

    private JSONObject tableJson;
    private Map<Integer, String> cityIdToNameMap;
    private List<SourceDestinationDistance> sourceDestinationDistanceList;

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public PathFinderDao() {
        try {
            File file = new File(
                    Objects.requireNonNull(getClass().getClassLoader().getResource("data.json")).getFile()
            );
            String content = Files.readString(Paths.get(file.getPath()));
            JSONObject jsonObject = new JSONObject(content.trim());
            tableJson = (JSONObject) jsonObject.get("tables");
            populateCityIdToNameMap();
            populateSourceDestinationDistanceList();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }

    public Map<Integer, String> getCityIdToNameMap() {
        return cityIdToNameMap;
    }

    public List<SourceDestinationDistance> getSourceDestinationDistanceList() {
        return sourceDestinationDistanceList;
    }

    private void populateCityIdToNameMap() {
        JSONArray citiesJson = (JSONArray) tableJson.get("cities");
        cityIdToNameMap = new HashMap<>();
        if (citiesJson != null) {
            for (int i = 0; i < citiesJson.length(); i++) {
                int cityId = citiesJson.getJSONObject(i).getInt("id");
                String cityName = citiesJson.getJSONObject(i).getString("name");
                cityIdToNameMap.putIfAbsent(cityId, cityName);
            }
        }
    }

    private void populateSourceDestinationDistanceList() {
        JSONArray distanceJson = (JSONArray) tableJson.get("distance");
        sourceDestinationDistanceList = new ArrayList<>();
        if (distanceJson != null) {
            for (int i = 0; i < distanceJson.length(); i++) {
                int sourceId = distanceJson.getJSONObject(i).getInt("source");
                String sourceName = cityIdToNameMap.getOrDefault(sourceId, null);
                int destinationId = distanceJson.getJSONObject(i).getInt("destination");
                String destinationName = cityIdToNameMap.getOrDefault(destinationId, null);
                int distance = distanceJson.getJSONObject(i).getInt("distance");

                sourceDestinationDistanceList.add(
                        new SourceDestinationDistance(
                                new ReferenceData(sourceId, sourceName, null),
                                new ReferenceData(destinationId, destinationName, null),
                                BigDecimal.valueOf(distance)
                        )
                );
            }
        }
    }

}
