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
    private ResultSet resultSet = null;
    private Map<Integer, String> cityIdToNameMap;

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public PathFinderDao() {
        if (Boolean.TRUE.equals(establishSqlConnection())) {
            LOGGER.log(Level.INFO, "SQL connection established with Sqlite3 database file");
        } else {
            LOGGER.log(Level.SEVERE, "SQL connection failed to connect with Sqlite3 database file");
        }
    }

    public Map<Integer, String> getCityIdToNameMap() {
        establishSqlConnection();
        cityIdToNameMap = new HashMap<>();
        try {
            resultSet = statement.executeQuery("select * from cities");
            while (resultSet.next()) {
                String cityName = resultSet.getString("city_name");
                int cityId = resultSet.getInt("city_id");

                cityIdToNameMap.putIfAbsent(cityId, cityName);
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.getMessage());
            this.closeSqlConnection();
        }
        this.closeSqlConnection();
        return cityIdToNameMap;
    }

    public List<SourceDestinationDistance> getSourceDestinationDistanceList() {
        List<SourceDestinationDistance> sourceDestinationDistanceList = new ArrayList<>();
        if (cityIdToNameMap != null && cityIdToNameMap.size() < 1) {
            cityIdToNameMap = getCityIdToNameMap();
        }
        this.establishSqlConnection();
        try {
            resultSet = statement.executeQuery("select * from distance");
            while (resultSet.next()) {
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
            resultSet.close();
        } catch (SQLException sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.getMessage());
            this.closeSqlConnection();
        }
        this.closeSqlConnection();
        return sourceDestinationDistanceList;
    }

    private Boolean establishSqlConnection() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            return false;
        }
        return true;
    }

    private Boolean closeSqlConnection() {
        try {
            if (connection != null) {
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return false;
        }
        return true;
    }

}
