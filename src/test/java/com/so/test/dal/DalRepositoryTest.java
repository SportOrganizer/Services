/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.test.dal;

import com.so.dal.model.Resource;
import com.so.dal.model.Team;
import com.so.dal.repository.ResourceRepository;
import com.so.dal.repository.TeamRepository;
import com.so.dal.repository.customDto.PathsToLogos;
import com.so.test.init.TestParent;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author peter
 */
public class DalRepositoryTest extends TestParent {

    @Autowired
    TeamRepository teamRepo;
    
    @Autowired
    ResourceRepository resourceRepo;

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
}
