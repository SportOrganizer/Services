/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices.game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.core.controller.dto.game.CompetitorTeamPlayerDto;
import com.so.core.exception.AppException;
import com.so.core.services.game.CompetitorTeamPlayerService;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kristián Kačinetz
 */
@RestController
@CrossOrigin
@RequestMapping("/competitorteamplayer")
public class CompetitorTeamPlayerController {

    @Autowired
    CompetitorTeamPlayerService competitorTeamPlayerService;

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getCompetitorTeamPlayers(@RequestParam(value = "q", required = false) String query) throws AppException {
        List<CompetitorTeamPlayerDto> competitorTeamPlayers;
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();

        competitorTeamPlayers = competitorTeamPlayerService.findAll();

        jo.addProperty("length", competitorTeamPlayers.size());
        jo.add("results", gson.toJsonTree(competitorTeamPlayers));

        return jo.toString();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getCompetitorTeamPlayer(@PathVariable(value = "id") Integer id) throws AppException {
        CompetitorTeamPlayerDto competitorTeamPlayerDto;
        Gson gson = new Gson();

        competitorTeamPlayerDto = competitorTeamPlayerService.findByID(id);
        return gson.toJson(competitorTeamPlayerDto);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String createCompetitorTeamPlayer(@RequestBody CompetitorTeamPlayerDto ct) throws IOException, AppException {
        Gson gson = new Gson();

        CompetitorTeamPlayerDto response = competitorTeamPlayerService.createCompetitorTeamPlayer(ct);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteCompetitorTeamPlayer(@PathVariable(value = "id") Integer i) throws AppException {
        Gson gson = new Gson();

        competitorTeamPlayerService.deleteCompetitorTeamPlayer(i);
        List< CompetitorTeamPlayerDto> response = competitorTeamPlayerService.findAll();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editCompetitorTeamPlayer(@PathVariable(value = "id") Integer id, @RequestBody CompetitorTeamPlayerDto st) throws AppException {
        Gson gson = new Gson();
        if (!Objects.equals(id, st.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id url sa nerovna s id v dto");
        }
        CompetitorTeamPlayerDto edited = competitorTeamPlayerService.update(st);
        return gson.toJson(edited);
    }
}
