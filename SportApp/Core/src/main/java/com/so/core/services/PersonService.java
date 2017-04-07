/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.core.controller.converter.PersonConverter;
import com.so.core.controller.dto.PersonDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Person;
import com.so.dal.core.repository.PersonRepository;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author peter
 */
@Service
public class PersonService {

    private final static Logger LOG = LoggerFactory.getLogger(PersonService.class); /// logovanie..

    @Autowired
    PersonRepository personRepo;

    @Autowired
    PersonConverter personConverter;

    @Transactional
    public PersonDTO addPerson(String name, String surname, Date birthDate, String mail, String phone, Boolean isStudent,
            String sex) throws AppException {

        LOG.info("addPerson({},{},{},{},{},{},{},{})", name, surname, birthDate, mail, phone, isStudent, sex);

        if (name == null || surname == null || isStudent == null || sex == null) {
            LOG.error("name surname isStudent sex nesmu byt prazdne: name={} surname={} sex={} isStudent={} ", name, surname, sex, isStudent);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre"); // zachytavat exceptiony v controllery alebo premysliet ako kde
        }

        Person p = new Person(name, surname, birthDate, mail, phone, isStudent, sex, null);

        p = personRepo.saveAndFlush(p);

        if (p == null) {
            LOG.info("Chyba pri ukladani person {}", p);
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "chyba pri ukladani person do db");
        }
        LOG.info("pridana osoba {}", p);
        return personConverter.personEntityToDto(p);
    }

    @Transactional
    public Person addPerson2(String name, String surname, Date birthDate, String mail, String phone, Boolean isStudent,
            String sex) throws AppException {

        LOG.info("addPerson({},{},{},{},{},{},{},{})", name, surname, birthDate, mail, phone, isStudent, sex);

        if (name == null || surname == null || isStudent == null || sex == null) {
            LOG.error("name surname isStudent sex nesmu byt prazdne: name={} surname={} sex={} isStudent={} ", name, surname, sex, isStudent);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre"); // zachytavat exceptiony v controllery alebo premysliet ako kde
        }

        Person p = new Person(name, surname, birthDate, mail, phone, isStudent, sex, null);

        p = personRepo.saveAndFlush(p);

        if (p == null) {
            LOG.info("Chyba pri ukladani person {}", p);
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "chyba pri ukladani person do db");
        }
        LOG.info("pridana osoba {}", p);
        return p;
    }

    public PersonDTO findPersonByNameAndSurname(String name, String surname) throws AppException {
        LOG.info("findPersonByNameAndSurname({},{})", name, surname);

        if (name == null || surname == null) {
            LOG.error("nevyplnene povinne parametre name={} surname={}", name, surname);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
        }

        Person p = personRepo.findByNameAndSurname(name, surname);

        if (p == null) {
            LOG.error("nenajdeny person podla mena a priezviska");
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena person podla mena a priezviska");
        }

        return personConverter.personEntityToDto(p);
    }

    @Transactional
    public PersonDTO createPerson(PersonDTO pDto) throws AppException {
        LOG.info("createPerson({})", pDto.getId());
        //MAIL NIE JE POVINNY? V DATABAZE TO JE TAK NASTAVENE
        if (pDto.getName() == null || pDto.getSurname() == null || pDto.isIsStudent() == null || pDto.getSex() == null) {
            LOG.error("parameter name, surname, isStudent, sex nemoze byt null: {}, {}, {}, {}", pDto.getName(), pDto.getSurname(), pDto.isIsStudent(), pDto.getSex());
            throw new AppException(HttpStatus.BAD_REQUEST, "nie je zadany povinny parameter");
        }
        
        if (pDto.getMail() != null && personRepo.findByMail(pDto.getMail()) != null) {
            LOG.error("duplicitny mail: {}", pDto.getMail());
            throw new AppException(HttpStatus.CONFLICT, "dany mail persony sa uz pouziva; mail=" + pDto.getMail());
        }
        
        Person p = personConverter.dtoToEntity(pDto);
        p = personRepo.saveAndFlush(p);

        if (p == null) {
            LOG.error("Person sa neulozila do databazy: {}", pDto.getId());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Person s ID:" + pDto.getId() + " sa neulozil do databazy");
        }

        return personConverter.personEntityToDto(p);

    }

    public PersonDTO findPersonByEmail(String mail) throws AppException {
        LOG.info("findPersonByEmail({})", mail);
        if (mail == null) {
            LOG.error("nevyplneny email");
            throw new AppException(HttpStatus.BAD_REQUEST, "nezadany email");
        }
        Person p = personRepo.findByMail(mail);
        if (p == null) {
            LOG.error("nenajdena person podla emaila");
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena person podla emaila");
        }
        return personConverter.personEntityToDto(p);
    }

    public Person findPersonByEmail2(String mail) throws AppException {
        LOG.info("findPersonByEmail({})", mail);
        if (mail == null) {
            LOG.error("nevyplneny email");
            throw new AppException(HttpStatus.BAD_REQUEST, "nezadany email");
        }
        Person p = personRepo.findByMail(mail);
        if (p == null) {
            LOG.error("nenajdena person podla emaila");
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena person podla emaila");
        }
        return p;
    }

    @Transactional
    public PersonDTO findById(Integer id) throws AppException {
        LOG.info("Person findById({})", id);
        if (id == null) {
            LOG.error("nevyplnene id: {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene id person");
        }

        Person p = personRepo.findOne(id);
        if (p == null) {
            LOG.error("k danemu id neexistuje zaznam v dbs");
            throw new AppException(HttpStatus.NOT_FOUND, "k danemu id neexistuje zaznam v dbs");
        }
        return personConverter.personEntityToDto(p);
    }

    @Transactional
    public Set<PersonDTO> findAll() {
        LOG.info("Person findByAll()");
        List<Person> lp = personRepo.findAll();
        Set<PersonDTO> personList = new HashSet<>();
        for (Person p : lp) {
            personList.add(personConverter.personEntityToDto(p));
        }
        return personList;
    }

    @Transactional
    public void deletePerson(Integer id) throws AppException {
        LOG.info("deletePerson({})", id);
        Person p = personRepo.findOne(id);

        if (p == null) {
            LOG.error("nenajdena person na vymazanie id={}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena osoba id=" + id);
        }
        personRepo.delete(p);
    }

    @Transactional
    public PersonDTO update(PersonDTO updated) throws AppException {
        LOG.info("update()");

        if (updated == null) {
            LOG.error("aktualizovany objekt je null");
            throw new AppException(HttpStatus.BAD_REQUEST, "zadany objekt je nespravne vyplneny");
        }
        Person p = personConverter.dtoToEntity(updated);
        Person saved = personRepo.saveAndFlush(p);

        if (saved == null) {
            LOG.error("nepodarilo sa ulozit person do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit person do db");
        }
        return personConverter.personEntityToDto(saved);
    }
}
