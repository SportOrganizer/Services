/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices;

import com.google.gson.Gson;
import com.so.core.controller.dto.SportsDto;
import com.so.core.exception.AppException;
import com.so.core.services.SportService;
import java.util.List;
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
 * @author peter
 */
@RestController
@CrossOrigin
@RequestMapping("/sport")
public class SportsController {

    @Autowired
    SportService service;

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSports() {
        List<SportsDto> result;
        Gson gson = new Gson();
        result = service.getAll();
        return gson.toJson(result);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addSport(@RequestBody SportsDto request) throws AppException {
        Gson gson = new Gson();
        SportsDto result = service.addSport(request);
        return gson.toJson(result);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteSport(@PathVariable(value = "id") Integer id) {
        List<SportsDto> result;
        Gson gson = new Gson();
        service.deleteSport(id);
        result = service.getAll();
        return gson.toJson(result);
    }

}
