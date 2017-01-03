/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal;


import com.so.configuration.AppConfigDal;
import com.so.dal.repository.TeamRepository;
import javax.transaction.Transactional;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author peter
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =AppConfigDal.class , loader = AnnotationConfigContextLoader.class)
public class DalRepositoryTest {

    TeamRepository repo;
        @Test
    public void numberOfTeamsTest() {

        assertTrue(repo.findAll().isEmpty());

    }
    
}
