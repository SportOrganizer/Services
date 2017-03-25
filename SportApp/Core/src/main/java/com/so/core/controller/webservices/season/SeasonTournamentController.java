package com.so.core.controller.webservices.season;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.core.services.season.SeasonTournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/seasontournament")
public class SeasonTournamentController {

    @Autowired
    SeasonTournamentService seasonTournamentService;

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournaments(@RequestParam(value = "q", required = false) String query) {
        List<SeasonTournamentDTO> seasonTournaments;
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();

        if (query != null) {
            seasonTournaments = seasonTournamentService.findByNameContaining(query);
            jo.addProperty("query", query);

        } else {
            seasonTournaments = seasonTournamentService.findAll();
        }

        jo.addProperty("length", seasonTournaments.size());
        jo.add("results", gson.toJsonTree(seasonTournaments));

        return jo.toString();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournament(@PathVariable(value = "id") Integer id) {

        SeasonTournamentDTO seasonTournamentDTO;
        Gson gson = new Gson();

        seasonTournamentDTO = seasonTournamentService.findById(id);

        return gson.toJson(seasonTournamentDTO);
    }

//    {
//        "seasonId": 1,
//            "tournamentId": 1,
//            "name": "Tests2"
//    }
    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String createSeasonTournament(@RequestBody SeasonTournamentDTO st) {

        Gson gson = new Gson();

        SeasonTournamentDTO response = null;
        try {
            response = seasonTournamentService.createSeasonTournament(st);
        } catch (Exception ex) {
            Logger.getLogger(SeasonTournamentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteSeasonTournament(@PathVariable(value = "id") Integer i) {

        Gson gson = new Gson();
        seasonTournamentService.deleteSeasonTournament(i);

        List< SeasonTournamentDTO> response = seasonTournamentService.findAll();

        return gson.toJson(response);
    }
    
      @RequestMapping(path = "/update/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
      public String editSeasonTOurnament(@RequestBody SeasonTournamentDTO st){
          
          Gson gson = new Gson();
          
          SeasonTournamentDTO edited = seasonTournamentService.update(st);
          
          return gson.toJson(edited);
          
      }
}
