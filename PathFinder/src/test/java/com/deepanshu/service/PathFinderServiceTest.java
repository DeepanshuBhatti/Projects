package com.deepanshu.service;

import com.deepanshu.model.PathData;
import com.deepanshu.model.ReferenceData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

/*
 * @author Deepanshu Bhatti
 */
@RunWith(MockitoJUnitRunner.class)
public class PathFinderServiceTest {

    @InjectMocks
    private PathFinderService pathFinderService;

    @Test
    public void testGetAllCityInfo() {
        List<ReferenceData> referenceDataList = pathFinderService.getAllCityInfo();

        Assert.assertNotNull(referenceDataList);
        Assert.assertEquals(4, referenceDataList.size());

    }

    @Test
    public void testGetShortestDistance() {
        BigDecimal distance  = pathFinderService.getShortestDistance("delhi", "mumbai");
        Assert.assertEquals(BigDecimal.valueOf(1428), distance);
        distance  = pathFinderService.getShortestDistance("mumbai", "delhi");
        Assert.assertEquals(BigDecimal.valueOf(1428), distance);
        distance  = pathFinderService.getShortestDistance("jaipur", "delhi");
        Assert.assertEquals(BigDecimal.valueOf(281), distance);
    }

}