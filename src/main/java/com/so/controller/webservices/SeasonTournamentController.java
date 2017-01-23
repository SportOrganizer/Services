package com.so.controller.webservices;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.controller.dto.SeasonTournamentDTO;
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

@RequestMapping("/seasontournament")
public class SeasonTournamentController {
    @Autowired
    SeasonTournamentService seasonTournamentService;


    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournaments(@RequestParam(value = "q",required = false) String query ){
        List<SeasonTournament> seasonTournaments;
        List<SeasonTournamentDTO> SeasonTournamentDTOS = new LinkedList<SeasonTournamentDTO>();
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();

        if(query != null){
            seasonTournaments = seasonTournamentService.findByNameContaining(query);
            jo.addProperty("query",query);

        }else{
            seasonTournaments = seasonTournamentService.findAll();
        }

        for(SeasonTournament st: seasonTournaments){
            SeasonTournamentDTOS.add(new SeasonTournamentDTO(st));
        }

        jo.addProperty("length",SeasonTournamentDTOS.size());
        jo.add("results",gson.toJsonTree(SeasonTournamentDTOS));

        return jo.toString();
    }

}
