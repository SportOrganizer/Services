package com.so.controller;


import com.google.gson.Gson;
import com.so.dal.model.Person;
import com.so.dal.repository.PersonRepository;
import com.so.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController

@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping
    public String get() {
        return "Get persons";
    }

    @RequestMapping(path = "",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPersonById(@RequestParam(value = "name") String name,
                                @RequestParam("surname") String surname) {

        Person p = personService.findPersonByNameAndSurname(name,surname);
//
//        Gson gson = new Gson();
//        return gson.toJson(p);


        return "{" +
                    "\"id\":" + "\"" + p.getId() +"\"," +
                    "\"name\":" + "\"" + p.getName() +"\"," +
                    "\"surname\":" + "\"" + p.getSurname() +"\"," +
                    "\"mail\":" + "\"" + p.getMail() +"\"," +
                    "\"phone\":" + "\"" + p.getPhone() +"\"" +
                "}";

    }


    private void getPersonJSON(){

    }
}
