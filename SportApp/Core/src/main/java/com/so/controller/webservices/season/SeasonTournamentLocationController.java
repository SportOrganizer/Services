/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.controller.webservices.season;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.controller.dto.season.SeasonTournamentLocationDTO;
import com.so.dal.model.season.SeasonTournamentLocation;
import com.so.services.season.SeasonTournamentLocationService;
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

@RequestMapping("/seasontournamentlocation")
public class SeasonTournamentLocationController {
    @Autowired
    SeasonTournamentLocationService seasonTournamentLocationService;
    
    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournamentLocations(@RequestParam(value = "q", required = false) String query){
        List<SeasonTournamentLocation> seasonTournamentLocations;
        List<SeasonTournamentLocationDTO> seasonTournamentLocationDTOS = new LinkedList<SeasonTournamentLocationDTO>();
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();
        
        if(query != null){
            seasonTournamentLocations = seasonTournamentLocationService.findByNameContaining(query);
            jo.addProperty("query", query);
        }else{
            seasonTournamentLocations = seasonTournamentLocationService.findAll();
        }
        
        for(SeasonTournamentLocation stl: seasonTournamentLocations){
            seasonTournamentLocationDTOS.add(new SeasonTournamentLocationDTO(stl));
        }
        
        jo.addProperty("length", seasonTournamentLocationDTOS.size());
        jo.add("result", gson.toJsonTree(seasonTournamentLocationDTOS));
         
        
        return jo.toString();
    }
    
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity createSeasonTournamentLocation(@RequestBody SeasonTournamentLocationDTO stl ){

        if (stl == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Boolean status = seasonTournamentLocationService.createSeasonTournamentLocation(stl.getSeasonTournamentId(), stl.getName());
        if(status){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournamentGrup(@PathVariable(value="id") Integer id ){

        SeasonTournamentLocation seasonTournamentLocation;
        SeasonTournamentLocationDTO seasonTournamentLocationDTO;
        Gson gson = new Gson();
        
        seasonTournamentLocation = seasonTournamentLocationService.findById(id);
        seasonTournamentLocationDTO = new SeasonTournamentLocationDTO(seasonTournamentLocation);

        return gson.toJson(seasonTournamentLocationDTO);
    }
}
