/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.controller.webservices;

import com.google.gson.Gson;
import com.so.core.exception.AppException;
import com.so.floorball.controller.floorballGame.dto.RequestSportFloorballGameActivityDto;
import com.so.floorball.controller.floorballGame.dto.ResponseSportFloorballGameActivityDto;
import com.so.floorball.services.SportFloorballGameActivityService;
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
@RequestMapping("/activity")
public class SportFloorballGameActivityController {
    
    @Autowired
    SportFloorballGameActivityService activityService;
    
    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addActivity(@RequestBody RequestSportFloorballGameActivityDto request) throws AppException {
        Gson gson = new Gson();
        ResponseSportFloorballGameActivityDto response = activityService.addSportFloorballGameActivity(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateActivity(@PathVariable(value = "id") Integer id, @RequestBody RequestSportFloorballGameActivityDto request) throws AppException {

        if (!Objects.equals(id, request.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id v url sa nerovna id v dto");
        }
        Gson gson = new Gson();
        ResponseSportFloorballGameActivityDto response = activityService.updateActivity(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllActivities() {
        Gson gson = new Gson();
        List<ResponseSportFloorballGameActivityDto> response = activityService.findAllActivities();
        return gson.toJson(response);
    }
    
    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllActivitiesByGame(@RequestParam(value = "game", required = false) Integer idGame) throws AppException {
        Gson gson = new Gson();
        List<ResponseSportFloorballGameActivityDto> response = activityService.findAllActivitiesByGame(idGame);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOneActivity(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        ResponseSportFloorballGameActivityDto response = activityService.findOneActivity(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteGameActivity(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        activityService.deleteSportFlorbalGameActivity(id);
        return gson.toJson(activityService.findAllActivities());
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteGameActivityByGame(@RequestParam(value = "game", required = false) Integer idGame) throws AppException {
        Gson gson = new Gson();
        activityService.deleteSportFlorbalGameActivityByGame(idGame);
        return gson.toJson(activityService.findAllActivities());
    }
    
}
