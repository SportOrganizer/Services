package com.so.controller;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.so.controller.dto.SeasonDTO;
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
        List<Season> seasons;
        List<SeasonDTO> seasonDTOS = new LinkedList<SeasonDTO>();
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();

        if(query != null){
            seasons = seasonService.findByNameContaining(query);
            jo.addProperty("query",query);

        }else{
            seasons = seasonService.findAll();
        }

        for(Season s: seasons){
            seasonDTOS.add(new SeasonDTO(s));
        }

        jo.addProperty("length",seasonDTOS.size());
        jo.add("results",gson.toJsonTree(seasonDTOS));
        return jo.toString();
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


}
