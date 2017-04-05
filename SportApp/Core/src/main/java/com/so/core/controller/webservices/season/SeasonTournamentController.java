package com.so.core.controller.webservices.season;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.core.exception.AppException;
import com.so.core.services.season.SeasonTournamentService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin
@RequestMapping("/seasontournament")
public class SeasonTournamentController {

    @Autowired
    SeasonTournamentService seasonTournamentService;

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournaments(@RequestParam(value = "q", required = false) String query) throws AppException {
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
    public String getSeasonTournament(@PathVariable(value = "id") Integer id) throws AppException {
        SeasonTournamentDTO seasonTournamentDTO;
        Gson gson = new Gson();

        seasonTournamentDTO = seasonTournamentService.findById(id);
        return gson.toJson(seasonTournamentDTO);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String createSeasonTournament(@RequestBody SeasonTournamentDTO st) throws IOException, AppException {
        Gson gson = new Gson();

        SeasonTournamentDTO response = seasonTournamentService.createSeasonTournament(st);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteSeasonTournament(@PathVariable(value = "id") Integer i) throws AppException {
        Gson gson = new Gson();

        seasonTournamentService.deleteSeasonTournament(i);
        List< SeasonTournamentDTO> response = seasonTournamentService.findAll();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editSeasonTOurnament(@PathVariable(value="id") Integer id, @RequestBody SeasonTournamentDTO st) throws AppException {
        Gson gson = new Gson();
        if(!Objects.equals(id, st.getId())){
            throw new AppException(HttpStatus.BAD_REQUEST,"id url sa nerovna s id v dto");
        }
        SeasonTournamentDTO edited = seasonTournamentService.update(st);
        return gson.toJson(edited);
    }
}
