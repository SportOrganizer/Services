package com.so.test.services;

import com.so.dal.core.model.Tournament;
import com.so.core.services.TournamentService;
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
