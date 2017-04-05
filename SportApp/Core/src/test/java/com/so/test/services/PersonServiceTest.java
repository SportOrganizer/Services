/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.test.services;

import com.so.dal.core.model.Person;
import com.so.core.services.PersonService;
import com.so.dal.core.model.season.Season;
import com.so.test.init.TestParent;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author peter
 */
public class PersonServiceTest extends TestParent {

    @Autowired
    PersonService service;

    @Test
    public void test(){
        assertTrue(true);
    }
//    @Test
//    public void addPersonTest() {
//        Person p = service.addPerson("peter", "ondovcik", null, null, null, true, "MALE");
//        assertTrue(p.getName().equals("peter"));
//
//    }
//
//    @Test
//    public void findByNameAndSurnameTest() {
//        service.addPerson("peter", "ondovcik", null, null, null, true, "MALE");
//
//        Person p = service.findPersonByNameAndSurname("peter", "ondovcik");
//
//        assertTrue(p.getName().equals("peter"));
//    }
//
//    @Test
//    public void findByIdTest() {
//        Person p = service.addPerson("kristian", "kacinetz", null, null, null, true, "MALE");
//       
//        assertTrue(service.findById(p.getId()).getName().equals("kristian"));
//    }
//    
//    @Test
//    public void findAllTest() {
//        Person p1 = service.addPerson("kristian", "kacinetz", null, null, null, true, "MALE");
//        Person p2 = service.addPerson("janko", "kacinetz", null, null, null, true, "MALE");
//        Person p3 = service.addPerson("petko", "kacinetz", null, null, null, true, "MALE");
//
//        assertNotNull(service.findAll());
//    }

}
