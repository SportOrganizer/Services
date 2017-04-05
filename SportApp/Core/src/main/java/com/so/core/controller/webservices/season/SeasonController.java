package com.so.core.controller.webservices.season;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.core.controller.dto.season.SeasonDTO;
import com.so.core.exception.AppException;
import com.so.core.services.season.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin
@RequestMapping("/season")
public class SeasonController {

    @Autowired
    SeasonService seasonService;

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasons(@RequestParam(value = "q", required = false) String query) throws AppException {
        List<SeasonDTO> result;
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();

        if (query != null) {
            result = seasonService.findByNameContaining(query);
            jo.addProperty("query", query);
        } else {
            result = seasonService.findAll();
        }
        return gson.toJson(result);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeason(@PathVariable(value = "id") Integer id) throws AppException {
        SeasonDTO result;
        Gson gson = new Gson();

        result = seasonService.findById(id);
        return gson.toJson(result);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String createSeason(@RequestBody SeasonDTO season) throws AppException {
        Gson gson = new Gson();

        SeasonDTO response = seasonService.createSeason(season.getName());
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteSeason(@PathVariable(value = "id") Integer i) throws AppException {
        Gson gson = new Gson();
        
        seasonService.deleteSeason(i);
        List< SeasonDTO> response = seasonService.findAll();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editSeasonTOurnament(@PathVariable(value="id") Integer id, @RequestBody SeasonDTO st) throws AppException {
        Gson gson = new Gson();
        if(!Objects.equals(id, st.getId())){
            throw new AppException(HttpStatus.BAD_REQUEST,"id url sa nerovna s id v dto");
        }
        SeasonDTO edited = seasonService.update(st);
        return gson.toJson(edited);
    }
}
