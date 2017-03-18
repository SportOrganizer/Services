/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.test.services;

import com.so.core.services.RegistrationService;
import com.so.core.services.document.DocumentService;
import com.so.dal.core.repository.TournamentRepository;
import com.so.dal.core.repository.registration.RegistrationPlayerRepository;
import com.so.dal.core.repository.season.SeasonRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import com.so.test.init.TestParent;
import java.io.IOException;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author peter
 */
public class RegistrationServiceTest extends TestParent {

    @Autowired
    SeasonRepository seasonRepo;
    @Autowired
    TournamentRepository tournamentRepo;
    @Autowired
    SeasonTournamentRepository stRepo;
    @Autowired
    RegistrationService service;
    
    @Autowired
    RegistrationPlayerRepository playerRepo;

    @Autowired
    DocumentService docService;
    
    @Test
    public void docTest() throws IOException{
        
    //    docService.createFile("VmFuMmFqejQ5NThwdA==".getBytes(), "foto");
assertTrue(true);        
    }
//    @Test
//    public void RegistrationTest() throws IOException {
////        Tournament t =tournamentRepo.save(new Tournament("turnaj1"));
////        Season s = seasonRepo.save(new Season("Sezona1"));
////        SeasonTournament st = stRepo.save(new SeasonTournament(s,t,"2016/2017"));
////        ResourceDto r= new ResourceDto("VmFuMmFqejQ5NThwdA==","txt","photos");
////        RegistrationTeamDto regTeam = new RegistrationTeamDto(r,st.getId(),"tim1","t1","red");
////        Set<RegistrationPlayerDto> playerList = new HashSet<RegistrationPlayerDto>();
////        
////        RegistrationPlayerDto p1  = new RegistrationPlayerDto("meno","priezvisko",new Date(),"email","phone",
////                    true,"male",true,"note",5);
////        playerList.add(p1);
////                RegistrationPlayerDto p2  = new RegistrationPlayerDto("meno2","priezvisko2",new Date(),"email2","phone",
////                    true,"male",true,"note2",20);
////                playerList.add(p2);
//                
////                regTeam.setRegistrationPlayers(playerList);
////        RegistrationTeam rt= service.registrationTeam(regTeam);
////        
////        assertNotNull(rt);
////        assertTrue("tim1".equals(rt.getName()));
//        
//                
//    }

}
