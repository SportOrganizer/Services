/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.test.dal;


import com.so.dal.repository.TeamRepository;
import com.so.test.init.TestParent;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author peter
 */

public class DalRepositoryTest extends TestParent {

    
    @Autowired
    TeamRepository repo;
        @Test
    public void numberOfTeamsTest() {

        assertTrue(repo.findAll().isEmpty());

    }
    
}
