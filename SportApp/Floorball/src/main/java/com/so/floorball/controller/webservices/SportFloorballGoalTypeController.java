/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.controller.webservices;

import com.google.gson.Gson;
import com.so.core.exception.AppException;
import com.so.floorball.controller.floorballGame.dto.SportFloorballGoalTypeDto;
import com.so.floorball.services.SportFloorballGoalTypeService;
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
@RequestMapping("/goaltype")
public class SportFloorballGoalTypeController {
    
    @Autowired
    SportFloorballGoalTypeService goalTypeService;
    
    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addGoaltype(@RequestBody SportFloorballGoalTypeDto request) throws AppException {
        Gson gson = new Gson();
        SportFloorballGoalTypeDto response = goalTypeService.addSportFloorballGoalType(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateGoaltype(@PathVariable(value = "id") Integer id, @RequestBody SportFloorballGoalTypeDto request) throws AppException {

        if (!Objects.equals(id, request.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id v url sa nerovna id v dto");
        }
        Gson gson = new Gson();
        SportFloorballGoalTypeDto response = goalTypeService.updateSportFlorbalGoalType(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllGoalTypes() {
        Gson gson = new Gson();
        List<SportFloorballGoalTypeDto> response = goalTypeService.findAllGoalType();
        return gson.toJson(response);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOneGoalType(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        SportFloorballGoalTypeDto response = goalTypeService.findOneGoalType(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteGoaltype(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        goalTypeService.deleteSportFlorbalGoalType(id);
        return gson.toJson(goalTypeService.findAllGoalType());
    }
    
}
