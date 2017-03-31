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
import com.so.core.exception.AppException;
import com.so.core.services.MappingService;
import java.util.Set;
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
    public String createMapping(@PathVariable(value = "id") Integer seasonTournamentId) throws AppException {
        Gson gson = new Gson();
        Set<IncompatiblePlayersDTO> response = mappingService.MappingTeamsAndPlayers(seasonTournamentId);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/submit/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String submitMappingPlayer(@RequestBody MappingDTO request) throws AppException {
        Gson gson = new Gson();
        PersonDTO response = mappingService.ConfirmIncompatiblePlayers(request.getNewPlayer(), request.getCompetitorTeamId());
        return gson.toJson(response);
    }

}
