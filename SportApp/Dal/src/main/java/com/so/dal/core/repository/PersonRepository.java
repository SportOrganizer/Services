/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository;

import com.so.dal.core.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author peter
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{
    
    public Person findByNameAndSurname(String name, String surname);
    public Person findByMail(String mail);
}
