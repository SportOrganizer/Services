/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices.game;

import com.google.gson.Gson;
import com.so.core.controller.dto.season.penalty.PenaltyDto;
import com.so.core.controller.dto.season.penalty.PenaltyTypeDto;
import com.so.core.controller.dto.season.penalty.SeasonTournamentPenaltySettingsDto;
import com.so.core.controller.dto.season.penalty.SeasonTournamentPenaltyTypeDto;
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
    public String getAllPenalty() {
        Gson gson = new Gson();
        List<PenaltyDto> response = service.getAllPenalties();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOnePenalty(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        PenaltyDto response = service.getOnePenalty(id);
        return gson.toJson(response);
    }

//    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String getAllPenaltyBySport(@PathVariable(value = "idSport") Integer id) throws AppException {
//        Gson gson = new Gson();
//        List<PenaltyDto> response = service.getAllPenaltiesBySport(id);
//        return gson.toJson(response);
//    }
    
    
//        @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String getAllPenaltyBySport(@RequestParam(value = "idSport") Integer idSport,@RequestParam(value="aa") String aa) throws AppException {
//        Gson gson = new Gson();
//        List<PenaltyDto> response = service.getAllPenaltiesBySport(idSport);
//        return gson.toJson(response);
//    }

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

    ///////////////////////////// Season Tournament PenaltyType /////////////////////////////////////
    @RequestMapping(path = "/stpenaltytype/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addStPenaltyType(@RequestBody SeasonTournamentPenaltyTypeDto request) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentPenaltyTypeDto response = service.addStPenaltyType(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltytype/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateStPenaltyType(@PathVariable(value = "id") Integer id, @RequestBody SeasonTournamentPenaltyTypeDto request) throws AppException {

        if (!Objects.equals(id, request.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id v url sa nerovna id v dto");
        }
        Gson gson = new Gson();
        SeasonTournamentPenaltyTypeDto response = service.editStPenaltyType(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltytype/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllStPenaltyType() {
        Gson gson = new Gson();
        List<SeasonTournamentPenaltyTypeDto> response = service.getAllStPenaltyType();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltytype/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOneStPenaltyType(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentPenaltyTypeDto response = service.getOneSeasonTournamentPenaltyType(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltytype/byst/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllStPenaltyType(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        List<SeasonTournamentPenaltyTypeDto> response = service.getAllStPenaltyTypeBySt(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltytype/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteStPenaltyType(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        service.deleteStPenaltyType(id);
        return gson.toJson(service.getAllStPenaltyType());
    }
/////////////////////// SEASON TOURNAMENT PENALTY SETTINGS ////////////////////////

    @RequestMapping(path = "/stpenaltysetting/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addStPenaltySettings(@RequestBody SeasonTournamentPenaltySettingsDto request) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentPenaltySettingsDto response = service.addStPenaltySettings(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltysetting/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateStPenaltySettings(@PathVariable(value = "id") Integer id, @RequestBody SeasonTournamentPenaltySettingsDto request) throws AppException {

        if (!Objects.equals(id, request.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id v url sa nerovna id v dto");
        }
        Gson gson = new Gson();
        SeasonTournamentPenaltySettingsDto response = service.editStPenaltySettings(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltysettings/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllStPenaltySettings() {
        Gson gson = new Gson();
        List<SeasonTournamentPenaltySettingsDto> response = service.getAllStPenaltySettings();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltysettings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOneStPenaltySettings(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentPenaltySettingsDto response = service.getOneSeasonTournamentPenaltySettings(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltysettings/byst/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllStPenaltySettings(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        List<SeasonTournamentPenaltySettingsDto> response = service.getAllStPenaltySettingsBySt(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltysettings/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteStPenaltySettings(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        service.deleteStPenaltySettings(id);
        return gson.toJson(service.getAllStPenaltySettings());
    }
}
