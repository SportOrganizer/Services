/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices;

import com.google.gson.Gson;
import com.so.core.controller.dto.TeamDTO;
import com.so.core.exception.AppException;
import com.so.core.services.TeamService;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
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
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTeams(@RequestParam(value = "q", required = false) String query) {
        Set<TeamDTO> result;
        Gson gson = new Gson();
        result = teamService.findAll();
        return gson.toJson(result);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTeam(@PathVariable(value = "id") Integer id) throws AppException {
        TeamDTO result;
        Gson gson = new Gson();
        result = teamService.findById(id);
        return gson.toJson(result);
    }
    
    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String createTeam(@RequestBody TeamDTO tDto) throws IOException, AppException {
        Gson gson = new Gson();

        TeamDTO response = teamService.createTeam(tDto);
        return gson.toJson(response);
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deletePerson(@PathVariable(value = "id") Integer i) throws AppException {
        Gson gson = new Gson();
        teamService.deleteTeam(i);
        Set<TeamDTO> response = teamService.findAll();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editPerson(@PathVariable(value = "id") Integer id, @RequestBody TeamDTO t) throws AppException {
        
        if (!Objects.equals(id, t.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id url sa nerovna s id v dto");
        }
        
        Gson gson = new Gson();
        TeamDTO edited = teamService.update(t);
        return gson.toJson(edited);
    }
}
