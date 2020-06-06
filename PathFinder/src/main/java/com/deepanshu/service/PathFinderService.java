package com.deepanshu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.deepanshu.dao.PathFinderDao;
import com.deepanshu.model.ReferenceData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author Deepanshu Bhatti
 */
@RestController
public class PathFinderService {
    private PathFinderDao pathFinderDao;

    @GetMapping("/getAllCityInfo")
    public List<ReferenceData> getAllCityInfo() {
        pathFinderDao = new PathFinderDao();
        Map<Integer, String> cityIdToNameMap = pathFinderDao.getCityIdToNameMap();
        List<ReferenceData> referenceDataList = new ArrayList<>();
        for (Map.Entry<Integer, String> city : cityIdToNameMap.entrySet()) {
            referenceDataList.add(
                    new ReferenceData(city.getKey(), city.getValue(), null)
            );
        }
        return referenceDataList;
    }
}
