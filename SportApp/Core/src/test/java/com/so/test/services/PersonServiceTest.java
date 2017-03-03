/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.test.services;

import com.so.dal.core.model.Person;
import com.so.core.services.PersonService;
import com.so.test.init.TestParent;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author peter
 */
public class PersonServiceTest extends TestParent{
    
    @Autowired
    PersonService service;
    
    @Test
    public void addPersonTest(){
      Boolean b=  service.addPerson("peter", "ondovcik", null, null, null, true, "MALE", null);
         assertTrue(b);
         
     }
    
    @Test
    public void findByNameAndSurnameTest(){
        service.addPerson("peter", "ondovcik", null, null, null, true, "MALE", null);
        
        Person p = service.findPersonByNameAndSurname("peter", "ondovcik");
        
        assertTrue(p.getName().equals("peter"));
    }
    
}
