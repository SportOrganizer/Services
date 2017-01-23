package com.so.controller.webservices;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.dal.model.season.SeasonTournament;
import com.so.services.season.SeasonTournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


@RestController

@RequestMapping("/seasonTournament")
public class SeasonTournamentController {
    @Autowired
    SeasonTournamentService seasonTournamentService;


//    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String getSeasonTournaments(@RequestParam(value = "q",required = false) String query ){
//        List<SeasonTournament> SeasonTournaments;
//        List<SeasonTournamentDTO> SeasonTournamentDTOS = new LinkedList<SeasonTournamentDTO>();
//        JsonObject jo = new JsonObject();
//        Gson gson = new Gson();
//
//
//        if(query != null){
//            SeasonTournaments = SeasonTournamentService.findByNameContaining(query);
//            jo.addProperty("query",query);
//
//        }else{
//            SeasonTournaments = SeasonTournamentService.findAll();
//        }
//
//        for(SeasonTournament t: SeasonTournaments){
//            SeasonTournamentDTOS.add(new SeasonTournamentDTO(t));
//        }
//
//        jo.addProperty("length",SeasonTournamentDTOS.size());
//        jo.add("results",gson.toJsonTree(SeasonTournamentDTOS));
//
//        return jo.toString();
//
//    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity createSeasonTournament(){


        Boolean status = seasonTournamentService.createSeasonTournament(1,1,"Tests");
        if(status){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
