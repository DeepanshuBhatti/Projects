package com.deepanshu.dao;

import com.deepanshu.model.ReferenceData;
import com.deepanshu.model.SourceDestinationDistance;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Deepanshu Bhatti
 */
public class PathFinderDao {

    private static final String JDBC_URL = "jdbc:sqlite:src/main/resources/PathFinder.db";
    private Connection connection = null;
    private Statement statement = null;
    private Map<Integer, String> cityIdToNameMap;

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public PathFinderDao() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }

    public boolean closeSqlConnection() {
        try {
            if (connection != null) {
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            LOGGER.log(Level.SEVERE, e.getMessage());
            return false;
        }
        return true;
    }

    public Map<Integer, String> getCityIdToNameMap() {
        cityIdToNameMap = new HashMap<>();
        LOGGER.log(Level.INFO, System.getProperty("user.dir"));
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("select * from cities");
            while (resultSet.next()) {
                // read the result set
                String cityName = resultSet.getString("city_name");
                int cityId = resultSet.getInt("city_id");

                cityIdToNameMap.putIfAbsent(cityId, cityName);
            }
        } catch (SQLException sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.getMessage());
            this.closeSqlConnection();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }

        return cityIdToNameMap;
    }

    public List<SourceDestinationDistance> getSourceDestinationDistanceList() {
        List<SourceDestinationDistance> sourceDestinationDistanceList = new ArrayList<>();
        if (cityIdToNameMap.size() < 1) {
            cityIdToNameMap = getCityIdToNameMap();
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("select * from distance");
            while (resultSet.next()) {
                // read the result set
                int sourceId = resultSet.getInt("source_id");
                int destinationId = resultSet.getInt("destination_id");
                BigDecimal distance = BigDecimal.valueOf(resultSet.getInt("distance"));
                String sourceName = cityIdToNameMap.getOrDefault(sourceId, null);
                String destinationName = cityIdToNameMap.getOrDefault(destinationId, null);
                sourceDestinationDistanceList.add(
                        new SourceDestinationDistance(
                                new ReferenceData(sourceId, sourceName, null),
                                new ReferenceData(destinationId, destinationName, null),
                                distance
                        )
                );
            }
        } catch (SQLException sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.getMessage());
            this.closeSqlConnection();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
        return sourceDestinationDistanceList;
    }

}
