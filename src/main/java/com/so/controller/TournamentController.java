package com.so.controller;


import com.google.gson.Gson;
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
        List<Tournament> tournamentList;

        if(query != null){
            tournamentList = tournamentService.findByNameContaining(query);
        }else{
            tournamentList = tournamentService.findAll();
        }

        return createTournamentListJSON(tournamentList);

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



    private String createTournamentListJSON(List<Tournament> TournamentsList){
        final List<String> TournamentNames = new LinkedList<String>();
        for(Tournament Tournament : TournamentsList){
            TournamentNames.add(Tournament.getName());
        }

        Gson gson = new Gson();
        return gson.toJson(TournamentNames);
    }







}
