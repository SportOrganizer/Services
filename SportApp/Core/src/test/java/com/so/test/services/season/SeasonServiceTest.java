package com.so.test.services.season;

import com.so.core.exception.AppException;
import com.so.dal.core.model.season.Season;
import com.so.core.services.season.SeasonService;
import com.so.test.init.TestParent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by janpolacek on 1/23/17.
 */
public class SeasonServiceTest extends TestParent {
    @Autowired
    SeasonService service;

//    @Test
//    public void createSeasonTest(){
//        Boolean b=  service.createSeason("Test");
//        assertTrue(b);
//    }

    @Test
    public void findByNameTest() throws AppException{
        service.createSeason("Test");
        Season s = service.findByName("Test");
        assertNotNull(s);
    }
}
