package com.so.test.init;


import com.so.configuration.AppConfig;
import com.so.configuration.AppConfigDal;
import javax.transaction.Transactional;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author peter
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={AppConfigDal.class, AppConfig.class} , loader = AnnotationConfigContextLoader.class)
public abstract class TestParent {
    
}
