/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.test.dal;

import com.so.dal.configuration.AppConfigDal;
import com.so.dal.core.model.Resource;
import com.so.dal.core.model.Team;
import com.so.dal.core.repository.ResourceRepository;
import com.so.dal.core.repository.TeamRepository;
import com.so.dal.core.repository.customDto.PathsToLogos;
import com.so.dal.floorball.repository.game.SportFlorbalGameShotsRepository;
import java.util.List;
import javax.transaction.Transactional;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author peter
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={AppConfigDal.class} , loader = AnnotationConfigContextLoader.class)

public class DalTest {
    
     @Autowired
    TeamRepository teamRepo;
    
    @Autowired
    ResourceRepository resourceRepo;
    
        @Autowired
    private SportFlorbalGameShotsRepository florbalRepo;

    @Test
    public void numberOfTeamsTest() {
        assertTrue(true);
    }

    @Test
   public void getPathsToLogsTest(){
     Resource r1 = resourceRepo.saveAndFlush(new Resource("cesta1"));
     Resource r2 = resourceRepo.saveAndFlush(new Resource("cesta2"));
     Resource r3 = resourceRepo.saveAndFlush(new Resource("cesta3"));
     Resource r4 = resourceRepo.saveAndFlush(new Resource("cesta4"));
     
     teamRepo.saveAndFlush(new Team(r1,"prvy tim","pt","r"));
     teamRepo.saveAndFlush(new Team(r2,"druhy tim","dt","r"));
     teamRepo.saveAndFlush(new Team(r3,"treti tim","tt","r"));
     teamRepo.saveAndFlush(new Team(r4,"stvrty tim","st","r"));
     
     List<PathsToLogos> l1 = teamRepo.getPathsToLogos();
     assertTrue(l1.get(0).getPath().equals(r1.getPath()));
    }
    
   

    
      @Test
   public void mojTest(){
       
       int size = florbalRepo.findAll().size();

     assertTrue(size==0);
    }
}
