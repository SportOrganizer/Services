package com.so.core.controller.webservices.season;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.core.services.season.SeasonTournamentService;
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
        List<SeasonTournamentDTO> seasonTournamentDTOS = new LinkedList<SeasonTournamentDTO>();
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();

        if(query != null){
            seasonTournaments = seasonTournamentService.findByNameContaining(query);
            jo.addProperty("query",query);

        }else{
            seasonTournaments = seasonTournamentService.findAll();
        }

        for(SeasonTournament st: seasonTournaments){
            seasonTournamentDTOS.add(new SeasonTournamentDTO(st));
        }

        jo.addProperty("length",seasonTournamentDTOS.size());
        jo.add("results",gson.toJsonTree(seasonTournamentDTOS));

        return jo.toString();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournament(@PathVariable(value="id") Integer id ){

        SeasonTournament seasonTournament;
        SeasonTournamentDTO seasonTournamentDTO;
        Gson gson = new Gson();

        seasonTournament = seasonTournamentService.findById(id);
        seasonTournamentDTO = new SeasonTournamentDTO(seasonTournament);

        return gson.toJson(seasonTournamentDTO);
    }

//    {
//        "seasonId": 1,
//            "tournamentId": 1,
//            "name": "Tests2"
//    }
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity createSeasonTournament(@RequestBody SeasonTournamentDTO st ){

        if (st == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Boolean status = seasonTournamentService.createSeasonTournament(st.getSeasonId(), st.getTournamentId(), st.getName());
        if(status){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
