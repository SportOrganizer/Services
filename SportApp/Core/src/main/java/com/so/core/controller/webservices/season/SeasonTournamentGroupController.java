/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices.season;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.so.core.controller.dto.season.SeasonTournamentGroupDTO;
import com.so.dal.core.model.season.SeasonTournamentGroup;
import com.so.core.services.season.SeasonTournamentGroupService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tomas
 */
@RestController

@RequestMapping("/seasontournamentgroup")
public class SeasonTournamentGroupController {
    @Autowired
    SeasonTournamentGroupService seasonTournamentGroupService;
    
    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournamentGroups(@RequestParam(value = "q", required = false) String query){
        List<SeasonTournamentGroup> seasonTournamentGroups;
        List<SeasonTournamentGroupDTO> seasonTournamentGroupsDTOS = new LinkedList<SeasonTournamentGroupDTO>();
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();
        
        if(query != null){
            seasonTournamentGroups = seasonTournamentGroupService.findByNameContaining(query);
            jo.addProperty("query", query);
        }else{
            seasonTournamentGroups = seasonTournamentGroupService.findAll();
        }
        
        for(SeasonTournamentGroup stg: seasonTournamentGroups){
            seasonTournamentGroupsDTOS.add(new SeasonTournamentGroupDTO(stg));
        }
        
        jo.addProperty("length", seasonTournamentGroups.size());
        jo.add("result", gson.toJsonTree(seasonTournamentGroupsDTOS));
         
        /*
        ulozenie dalo ok
        skontrolovat databazu ci je tam zaznam
        pada mi to ked chcem selektovat vsetko
        */
        return jo.toString();
    }
    
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity createSeasonTournamentGroup(@RequestBody SeasonTournamentGroupDTO stg ){

        if (stg == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Boolean status = seasonTournamentGroupService.createSeasonTournamentGroup(stg.getSeasonTournamentId(), stg.getName());
        if(status){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournamentGrup(@PathVariable(value="id") Integer id ){

        SeasonTournamentGroup seasonTournamentGroup;
        SeasonTournamentGroupDTO seasonTournamentGroupDTO;
        Gson gson = new Gson();
        
        seasonTournamentGroup = seasonTournamentGroupService.findById(id);
        seasonTournamentGroupDTO = new SeasonTournamentGroupDTO(seasonTournamentGroup);

        return gson.toJson(seasonTournamentGroupDTO);
    }
}
