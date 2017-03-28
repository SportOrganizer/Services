/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.test.dal;

import com.so.dal.core.model.Resource;
import com.so.dal.core.model.Team;
import com.so.dal.core.repository.ResourceRepository;
import com.so.dal.core.repository.TeamRepository;
import com.so.dal.core.repository.customDto.PathsToLogos;
import com.so.dal.floorball.repository.game.SportFlorbalGameShotsRepository;
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

    @Autowired
    private SportFlorbalGameShotsRepository florbalRepo;

    @Test
    public void numberOfTeamsTest() {
        assertTrue(true);
    }

 

    @Test
    public void mojTest() {

        int size = florbalRepo.findAll().size();

        assertTrue(size == 0);
    }
}
