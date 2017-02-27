package com.so.test.services;

import com.so.dal.model.Tournament;
import com.so.dal.model.season.Season;
import com.so.services.TournamentService;
import com.so.services.season.SeasonService;
import com.so.test.init.TestParent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by janpolacek on 1/23/17.
 */
public class TournamentServiceTest extends TestParent {
    @Autowired
    TournamentService service;

    @Test
    public void createTournamentnTest(){
        Boolean b=  service.createTournament("Test");
        assertTrue(b);
    }

    @Test
    public void findByNameTest(){
        service.createTournament("Test");
        Tournament s = service.findByName("Test");
        assertNotNull(s);
    }
}
