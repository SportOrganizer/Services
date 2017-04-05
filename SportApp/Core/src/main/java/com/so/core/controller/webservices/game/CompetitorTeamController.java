/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices.game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.core.controller.dto.game.CompetitorTeamDto;
import com.so.core.exception.AppException;
import com.so.core.services.game.CompetitorTeamService;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kristián Kačinetz
 */

@RestController
@CrossOrigin
@RequestMapping("/competitorteam")
public class CompetitorTeamController {

    @Autowired
    CompetitorTeamService competitorTeamService;
    
    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getCompetitorTeams(@RequestParam(value = "q", required = false) String query) throws AppException {
        List<CompetitorTeamDto> competitorTeams;
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();

        competitorTeams = competitorTeamService.findAll();
        
        jo.addProperty("length", competitorTeams.size());
        jo.add("results", gson.toJsonTree(competitorTeams));

        return jo.toString();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getCompetitorTeam(@PathVariable(value = "id") Integer id) throws AppException {
        CompetitorTeamDto competitorTeamDto;
        Gson gson = new Gson();

        competitorTeamDto = competitorTeamService.findByID(id);
        return gson.toJson(competitorTeamDto);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String createCompetitorTeam(@RequestBody CompetitorTeamDto st) throws IOException, AppException {
        Gson gson = new Gson();

        CompetitorTeamDto response = competitorTeamService.createCompetitorTeam(st);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteCompetitorTeam(@PathVariable(value = "id") Integer i) throws AppException {
        Gson gson = new Gson();

        competitorTeamService.deleteCompetitorTeam(i);
        List< CompetitorTeamDto> response = competitorTeamService.findAll();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editCompetitorTeam(@PathVariable(value = "id") Integer id, @RequestBody CompetitorTeamDto st) throws AppException {
        Gson gson = new Gson();
        if (!Objects.equals(id, st.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id url sa nerovna s id v dto");
        }
        CompetitorTeamDto edited = competitorTeamService.update(st);
        return gson.toJson(edited);
    }

}
