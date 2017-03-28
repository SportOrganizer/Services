package com.so.core.controller.webservices.season;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.core.controller.dto.season.SeasonDTO;
import com.so.dal.core.model.season.Season;
import com.so.core.services.season.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/season")
public class SeasonController {
    
    @Autowired
    SeasonService seasonService;


    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasons(@RequestParam(value = "q",required = false) String query ){
        List<SeasonDTO> result;
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();

        if(query != null){
            result = seasonService.findByNameContaining(query);
            jo.addProperty("query",query);

        }else{
            result = seasonService.findAll();
        }



        return gson.toJson(result);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeason(@PathVariable(value="id") Integer id ){

        SeasonDTO result;
        Gson gson = new Gson();

        result = seasonService.findById(id);
       

        return gson.toJson(result);
    }


    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity createSeason(@RequestBody Season season ){


        Boolean status = seasonService.createSeason(season.getName());
        if(status){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
    
      @RequestMapping(path = "/{id}", method = RequestMethod.DELETE ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteSeason(@PathVariable(value = "id") Integer i) {

        Gson gson = new Gson();
        seasonService.deleteSeason(i);

        List< SeasonDTO> response = seasonService.findAll();

        return gson.toJson(response);
    }
    
      @RequestMapping(path = "/update/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
      public String editSeasonTOurnament(@RequestBody SeasonDTO st){
          
          Gson gson = new Gson();
          
          SeasonDTO edited = seasonService.update(st);
          
          return gson.toJson(edited);
          
      }


}
