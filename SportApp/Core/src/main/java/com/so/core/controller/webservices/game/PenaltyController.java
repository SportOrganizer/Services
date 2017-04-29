/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices.game;

import com.google.gson.Gson;
import com.so.core.controller.dto.season.penalty.PenaltyCustomDto;
import com.so.core.controller.dto.season.penalty.PenaltyDto;
import com.so.core.controller.dto.season.penalty.PenaltyTypeDto;
import com.so.core.exception.AppException;
import com.so.core.services.game.PenaltyService;
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
 * @author peter
 */
@RestController
@CrossOrigin
@RequestMapping("/penalty")
public class PenaltyController {

    @Autowired
    private PenaltyService service;

    ////////////////////////    PENALTY ////////////////////////////
    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addPenalty(@RequestBody PenaltyDto request) throws AppException {
        Gson gson = new Gson();
        PenaltyDto response = service.addPenalty(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/duplicate/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String duplicatePenalty(@RequestBody PenaltyDto request) throws AppException {
        Gson gson = new Gson();
        PenaltyDto response = service.duplicatePenalty(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updatePenalty(@PathVariable(value = "id") Integer id, @RequestBody PenaltyDto request) throws AppException {

        if (!Objects.equals(id, request.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id v url sa nerovna id v dto");
        }
        Gson gson = new Gson();
        PenaltyDto response = service.editPenalty(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllPenalty(@RequestParam(value = "sport", required = false) Integer idSport) throws AppException {
        Gson gson = new Gson();
        List<PenaltyDto> response;
        if (idSport == null) {
            response = service.getAllPenalties();
        } else {
            response = service.getAllPenaltiesBySport(idSport);
        }
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOnePenalty(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        PenaltyDto response = service.getOnePenalty(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deletePenalty(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        service.deletePenalty(id);
        return gson.toJson(service.getAllPenalties());
    }

    ////////////////// PENALTY TYPE //////////////////////
    @RequestMapping(path = "/penaltytype/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addPenaltyType(@RequestBody PenaltyTypeDto request) throws AppException {
        Gson gson = new Gson();
        PenaltyTypeDto response = service.addPenaltyType(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/penaltytype/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updatePenaltyType(@PathVariable(value = "id") Integer id, @RequestBody PenaltyTypeDto request) throws AppException {

        if (!Objects.equals(id, request.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id v url sa nerovna id v dto");
        }
        Gson gson = new Gson();
        PenaltyTypeDto response = service.editPenaltyType(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/penaltytype/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllPenaltyType() {
        Gson gson = new Gson();
        List<PenaltyTypeDto> response = service.getAllPenaltyType();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/penaltytype/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOnePenaltyType(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        PenaltyTypeDto response = service.getOnePenaltyType(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/penaltytype/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deletePenaltyType(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        service.deletePenaltyType(id);
        return gson.toJson(service.getAllPenaltyType());
    }
}
