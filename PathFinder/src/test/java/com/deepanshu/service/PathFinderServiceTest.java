package com.deepanshu.service;

import com.deepanshu.model.PathData;
import com.deepanshu.model.ReferenceData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

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

}