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
import com.so.core.services.RegistrationService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(path = "/team/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllRegistrationTeams() {
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();
        List<RegistrationTeamDto> resultList;

        try {
            resultList = service.getAllTeams();
            jo.addProperty("length", resultList.size());
            jo.add("teams", gson.toJsonTree(resultList));
            return jo.toString();
        } catch (Exception e) {

        }

        return null;
    }

    @RequestMapping(path = "/team/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTeam(@PathVariable(value = "id") Integer i) {
        Gson gson = new Gson();
        try {
            RegistrationTeamDto team = service.getTeam(i);
            return gson.toJson(team);
        } catch (Exception e) {

        }
        return null;
    }

    @RequestMapping(path = "/player/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPlayer(@PathVariable(value = "id") Integer i) {
        Gson gson = new Gson();
        try {
            RegistrationPlayerDto p = service.getPlayer(i);
            gson.toJson(p);
        } catch (Exception e) {

        }
        return null;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String createSeason(@RequestBody RegistrationTeamDto regTeam) {

        Gson gson = new Gson();

        RegistrationTeamDto response = new RegistrationTeamDto();
        try {
            response = service.registrationTeam(regTeam);
        } catch (Exception ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            // vyriesit co robit ak exception
        }

        return gson.toJson(response);
    }

    @RequestMapping(path = "/team/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRegistrationTeam(@PathVariable(value = "id") Integer i) {

        try {
            service.deleteRegistrationTeam(i);

            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(path = "/player/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRegistrationPlayer(@PathVariable(value = "id") Integer i) {

        try {
            service.deleteRegistrationPlayer(i);

            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(path = "/player/update/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editPlayer(@RequestBody RegistrationPlayerDto updatedPlayer) {
        Gson gson = new Gson();

        RegistrationPlayerDto response = new RegistrationPlayerDto();
        try {
            response = service.editPlayer(updatedPlayer);
        } catch (Exception ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            // vyriesit co robit ak exception
        }

        return gson.toJson(response);
    }

    @RequestMapping(path = "/team/update/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editTeam(@RequestBody RegistrationTeamDto updatedTeam) {
        Gson gson = new Gson();

        RegistrationTeamDto response = new RegistrationTeamDto();
        try {
            response = service.editTeam(updatedTeam);
        } catch (Exception ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            // vyriesit co robit ak exception
        }

        return gson.toJson(response);
    }
}
