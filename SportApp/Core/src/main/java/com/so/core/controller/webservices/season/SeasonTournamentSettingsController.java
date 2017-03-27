/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices.season;

import com.google.gson.Gson;
import com.so.core.controller.dto.season.SeasonTournamentSettingsDTO;
import com.so.core.services.season.SeasonTournamentSettingsService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kristian
 */
@RestController

@RequestMapping("/seasontournamentsettings")
@CrossOrigin
public class SeasonTournamentSettingsController {
    @Autowired
    SeasonTournamentSettingsService service;
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournamentSettings(@PathVariable(value="id") Integer id){
        List<SeasonTournamentSettingsDTO> response = new ArrayList<SeasonTournamentSettingsDTO>();
        
        Gson gson = new Gson();
        
        try {
            response = service.findByTournamentId(id);
        } catch (Exception e) {
            System.out.println(""); //TODO
        }
        
        return gson.toJson(response);
    }
}
