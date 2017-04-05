package com.so.core.controller.webservices;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.core.controller.dto.TournamentDTO;
import com.so.dal.core.model.Tournament;
import com.so.core.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/tournament")
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTournaments(@RequestParam(value = "q", required = false) String query) {
        List<TournamentDTO> tournaments;
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();

        if (query != null) {
            tournaments = tournamentService.findByNameContaining(query);
            jo.addProperty("query", query);

        } else {
            tournaments = tournamentService.findAll();
        }

        jo.addProperty("length", tournaments.size());
        jo.add("results", gson.toJsonTree(tournaments));

        return jo.toString();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTournament(@PathVariable(value = "id") Integer id) {

        TournamentDTO tournamentDTO;
        Gson gson = new Gson();

        tournamentDTO = tournamentService.findById(id);

        return gson.toJson(tournamentDTO);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String createTournament(@RequestBody TournamentDTO t) {

//
//        Boolean status = tournamentService.createTournament(t.getName());
//        if(status){
//            return new ResponseEntity(HttpStatus.OK);
//        }else{
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
        Gson gson = new Gson();

        TournamentDTO response = null;
        try {
            response = tournamentService.createTournament(t);
        } catch (Exception ex) {
            Logger.getLogger(TournamentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gson.toJson(response);

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteTournament(@PathVariable(value = "id") Integer i) {

        Gson gson = new Gson();
        tournamentService.deleteTournament(i);

        List<TournamentDTO> response = tournamentService.findAll();

        return gson.toJson(response);
    }

    @RequestMapping(path = "/update/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editTournament(@RequestBody TournamentDTO t) {

        Gson gson = new Gson();

        TournamentDTO edited = tournamentService.update(t);

        return gson.toJson(edited);

    }

}
