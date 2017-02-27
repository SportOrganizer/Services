/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.test.dal;

import com.so.configuration.AppConfig;
import com.so.configuration.AppConfigDal;
import com.so.floorball.configuration.FloorballAppConfig;
import com.so.floorball.configuration.SportAppConfigDal;
import com.so.floorball.dal.repository.game.SportFlorbalGameShotsRepository;
import javax.transaction.Transactional;
import static org.junit.Assert.assertTrue;
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
@ContextConfiguration(classes ={AppConfigDal.class, AppConfig.class,SportAppConfigDal.class,FloorballAppConfig.class} , loader = AnnotationConfigContextLoader.class)
public class FloorballDalTest {
    
    
    private SportFlorbalGameShotsRepository florbalRepo;
    
      @Test
   public void getPathsToLogsTest(){
       
       int size = florbalRepo.findAll().size();

     assertTrue(size==0);
    }
}
