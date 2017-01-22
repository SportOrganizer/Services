package com.so.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.so.dal.model.Person;
import com.so.dal.repository.PersonRepository;
import com.so.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by janpolacek on 12/26/16.
 */

@RestController

@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping
    public String get() {
        return "Get persons";
    }

    //com
    @RequestMapping(path = "/search/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPersonById(@RequestParam(value = "name") String name,
                                @RequestParam("surname") String surname) {

        Person p = personService.findPersonByNameAndSurname(name,surname);

        return "{" +
                    "\"id\":" + "\"" + p.getId() +"\"," +
                    "\"name\":" + "\"" + p.getName() +"\"," +
                    "\"surname\":" + "\"" + p.getSurname() +"\"," +
                    "\"mail\":" + "\"" + p.getMail() +"\"," +
                    "\"phone\":" + "\"" + p.getPhone() +"\"" +
                "}";

    }
}
