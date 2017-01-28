package com.so.controller.webservices;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.controller.dto.SeasonTournamentDTO;
import com.so.controller.dto.SeasonTournamentRoundDTO;
import com.so.controller.dto.TournamentDTO;
import com.so.dal.model.season.SeasonTournament;
import com.so.dal.model.season.SeasonTournamentRound;
import com.so.services.season.SeasonTournamentRoundService;
import com.so.services.season.SeasonTournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


@RestController

@RequestMapping("/seasontournamentround")
public class SeasonTournamentRoundController {
    @Autowired
    SeasonTournamentRoundService seasonTournamentRoundService;


    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournaments(@RequestParam(value = "q",required = false) String query ){
        List<SeasonTournamentRound> seasonTournamentRounds;
        List<SeasonTournamentRoundDTO> seasonTournamentRoundDTOS = new LinkedList<SeasonTournamentRoundDTO>();
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();

        if(query != null){
            seasonTournamentRounds = seasonTournamentRoundService.findByNameContaining(query);
            jo.addProperty("query",query);

        }else{
            seasonTournamentRounds = seasonTournamentRoundService.findAll();
        }

        for(SeasonTournamentRound st: seasonTournamentRounds){
            seasonTournamentRoundDTOS.add(new SeasonTournamentRoundDTO(st));
        }

        jo.addProperty("length",seasonTournamentRoundDTOS.size());
        jo.add("results",gson.toJsonTree(seasonTournamentRoundDTOS));

        return jo.toString();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournament(@PathVariable(value="id") Integer id ){

        SeasonTournamentRound seasonTournamentRound;
        SeasonTournamentRoundDTO seasonTournamentRoundDTO;
        Gson gson = new Gson();

        seasonTournamentRound = seasonTournamentRoundService.findById(id);
        seasonTournamentRoundDTO = new SeasonTournamentRoundDTO(seasonTournamentRound);

        return gson.toJson(seasonTournamentRoundDTO);
    }


    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity createSeasonTournament(@RequestBody SeasonTournamentRoundDTO st ){

        if (st == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Boolean status = seasonTournamentRoundService.createSeasonTournamentRound(st.getSeasonTournamentId(), st.getName());
        if(status){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
