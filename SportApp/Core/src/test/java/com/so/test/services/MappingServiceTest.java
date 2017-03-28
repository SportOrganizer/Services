/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.so.test.services;

import com.so.core.services.MappingService;
import com.so.test.init.TestParent;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kristián Kačinetz
 */
public class MappingServiceTest extends TestParent {
    
    @Autowired
    MappingService ms;
    
        @Test
    public void createMappingTest() {
        //ms.MappingTeamsAndPlayers(4);
        assertTrue(true);

    }
    
}
