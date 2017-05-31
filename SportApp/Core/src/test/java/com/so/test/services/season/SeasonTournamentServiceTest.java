package com.so.test.services.season;

import com.so.core.exception.AppException;
import com.so.core.services.season.SeasonTournamentFlow;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import com.so.test.init.TestParent;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by janpolacek on 1/23/17.
 */
public class SeasonTournamentServiceTest extends TestParent {

//    @Autowired
//    SeasonTournamentSettingsService service;

    @Autowired
    private SeasonTournamentFlow flowService;
    
    @Autowired
    private SeasonTournamentRepository repo;
   
    @Test
    public void flowServiceTest() throws AppException {

        
        flowService.makeMatches(51);
        SeasonTournament st = repo.findOne(51);
        assertTrue(true);
       
    }

 
}
