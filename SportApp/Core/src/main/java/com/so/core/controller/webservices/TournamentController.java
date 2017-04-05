package com.so.core.controller.webservices;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.core.controller.dto.TournamentDTO;
import com.so.core.exception.AppException;
import com.so.core.services.TournamentService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin
@RequestMapping("/tournament")
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getTournaments(@RequestParam(value = "q", required = false) String query) throws AppException {
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
    public String getTournament(@PathVariable(value = "id") Integer id) throws AppException {
        TournamentDTO tournamentDTO;
        Gson gson = new Gson();
        tournamentDTO = tournamentService.findById(id);
        return gson.toJson(tournamentDTO);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String createTournament(@RequestBody TournamentDTO t) throws IOException, AppException {
        Gson gson = new Gson();
        TournamentDTO response = tournamentService.createTournament(t);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteTournament(@PathVariable(value = "id") Integer i) throws AppException {
        Gson gson = new Gson();
        tournamentService.deleteTournament(i);
        List<TournamentDTO> response = tournamentService.findAll();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editTournament(@PathVariable(value = "id") Integer id, @RequestBody TournamentDTO t) throws AppException {
        Gson gson = new Gson();

        if (!Objects.equals(id, t.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id url sa nerovna s id v dto");
        }
        TournamentDTO edited = tournamentService.update(t);
        return gson.toJson(edited);
    }

}
