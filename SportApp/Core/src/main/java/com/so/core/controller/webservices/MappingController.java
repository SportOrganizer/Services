/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices;

import com.google.gson.Gson;
import com.so.core.controller.dto.IncompatiblePlayersDTO;
import com.so.core.controller.dto.MappingDTO;
import com.so.core.controller.dto.PersonDTO;
import com.so.core.controller.dto.registration.RegistrationTeamDto;
import com.so.core.services.MappingService;
import com.so.dal.core.model.season.SeasonTournament;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kristián Kačinetz
 */
@RestController
@CrossOrigin
@RequestMapping("/mapping")
public class MappingController {

    @Autowired
    private MappingService mappingService;

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String createMapping(@PathVariable(value = "id") Integer seasonTournamentId) {

        Gson gson = new Gson();

        Set<IncompatiblePlayersDTO> response = new HashSet<>();
        try {
            response = mappingService.MappingTeamsAndPlayers(seasonTournamentId);
        } catch (Exception ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            // vyriesit co robit ak exception
        }

        return gson.toJson(response);
    }
    
     @RequestMapping(path = "/submit/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String submitMappingPlayer(@RequestBody MappingDTO request){
        Gson gson = new Gson();
        PersonDTO response = new PersonDTO();
        
        try {
            response = mappingService.ConfirmIncompatiblePlayers(request.getNewPlayer(), request.getCompetitorTeamId());
        } catch (Exception ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            // vyriesit co robit ak exception
        }

        return gson.toJson(response);
    }
    

}
