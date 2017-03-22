/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.dal.core.model.Person;
import com.so.dal.core.model.game.CompetitorTeamPlayer;
import com.so.dal.core.repository.PersonRepository;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Set;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author peter
 */

@Service
public class PersonService {

    private final static Logger LOG = LoggerFactory.getLogger(PersonService.class); /// logovanie..
    //// properties v resources/log4j.properties pozri TODO

    @Autowired
    PersonRepository personRepo;

    @Transactional
    public Person addPerson(String name, String surname, Date birthDate, String mail, String phone, Boolean isStudent,
            String sex) {

        LOG.info("addPerson({},{},{},{},{},{},{},{})", name, surname, birthDate, mail, phone, isStudent, sex);

        if (name == null || surname == null || isStudent == null || sex == null) {
            LOG.error("name surname isStudent sex nesmu byt prazdne: name={} surname={} sex={} isStudent={} ", name, surname, sex, isStudent);
            throw new InvalidParameterException("nevyplnene povinne parametre"); // zachytavat exceptiony v controllery alebo premysliet ako kde
        }

        //todo competitorTeamPlayers sa bude v buducnu doplnat vyhladavanim timu po poslani z napr. id z FE to sa odkomentuje este
        // pri tvorbe FE
        Person p = new Person(name, surname, birthDate, mail, phone, isStudent, sex, null);

        p = personRepo.saveAndFlush(p);

        if (p == null) {
            LOG.info("Chyba pri pridavani persony {}", p);
            throw new IllegalStateException();
        }
        LOG.info("pridana osoba {}", p);
        return p;
    }

    public Person findPersonByNameAndSurname(String name, String surname) {

        LOG.info("findPersonByNameAndSurname({},{})", name, surname);

        Person p = personRepo.findByNameAndSurname(name, surname);
        return p;
    }
    
     public Person findPersonByEmail(String mail) {

        LOG.info("findPersonByEmail({})", mail);

        Person p = personRepo.findByMail(mail);
        return p;
    }
}
