package com.so.controller;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.so.dal.model.Person;
import com.so.dal.model.season.Season;
import com.so.dal.repository.PersonRepository;
import com.so.services.PersonService;
import com.so.services.season.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


@RestController

@RequestMapping("/season")
public class SeasonController {
    @Autowired
    SeasonService seasonService;



    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasons(@RequestParam(value = "q",required = false) String query ){
        List<Season> seasonsList;

        if(query != null){
            seasonsList = seasonService.findByNameContaining(query);
        }else{
            seasonsList = seasonService.findAll();
        }

        return createSeasonListJSON(seasonsList);

    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity createSeason(@RequestBody Season season ){


        Boolean status = seasonService.createSeason(season.getName());
        if(status){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }





    private String createSeasonListJSON(List<Season> seasonsList){
        final List<String> seasonNames = new LinkedList<String>();
        for(Season season : seasonsList){
            seasonNames.add(season.getName());
        }

        Gson gson = new Gson();
        return gson.toJson(seasonNames);
    }







}
