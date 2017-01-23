package com.so.controller.webservices;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.so.controller.dto.TournamentDTO;
import com.so.dal.model.Tournament;
import com.so.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


@RestController

@RequestMapping("/tournament")
public class TournamentController {
    @Autowired
    TournamentService tournamentService;


    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTournaments(@RequestParam(value = "q",required = false) String query ){
        List<Tournament> tournaments;
        List<TournamentDTO> tournamentDTOS = new LinkedList<TournamentDTO>();
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();


        if(query != null){
            tournaments = tournamentService.findByNameContaining(query);
            jo.addProperty("query",query);

        }else{
            tournaments = tournamentService.findAll();
        }

        for(Tournament t: tournaments){
            tournamentDTOS.add(new TournamentDTO(t));
        }

        jo.addProperty("length",tournamentDTOS.size());
        jo.add("results",gson.toJsonTree(tournamentDTOS));

        return jo.toString();

    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity createTournament(@RequestBody Tournament Tournament ){


        Boolean status = tournamentService.createTournament(Tournament.getName());
        if(status){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
