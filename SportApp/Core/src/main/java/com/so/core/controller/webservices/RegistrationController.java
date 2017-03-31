/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.core.controller.dto.registration.RegistrationPlayerDto;
import com.so.core.controller.dto.registration.RegistrationTeamDto;
import com.so.core.exception.AppException;
import com.so.core.services.RegistrationService;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peter
 */
@RestController
@CrossOrigin
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    
      @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String registration(@RequestBody RegistrationTeamDto regTeam) throws IOException, AppException {
        Gson gson = new Gson();
        RegistrationTeamDto response = service.registrationTeam(regTeam);
        return gson.toJson(response);
    }
    
    @RequestMapping(path = "/team/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllRegistrationTeams() throws AppException {
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();
        List<RegistrationTeamDto> resultList;

        resultList = service.getAllTeams();
        jo.addProperty("length", resultList.size());
        jo.add("teams", gson.toJsonTree(resultList));
        return jo.toString();
    }

    @RequestMapping(path = "/team/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTeam(@PathVariable(value = "id") Integer i) throws AppException {
        Gson gson = new Gson();
        RegistrationTeamDto team = service.getTeam(i);
        return gson.toJson(team);
    }

    @RequestMapping(path = "/player/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPlayer(@PathVariable(value = "id") Integer i) throws AppException {
        Gson gson = new Gson();
        RegistrationPlayerDto p = service.getPlayer(i);
        return gson.toJson(p);
    }


    @RequestMapping(path = "/team/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteRegistrationTeam(@PathVariable(value = "id") Integer i) throws AppException {
         Gson gson = new Gson();
        service.deleteRegistrationTeam(i);
        return gson.toJson(service.getAllTeams());
    }

    @RequestMapping(path = "/player/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteRegistrationPlayer(@PathVariable(value = "id") Integer i) throws AppException {
        service.deleteRegistrationPlayer(i);
          Gson gson = new Gson();
        return gson.toJson(service.getAllTeams());
    }

    @RequestMapping(path = "/player/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editPlayer(@PathVariable(value="id")Integer id, @RequestBody RegistrationPlayerDto updatedPlayer) throws AppException {
        Gson gson = new Gson();
              if (!Objects.equals(id, updatedPlayer.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id url sa nerovna s id v dto");
        }
        RegistrationPlayerDto response = service.editPlayer(updatedPlayer);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/team/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editTeam(@PathVariable(value="id")Integer id, @RequestBody RegistrationTeamDto updatedTeam) throws AppException {
        Gson gson = new Gson();
              if (!Objects.equals(id, updatedTeam.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id url sa nerovna s id v dto");
        }
        RegistrationTeamDto response = service.editTeam(updatedTeam);
        return gson.toJson(response);
    }
}
